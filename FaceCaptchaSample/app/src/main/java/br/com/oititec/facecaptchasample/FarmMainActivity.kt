package br.com.oititec.facecaptchasample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.oiti.certiface.FaceCaptchaActivity
import br.com.oiti.certiface.UserData
import br.com.oiti.certiface.documentscopy.DocumentscopyActivity
import br.com.oititec.facecaptchasample.data.CertifaceRepository
import br.com.oititec.facecaptchasample.data.SharedPrefKey
import br.com.oititec.facecaptchasample.data.SharedPrefManager
import kotlinx.android.synthetic.main.activity_farm_main.*
import kotlinx.android.synthetic.main.activity_main.progress_bar

class FarmMainActivity : AppCompatActivity() {

    private val sharedPrefManager = SharedPrefManager(this)
    private var repository = CertifaceRepository(this, ENDPOINT)
    private var appKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_main)
        if (sharedPrefManager.getString(SharedPrefKey.TOKEN) == null) {
            sharedPrefManager.saveString(SharedPrefKey.USERNAME, USERNAME)
            sharedPrefManager.saveString(SharedPrefKey.CPF, CPF)
            sharedPrefManager.saveString(SharedPrefKey.NAME, NAME)
            sharedPrefManager.saveString(SharedPrefKey.BIRTHDAY, BIRTHDAY)
            authenticate()
        }
    }

    fun onStartClick(v: View) {
        generateAppKey {
            startFaceCaptcha()
        }
    }

    private fun startFaceCaptcha() {

        if (appKey == null) {
            Toast.makeText(this, "AppKey null", Toast.LENGTH_LONG).show()
            return
        }

        val userData = UserData(appKey = this.appKey!!)

        val intent = Intent(this, FaceCaptchaActivity::class.java).apply {
            putExtra(FaceCaptchaActivity.PARAM_ENDPOINT, ENDPOINT)
            putExtra(FaceCaptchaActivity.PARAM_USER_DATA, userData)
        }

        startActivityForResult(intent, CAPTCHA_RESULT_REQUEST)
    }

    private fun startDocumentscopy() {

        val intent = Intent(this, DocumentscopyActivity::class.java).apply {
            putExtra(DocumentscopyActivity.PARAM_ENDPOINT, ENDPOINT)
            putExtra(DocumentscopyActivity.PARAM_APP_KEY, appKey)
        }

        startActivityForResult(intent, DOCUMENTSCOPY_RESULT_REQUEST)
    }

    private fun authenticate() {

        start_button.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE

        repository.authenticate(USERNAME, PASSWORD) {
            runOnUiThread {
                progress_bar.visibility = View.GONE
                if (it != null) {
                    sharedPrefManager.saveString(SharedPrefKey.TOKEN, it)
                    start_button.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this, "Erro ao autenticar", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun generateAppKey(callback: (String) -> Unit) {

        start_button.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE

        repository.generateAppKey {
            runOnUiThread {
                start_button.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
                if (it != null) {
                    appKey = it
                    callback(it)
                } else {
                    Toast.makeText(
                        this,
                        "Erro ao gerar app key. Verifique se as credenciais estão corretas.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CAPTCHA_RESULT_REQUEST -> {
                when (resultCode) {
                    Activity.RESULT_OK -> onFaceCaptchaResultSuccess(data)
                    Activity.RESULT_CANCELED -> onFaceCaptchaResultCancelled(data)
                }
            }
            DOCUMENTSCOPY_RESULT_REQUEST -> {
                when (resultCode) {
                    Activity.RESULT_OK -> onDocumentscopyResultSuccess()
                    Activity.RESULT_CANCELED -> onDocumentscopyCancelled(data)
                }
            }
        }
    }

    private fun onFaceCaptchaResultSuccess(data: Intent?) {
        val result = data?.getBooleanExtra(FaceCaptchaActivity.PARAM_RESULT, false)
        val cause = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_CAUSE)
        val codID = data?.getDoubleExtra(FaceCaptchaActivity.PARAM_RESULT_COD_ID, 0.0)
        val protocol = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_PROTOCOL)
        Toast.makeText(this, "Valid: $result\nCause: $cause", Toast.LENGTH_LONG).show()
        Log.d(TAG, "Result: $result - Cause: $cause - CodID: $codID - Protocol: $protocol")
        startDocumentscopy()
    }

    private fun onFaceCaptchaResultCancelled(data: Intent?) {
        val errorMessage = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR)
        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()
    }

    private fun onDocumentscopyResultSuccess() {
        Toast.makeText(this, "Sucesso documentoscopia", Toast.LENGTH_LONG).show()
        Log.d(TAG, "Sucesso documentoscopia")
    }

    private fun onDocumentscopyCancelled(data: Intent?) {
        val errorMessage = data?.getStringExtra(DocumentscopyActivity.PARAM_RESULT_ERROR)
        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()
    }

    companion object {

        private val TAG = FarmMainActivity::class.java.simpleName

        private const val CAPTCHA_RESULT_REQUEST = 1
        private const val DOCUMENTSCOPY_RESULT_REQUEST = 2

        private const val ENDPOINT = "https://comercial.certiface.com.br:8443"

        private const val USERNAME = ""
        private const val PASSWORD = ""
        private const val NAME = ""
        private const val CPF = ""
        private const val BIRTHDAY = ""
    }
}