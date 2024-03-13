package com.kornos.lint.demo.leonxia

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UAnnotation
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement

class SuppressDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE = Issue.create(
            "SuppressCheck",
            "Suppress(DEPRECATION_ERROR) 尽快修复",
            "Suppress(DEPRECATION_ERROR) 尽快修复",
            Category.CORRECTNESS,
            6,
//            Severity.WARNING,
            Severity.ERROR,
            Implementation(SuppressDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement?>>? {
        val types: MutableList<Class<out UElement?>> = ArrayList()
        types.add(UCallExpression::class.java)
        types.add(UAnnotation::class.java)
        return types
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {

            override fun visitAnnotation(node: UAnnotation) {
                val an = node.qualifiedName
                if (an != "kotlin.Suppress") {
                    return
                }
//                println("SuppressDetector: an = $an")
//                println("SuppressDetector: node = $node")
//                println("SuppressDetector: node = ${node.attributeValues}")
//                node.attributeValues.forEach {
//                    println("SuppressDetector: attributeValues = ${it.asSourceString()}")
//                }
                val s = node.attributeValues.joinToString(separator = ",") { it.asSourceString() }
                println("SuppressDetector: attributeValues = $s")
                if (s.contains("DEPRECATION_ERROR")) {
                    context.report(
                        ISSUE, node, context.getLocation(node),
                        "@Suppress('DEPRECATION_ERROR') 只是临时解决方案，请尽快修复"
                    )
                }
            }
        }
    }
}
