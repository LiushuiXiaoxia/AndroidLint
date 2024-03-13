package com.kronos.sample

import okio.Okio
import java.io.File


@Suppress("DEPRECATION_ERROR")
class OkioDemo {

    fun okioDemo4() {
        val file = File("")
        val okio = Okio.buffer(Okio.source(file))
    }
}