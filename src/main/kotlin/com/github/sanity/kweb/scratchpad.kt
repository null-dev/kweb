package com.github.sanity.kweb

import com.github.sanity.kweb.clientConduits.InefficientClientConduit
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlin.concurrent.thread

/**
 * Created by ian on 11/6/16.
 */

fun main(args: Array<String>) {
    val cc = InefficientClientConduit(8091) {
        thread {
            async {
                for (x in 0 .. 100) {
                    doc().body().setInnerHTML("<h1 id=\"342\">contents</h1>")
                    val h1 = doc().getElementById("342")
                    println("h1 contents: '${h1.readInnerHtml().await()}'")
                    h1.setInnerHTML("Contents modified")
                    println("h1 contents: '${h1.readInnerHtml().await()}'")
                    Thread.sleep(500)
                }
            }
        }
        false
    }
}

