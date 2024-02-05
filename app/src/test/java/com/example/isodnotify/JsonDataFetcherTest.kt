package com.example.isodnotify

import com.example.isodnotify.utils.JsonDataFetcher
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito

class JsonDataFetcherTest {
    @Test
    fun getJsonDataFromUrl() {
        val name = System.getenv("ISOD_USERNAME")
        val apiKey = System.getenv("ISOD_API")
        val expectedJson = """{"items":[{"hash":"mock_hash","subject":"mock_subject","modifiedDate":"mock_date","modifiedBy":"mock_user","attachments":[],"noAttachments":0,"type":1002}],"username":"mock_username","semester":"mock_semester","firstname":"mock_firstname","lastname":"mock_lastname","studentNo":"mock_studentNo"}"""

        val mockFetcher = Mockito.mock(JsonDataFetcher::class.java)
        Mockito.`when`(mockFetcher.getJsonDataFromUrl(mockFetcher.createUrlFromNameAndApi(name, apiKey))).thenReturn(expectedJson)

        assertEquals(expectedJson, mockFetcher.getJsonDataFromUrl(mockFetcher.createUrlFromNameAndApi(name, apiKey)))
    }

    //@Test
    fun getHashAndSubject(){
        val name = System.getenv("ISOD_USERNAME")
        val apiKey = System.getenv("ISOD_API")
        val mockFetcher = Mockito.mock(JsonDataFetcher::class.java)
        val url = mockFetcher.createUrlFromNameAndApi(name, apiKey)
        val jsonString = """{"items":[{"hash":"mock_hash","subject":"mock_subject","modifiedDate":"mock_date","modifiedBy":"mock_user","attachments":[],"noAttachments":0,"type":1002}],"username":"mock_username","semester":"mock_semester","firstname":"mock_firstname","lastname":"mock_lastname","studentNo":"mock_studentNo"}"""

        Mockito.`when`(mockFetcher.getJsonDataFromUrl(url)).thenReturn(jsonString)
        val (hash, subject, modifiedBy) = mockFetcher.getImportantInformation(jsonString)

        assertEquals("mock_hash", hash)
        assertEquals("mock_subject", subject)
        assertEquals("mock_user", modifiedBy)
    }
}