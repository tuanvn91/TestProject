package com.tadfas.testproject

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class FileDownloader(
    private val context: Context,
    private val url: String,
    private val fileName: String
) {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val errorMessage = "File couldn't be downloaded"
    private val bufferLengthBytes: Int = 1024 * 4

    fun download(): Observable<Int> {
        return Observable.create { emitter ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // To Download File for Android 10 and above
                val content = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
                }
                val uri = context.contentResolver.insert(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    content
                )
                uri?.apply {
                    val responseBody = getResponseBody(url)
                    if (responseBody != null
                    ) {
                        responseBody.byteStream().use { inputStream ->
                            context.contentResolver.openOutputStream(uri)?.use { fileOutStream ->
                                writeOutStream(
                                    inStream = inputStream,
                                    outStream = fileOutStream,
                                    contentLength = responseBody.contentLength(),
                                    emitter = emitter
                                )
                            }
                            emitter.onComplete()
                        }
                    } else {
                        emitter.onError(Throwable(errorMessage))
                    }
                }
            } else { // For Android versions below than 10
                val directory = File(
                    Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS
                    ).absolutePath
                ).apply {
                    if (!exists()) {
                        mkdir()
                    }
                }

                val file = File(directory, fileName)
                val responseBody = getResponseBody(url)

                if (responseBody != null) {
                    responseBody.byteStream().use { inputStream ->
                        file.outputStream().use { fileOutStream ->
                            writeOutStream(
                                inStream = inputStream,
                                outStream = fileOutStream,
                                contentLength = responseBody.contentLength(),
                                emitter = emitter
                            )
                        }
                        emitter.onComplete()
                    }

                } else {
                    emitter.onError(Throwable(errorMessage))
                }
            }
        }
    }

    private fun getResponseBody(url: String): ResponseBody? {
        val response = okHttpClient.newCall(Request.Builder().url(url).build()).execute()

        return if (response.code >= HttpURLConnection.HTTP_OK &&
            response.code < HttpURLConnection.HTTP_MULT_CHOICE &&
            response.body != null
        )
            response.body
        else
            null
    }

    private fun writeOutStream(
        inStream: InputStream,
        outStream: OutputStream,
        contentLength: Long,
        emitter: ObservableEmitter<Int>
    ) {
        var bytesCopied = 0
        val buffer = ByteArray(bufferLengthBytes)
        var bytes = inStream.read(buffer)
        while (bytes >= 0) {
            outStream.write(buffer, 0, bytes)
            bytesCopied += bytes
            bytes = inStream.read(buffer)
//                            emitter.onNext(
            ((bytesCopied * 100) / contentLength).toInt()
//                            )
        }
        outStream.flush()
        outStream.close()
        inStream.close()
    }
}