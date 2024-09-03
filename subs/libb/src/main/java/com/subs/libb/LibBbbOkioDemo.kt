package com.subs.libb

import okio.Okio
import java.io.File


@Suppress("DEPRECATION_ERROR")
class LibBbbOkioDemo {

    fun okBbbb() {
        val file = File("")
        val okio = Okio.buffer(Okio.source(file))
    }
}