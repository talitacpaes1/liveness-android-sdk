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
import br.com.oiti.certiface.data.FaceCaptchaErrorCode

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
            putExtra(FaceCaptchaActivity.PARAM_DEBUG_ON, false) // Passar true para mostrar logs na tela
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
        val errorCode = data?.getSerializableExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR_CODE) as? FaceCaptchaErrorCode

        errorCode?.let {

            Toast.makeText(this, "Código de Erro: $it\n\nMensagem: $errorMessage", Toast.LENGTH_LONG).show()

            when (it) {
                FaceCaptchaErrorCode.INVALID_BUNDLE_PARAMS -> Log.d(TAG, "Error code: INVALID_BUNDLE_PARAMS")
                FaceCaptchaErrorCode.INVALID_APP_KEY -> Log.d(TAG, "Error code: INVALID_APP_KEY")
                FaceCaptchaErrorCode.CERTIFACE_OFF -> Log.d(TAG, "Error code: CERTIFACE_OFF")
                FaceCaptchaErrorCode.NO_FRONT_CAMERA -> Log.d(TAG, "Error code: NO_FRONT_CAMERA")
                FaceCaptchaErrorCode.NO_CAMERA_PERMISSION -> Log.d(TAG, "Error code: NO_CAMERA_PERMISSION")
                FaceCaptchaErrorCode.NO_INTERNET_CONNECTION -> Log.d(TAG, "Error code: NO_INTERNET_CONNECTION")
                FaceCaptchaErrorCode.PHONE_CALL_IN_PROGRESS -> Log.d(TAG, "Error code: PHONE_CALL_IN_PROGRESS")
                FaceCaptchaErrorCode.REQUEST_ERROR -> Log.d(TAG, "Error code: REQUEST_ERROR")
                FaceCaptchaErrorCode.CHALLENGE_INTERRUPTED -> Log.d(TAG, "Error code: CHALLENGE_INTERRUPTED")
                FaceCaptchaErrorCode.LOW_MEMORY -> Log.d(TAG, "Error code: LOW_MEMORY")
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val CAPTCHA_RESULT_REQUEST = 1
        private const val ENDPOINT = "https://comercial.certiface.com.br:443"
        private const val APP_KEY = ""
    }
}
