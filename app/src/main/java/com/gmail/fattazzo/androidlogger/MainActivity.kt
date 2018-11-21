/*
 * Project: android-logger
 * File: MainActivity.kt
 *
 * Created by fattazzo
 * Copyright © 2018 Gianluca Fattarsi. All rights reserved.
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gmail.fattazzo.androidlogger

import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.Logger
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity


@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

    @Click
    internal fun sampleLogButtonClicked() {
        Logger.d("Debug log!")
        Logger.e("Error log!")
        Logger.w("Warning log!")
        Logger.v("Verbose log!")
        Logger.i("Information log!")
    }

    @Click
    internal fun collectionLogButtonClicked() {
        val map: Map<Int, String> = mapOf(Pair(1, "uno"), Pair(2, "due"), Pair(3, "tre"))
        val set: Set<String> = setOf("a", "b", "c", "d")
        val list: List<Float> = listOf(23.3f, 45.1f, 12f, 34.8f)
        val array: Array<String> = arrayOf("giallo", "verde", "rosso", "blu")

        Logger.d(map)
        Logger.d(set)
        Logger.d(list)
        Logger.d(array)
    }

    @Click
    internal fun jsonXmlLogButtonClicked() {
        val json: String =
            "{\"menu\": {\"id\": \"file\", \"value\": \"File\", \"popup\": { \"menuitem\": [ {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"}," +
                    "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"}, {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}] }}}"
        val xml: String =
            "<menu id=\"file\" value=\"File\"><popup><menuitem value=\"New\" onclick=\"CreateNewDoc()\" />" +
                    "<menuitem value=\"Open\" onclick=\"OpenDoc()\" /><menuitem value=\"Close\" onclick=\"CloseDoc()\" /></popup></menu>"

        Logger.json(json)
        Logger.xml(xml)
    }

    @Click
    internal fun threadLogButtonClicked() {
        Logger.d("Log in main thread")
        backgroundWork()
    }

    @Background
    open fun backgroundWork() {
        Logger.d("Log in background thread")
    }

    @Click
    internal fun diskLogButtonClicked() {

        try {
            MyApplication.initLogDisk()

            Logger.d("Test disk log")
        } finally {
            MyApplication.initLogLogCat()
        }
    }

    @Click
    internal fun compactLogButtonClicked() {

        try {
            MyApplication.initLogLogCatCompact()

            collectionLogButtonClicked()
        } finally {
            MyApplication.initLogLogCat()
        }
    }
}
