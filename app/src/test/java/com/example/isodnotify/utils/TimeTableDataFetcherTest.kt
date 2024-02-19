package com.example.isodnotify.utils

import org.junit.Test
import kotlinx.coroutines.runBlocking
import org.junit.Before

class TimeTableDataFetcherTest{
    private lateinit var fetcher: TimeTableDataFetcher
    @Before
    fun setUp() {
        val name = System.getenv("ISOD_USERNAME")
        val apiKey = System.getenv("ISOD_API")
        this.fetcher = TimeTableDataFetcher(name, apiKey)
    }

    @Test
    fun generalTest(){
        runBlocking {
            val list = fetcher.getAllData()
            for (i in list){
                println(i)
            }
        }
    }
}