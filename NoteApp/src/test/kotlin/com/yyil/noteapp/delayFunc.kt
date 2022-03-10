package com.yyil.noteapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun delayOneSecond() {
    runBlocking {
        launch {
            delay(1000L)
        }
    }
}