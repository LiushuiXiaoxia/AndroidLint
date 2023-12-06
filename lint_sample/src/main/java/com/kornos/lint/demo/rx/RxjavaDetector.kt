package com.kornos.lint.demo.rx

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.kornos.lint.demo.entity.getQualifiedName
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.util.isMethodCall

class RxjavaDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE = Issue.create(
            "RxJavaV1Check",
            "RxJava v1 已不推荐使用，建议升级到 rxjava3 或使用协程代替",
            "RxJava v1 已不推荐使用，建议升级到 rxjava3 或使用协程代替",
            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            Implementation(RxjavaDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement?>>? {
        val types: MutableList<Class<out UElement?>> = ArrayList()
        types.add(UCallExpression::class.java)
        return types
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {

            override fun visitCallExpression(node: UCallExpression) {
                if (!node.isMethodCall()) {
                    return
                }
                try {
                    if (node.receiverType != null) {
                        if (node.getQualifiedName().startsWith("rx.android.")) {
                            context.report(
                                ISSUE, node, context.getLocation(node),
                                "RxJavaV1 已不推荐使用"
                            )
                        } else if (node.getQualifiedName().startsWith("rx.")) {
                            context.report(
                                ISSUE,
                                node,
                                context.getLocation(node),
                                "RxAndroid 已不推荐使用"
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
