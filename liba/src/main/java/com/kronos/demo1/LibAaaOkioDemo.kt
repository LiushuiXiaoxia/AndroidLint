package com.kronos.demo1

import okio.Okio
import java.io.File


@Suppress("DEPRECATION_ERROR")
class LibAaaOkioDemo {

    fun okAaaa() {
        val file = File("")
        val okio = Okio.buffer(Okio.source(file))
    }
}