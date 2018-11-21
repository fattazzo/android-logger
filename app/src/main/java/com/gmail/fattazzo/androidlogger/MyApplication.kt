/*
 * Project: android-logger
 * File: MyApplication.kt
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

import android.app.Application
import com.orhanobut.logger.*


/**
 * @author fattazzo
 *         <p/>
 *         date: 13/11/18
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogLogCat()
    }

    companion object {

        private const val LOGGER_TAG = "MY_LOGGER"

        fun initLogLogCat() {
            val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(LogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(LOGGER_TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()

            Logger.clearLogAdapters()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        }

        fun initLogDisk() {
            val csvFormatStrategy = CsvFormatStrategy.newBuilder()
                .tag(LOGGER_TAG)
                .build()

            Logger.clearLogAdapters()
            Logger.addLogAdapter(DiskLogAdapter(csvFormatStrategy))
        }

        fun initLogLogCatCompact() {
            val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(LogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(LOGGER_TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()

            Logger.clearLogAdapters()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        }
    }
}