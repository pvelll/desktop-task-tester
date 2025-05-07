package com.sushkpavel.leetcode.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

val appConfigPath = "${File(".").absolutePath}/app.properties"

fun ensureConfigFileExists() {
    File(appConfigPath).let {
        if (!it.exists()) {
            it.createNewFile()
        }
    }
}

actual fun saveToken(token: String) {
    ensureConfigFileExists()
    Properties().apply {
        load(FileInputStream(appConfigPath))
        setProperty("token", token)
        store(FileOutputStream(appConfigPath), null)
    }
    println("Token saved: $token")
}

actual fun getToken(): String {
    ensureConfigFileExists()
    return Properties().run {
        load(FileInputStream(appConfigPath))
        getProperty("token", "")
    }.also {
        println(it)
    }
}

actual fun removeToken() {
    ensureConfigFileExists()
    Properties().apply {
        load(FileInputStream(appConfigPath))
        remove("token")
        store(FileOutputStream(appConfigPath), null)
    }
    println("Token removed")
}

