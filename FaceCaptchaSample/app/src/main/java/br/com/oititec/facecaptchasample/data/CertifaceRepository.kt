package br.com.oititec.facecaptchasample.data;

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import br.com.oititec.facecaptchasample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class CertifaceRepository(context: Context, baseUrl: String) {

    private val api: CertifaceApi
    private val backgroundHandler: Handler
    private val sharedPrefManager = SharedPrefManager(context)

    init {

        val backgroundThread = HandlerThread(this::class.java.simpleName)
        backgroundThread.let {
            it.start()
            backgroundHandler = Handler(it.looper)
        }

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        api = retrofit.create(CertifaceApi::class.java)
    }

    fun authenticate(username: String, password: String, callback: (String?) -> Unit) {

        val md = MessageDigest.getInstance("MD5")
        val passwordMD5 =
            BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')

        backgroundHandler.post {
            val credentialResponse = api.credential(username, passwordMD5).execute()
            callback(credentialResponse.body()?.string())
        }
    }

    fun generateAppKey(callback: (String?) -> Unit) {
        backgroundHandler.post {
            val response = api.appKey(
                sharedPrefManager.getString(SharedPrefKey.USERNAME) ?: "",
                sharedPrefManager.getString(SharedPrefKey.TOKEN) ?: "",
                sharedPrefManager.getString(SharedPrefKey.NAME) ?: "",
                sharedPrefManager.getString(SharedPrefKey.CPF) ?: "",
                sharedPrefManager.getString(SharedPrefKey.BIRTHDAY) ?: ""
            ).execute()
            callback(response.body()?.appkey)
        }
    }
}
