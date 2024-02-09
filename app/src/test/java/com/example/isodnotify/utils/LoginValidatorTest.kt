package com.example.isodnotify.utils

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
class LoginValidatorTest{
    private lateinit var loginValidator: LoginValidator
    private lateinit var name: String
    private lateinit var apiKey: String
    @Before
    fun setUp() {
        loginValidator = LoginValidator()
        name = System.getenv("ISOD_USERNAME")
        apiKey = System.getenv("ISOD_API")
    }
    @Test
    fun validate() {
        assertTrue(loginValidator.validate(name, apiKey))
    }

}