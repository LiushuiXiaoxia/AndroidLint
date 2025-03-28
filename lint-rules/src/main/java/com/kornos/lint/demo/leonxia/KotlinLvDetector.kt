package com.kornos.lint.demo.leonxia

import com.android.tools.lint.detector.api.*

class KotlinLvDetector : Detector(), Detector.GradleScanner {

    companion object {
        @JvmField
        val ISSUE = Issue.create(
            "KotlinLvDetector",
            "当前使用自定义的 languageVersion，建议去除，使用全局配置",
            "当前使用自定义的 languageVersion，建议去除，使用全局配置",
            Category.CORRECTNESS,
            7,
            Severity.ERROR,
            Implementation(KotlinLvDetector::class.java, Scope.GRADLE_SCOPE)
        )
    }

    override fun checkMethodCall(
        context: GradleContext,
        statement: String,
        parent: String?,
        namedArguments: Map<String, String>,
        unnamedArguments: List<String>,
        cookie: Any
    ) {
        if (statement == "kotlinOptions") {
            // println("kotlinOptions: namedArguments = $namedArguments, unnamedArguments = $unnamedArguments")
            val ret = unnamedArguments.any { it.contains("languageVersion") }
            if (ret) {
                report(context, cookie, ISSUE, "boom")
                return
            }
        }
    }

    private fun report(
        context: Context,
        cookie: Any,
        issue: Issue,
        message: String,
        fix: LintFix? = null
    ) {
        // Some methods in GradleDetector are run without the PSI read lock in order
        // to accommodate network requests, so we grab the read lock here.
        context.client.runReadAction(Runnable {
            if (context.isEnabled(issue) && context is GradleContext) {
                // Suppressed?
                // Temporarily unconditionally checking for suppress comments in Gradle files
                // since Studio insists on an AndroidLint id prefix
                val checkComments = /*context.getClient().checkForSuppressComments() &&*/
                    context.containsCommentSuppress()
                if (checkComments && context.isSuppressedWithComment(cookie, issue)) {
                    return@Runnable
                }
                val location = context.getLocation(cookie)
                context.report(issue, location, message, fix)
            }
        })
    }
}