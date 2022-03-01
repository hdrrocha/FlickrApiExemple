package com.mindera.flickergallery

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.*

open class BaseUnitTest {
    /**
     * For MockWebServer instance
     */
    protected lateinit var mMockServerInstance: MockWebServer

    /**
     * Default, let server be shut down
     */
    private var mShouldStart = false

    @Before
    open fun setUp() {
        startMockServer(true)
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
        mMockServerInstance.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    /**
     * Reads input file and converts to json
     */
    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    /**
     * Start Mockwebserver
     */
    private fun startMockServer(shouldStart: Boolean) {
        if (shouldStart) {
            mShouldStart = shouldStart
            mMockServerInstance = MockWebServer()
            mMockServerInstance.start()
        }
    }

    /**
     * Stop Mockwebserver
     */
    private fun stopMockServer() {
        if (mShouldStart) {
            mMockServerInstance.shutdown()
        }
    }

    protected inline fun <reified T> buildApi(): T {
        return Retrofit.Builder()
            .baseUrl(mMockServerInstance.url("/").toString())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()
                )
            ).addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(T::class.java)
    }

    @After
    open fun tearDown() {
        // Stop Mock server
        stopMockServer()
    }
}
