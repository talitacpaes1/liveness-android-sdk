package br.com.oititec.facecaptchasample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.oiti.certiface.FaceCaptchaActivity
import br.com.oiti.certiface.UserData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onOpenFaceCaptchaClick(view: View) {

        val userData = UserData(appKey = APP_KEY)

        val intent = Intent(this, FaceCaptchaActivity::class.java).apply {
            putExtra(FaceCaptchaActivity.PARAM_ENDPOINT, ENDPOINT)
            putExtra(FaceCaptchaActivity.PARAM_USER_DATA, userData)
            putExtra(FaceCaptchaActivity.PARAM_OVERLAY_IMAGE, R.drawable.overlay)
        }

        startActivityForResult(intent, CAPTCHA_RESULT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAPTCHA_RESULT_REQUEST) {
            when (resultCode) {
                Activity.RESULT_OK -> onResultSuccess(data)
                Activity.RESULT_CANCELED -> onResultCancelled(data)
            }
        }
    }

    private fun onResultSuccess(data: Intent?) {
        val result = data?.getBooleanExtra(FaceCaptchaActivity.PARAM_RESULT, false)
        val cause = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_CAUSE)
        val hash = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_HASH)
        val protocol = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_PROTOCOL)
        Toast.makeText(this, "Valid: $result\nCause: $cause", Toast.LENGTH_LONG).show()
        Log.i("RESULT_OK", "$result - $cause - $hash - $protocol")
    }

    private fun onResultCancelled(data: Intent?) {
        val errorMessage = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR)
        val errorImage = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR_IMAGE)
        val errorAppKey = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR_APPKEY)

        Toast.makeText(this, "Ação cancelada, Erro: $errorMessage", Toast.LENGTH_LONG).show()

        if (errorImage != null) {
            Toast.makeText(this, "Ação cancelada, Erro: $errorImage", Toast.LENGTH_LONG).show()
        }

        if (errorAppKey != null) {
            Toast.makeText(this, "Ação cancelada, Erro: APP_KEY inválido", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val CAPTCHA_RESULT_REQUEST = 1
        private const val ENDPOINT = "https://comercial.certiface.com.br:443"
        private const val APP_KEY = ""
    }
}
