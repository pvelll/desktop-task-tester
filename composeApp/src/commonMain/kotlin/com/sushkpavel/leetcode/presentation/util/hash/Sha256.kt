package com.sushkpavel.leetcode.presentation.util.hash

import java.security.MessageDigest

fun sha256(str : String): String {
    val bytes = str.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}