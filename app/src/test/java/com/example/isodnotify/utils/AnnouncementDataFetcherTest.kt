package com.example.isodnotify.utils

import org.junit.Test
import kotlinx.coroutines.runBlocking
import org.junit.Before

class AnnouncementDataFetcherTest{

    private lateinit var fetcher: AnnouncementDataFetcher
    @Before
    fun setUp() {
        val name = System.getenv("ISOD_USERNAME")
        val apiKey = System.getenv("ISOD_API")
        this.fetcher = AnnouncementDataFetcher(name, apiKey)
    }

    @Test
    fun GeneralTest(){
        runBlocking {
            val lista = fetcher.getAllData()
            for (i in lista){
                println(i)
            }
        }
    }
}