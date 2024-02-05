package com.example.isodnotify.utils

class LoginValidator {
    fun validate(username: String, password: String): Boolean {
        // Replace this with your actual validation logic
        return username.isNotEmpty() && password.isNotEmpty()
    }
}