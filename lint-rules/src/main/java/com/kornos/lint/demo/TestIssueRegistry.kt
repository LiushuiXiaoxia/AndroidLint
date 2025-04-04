/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kornos.lint.demo

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.Issue
import com.kornos.lint.demo.leonxia.KotlinLvDetector
import com.kornos.lint.demo.leonxia.RxjavaDetector
import com.kornos.lint.demo.leonxia.SuppressDetector

/*
 * The list of issues that will be checked when running <code>lint</code>.
 */
class TestIssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() {
            val lintList = mutableListOf<Issue>(
//                RouteDetector.ISSUE,
//                RouteDetector.CALL_ISSUE,
//                LogDetector.ISSUE,
//                GlideUnusedDetector.ISSUE,
//                ThreadDetector.ISSUE,
//                PngResourceDetector.ISSUE,
//                EventSpaceDetector.ISSUE,
//                SafeFileDetector.ISSUE,
//                DynamicLint.ISSUE,
//                PrivacyClassDetector.ISSUE,
//                ContextCastDetector.ISSUE,
//                PreDrawDetector.ISSUE,
//                AlertDialogDetector.ISSUE,
//                FragmentHiddenDetector.ISSUE,
//                KAEDetector.ISSUE,
//                RxjavaDetector.ISSUE,
//                SuppressDetector.ISSUE,
                KotlinLvDetector.ISSUE,
            )
            /*   ServiceLoader.load(LintSpi::class.java).forEach {
                   it.issue().forEach { issue ->
                       lintList.add(issue)
                   }
               }*/
            return lintList
        }

    override val vendor: Vendor?
        get() = Vendor(
            vendorName = "Kornos",
            identifier = "com.kornos.lint.demo",
            feedbackUrl = ""
        )
}