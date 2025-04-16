package com.sushkpavel.leetcode.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

val rootPath = File(".").absolutePath
val appConfigPath = "$rootPath/app.properties"
val appProps = Properties()

fun ensureConfigFileExists() {
    val configFile = File(appConfigPath)
    if (!configFile.exists()) {
        configFile.createNewFile()
    }
}

actual fun saveToken(token: String) {
    ensureConfigFileExists()
    println(rootPath)
    appProps.load(FileInputStream(appConfigPath))
    appProps.setProperty("token", token)
    appProps.store(FileOutputStream(appConfigPath), null)
    println("Token saved: $token")
}

actual fun getToken(): String {
    ensureConfigFileExists()
    appProps.load(FileInputStream(appConfigPath))
    val token = appProps.getProperty("token", "")
    println(token)
    return token
}

actual fun removeToken() {
    ensureConfigFileExists()
    appProps.load(FileInputStream(appConfigPath))
    appProps.remove("token")
    appProps.store(FileOutputStream(appConfigPath), null)
    println("Token removed")
}

