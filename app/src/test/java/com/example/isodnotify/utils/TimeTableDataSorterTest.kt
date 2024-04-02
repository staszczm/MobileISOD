package com.example.isodnotify.utils

import org.junit.Test
import kotlinx.coroutines.runBlocking
import org.junit.Before


class TimeTableDataSorterTest{
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
            val timeTableDataSorter = TimeTableDataSorter()
            val sortedlist = timeTableDataSorter.sortTimeTableData(list)
            //timeTableDataSorter.printTimeTableData(sortedlist)
            timeTableDataSorter.printNextTwoClasses(sortedlist)
        }
    }
}