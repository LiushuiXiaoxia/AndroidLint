package com.kronos.sample

import okio.Okio
import java.io.File


@Suppress
fun okioDemo1() {
}

@Suppress("DEPRECATION_ERROR")
fun okioDemo2() {
    val file = File("")
    val okio = Okio.buffer(Okio.source(file))
}

@Suppress("xxx", "DEPRECATION_ERROR")
fun okioDemo3() {
    val file = File("")
    val okio = Okio.buffer(Okio.source(file))
}