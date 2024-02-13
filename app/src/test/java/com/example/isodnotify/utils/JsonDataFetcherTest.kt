package com.example.isodnotify.utils

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import java.util.Date

class JsonDataFetcherTest {

    private lateinit var name: String
    private lateinit var apiKey: String
    @Before
    fun setUp() {
        name = System.getenv("ISOD_USERNAME")
        apiKey = System.getenv("ISOD_API")
    }
    @Test
    fun getJsonDataFromUrl() {
        //given
        val expectedJson = """{"items":[{"hash":"mock_hash","subject":"mock_subject","modifiedDate":"mock_date","modifiedBy":"mock_user","attachments":[],"noAttachments":0,"type":1002}],"username":"mock_username","semester":"mock_semester","firstname":"mock_firstname","lastname":"mock_lastname","studentNo":"mock_studentNo"}"""

        //when
        val mockFetcher = Mockito.mock(JsonDataFetcher::class.java)
        Mockito.`when`(mockFetcher.getJsonDataFromUrl(mockFetcher.createUrlFromNameAndApi(name, apiKey))).thenReturn(expectedJson)

        //then
        assertEquals(expectedJson, mockFetcher.getJsonDataFromUrl(mockFetcher.createUrlFromNameAndApi(name, apiKey)))
    }

    @Test
    fun getHashAndSubject(){
        //given
        val mockFetcher = Mockito.mock(JsonDataFetcher::class.java)
        val url = "mock_url"
        val jsonString = """{"items":[{"hash":"mock_hash","subject":"mock_subject","modifiedDate":"mock_date","modifiedBy":"mock_user","attachments":[],"noAttachments":0,"type":1002}],"username":"mock_username","semester":"mock_semester","firstname":"mock_firstname","lastname":"mock_lastname","studentNo":"mock_studentNo"}"""
        val expectedNotifyInformations = AnnouncementData("mock_hash", "mock_subject", "mock_user", Date("mock_date"))

        Mockito.`when`(mockFetcher.createUrlFromNameAndApi(name, apiKey)).thenReturn(url)
        Mockito.`when`(mockFetcher.getJsonDataFromUrl(url)).thenReturn(jsonString)
        Mockito.`when`(mockFetcher.getImportantInformation(jsonString)).thenReturn(expectedNotifyInformations)

        //when
        val notifyInformations = mockFetcher.getImportantInformation(jsonString)

        //then
        assertEquals(expectedNotifyInformations, notifyInformations)
    }
}