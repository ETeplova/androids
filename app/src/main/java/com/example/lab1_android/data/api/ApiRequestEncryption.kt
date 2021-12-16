package com.example.lab1_android.data.api

import java.security.MessageDigest

object ApiRequestEncryption {
    //getters for the keys
    private val _privateKey = "2b2fd5d2b03d80b4635aaacdf4034b98f17f660f"
    val privateKey =
        _privateKey
    private val _publicKey = "0b26845ab0185ac5991192bfb9e553a8"
    val publicKey =
        _publicKey

    //hash creator
    fun toMD5hash(timeString: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(
            timeString.toByteArray()
                    + privateKey.toByteArray() + publicKey.toByteArray()
        )
        return hash.toHex()
    }

    //format the the hash
    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}