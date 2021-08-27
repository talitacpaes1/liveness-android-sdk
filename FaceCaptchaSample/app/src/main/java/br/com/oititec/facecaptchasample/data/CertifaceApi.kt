package br.com.oititec.facecaptchasample.data

import br.com.oititec.facecaptchasample.data.model.AppKeyResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CertifaceApi {

    @FormUrlEncoded
    @POST("/facecaptcha/service/captcha/credencial")
    fun credential(@Field("user") user: String,
                   @Field("pass") pass: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/facecaptcha/service/captcha/appkey")
    fun appKey(@Field("user") user: String,
               @Field("token") token: String,
               @Field("nome") nome: String,
               @Field("cpf") cpf: String,
               @Field("nascimento") nascimento: String): Call<AppKeyResponse>
}
