package br.com.oititec.facecaptchasample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.oiti.certiface.data.FaceCaptchaErrorCode
import br.com.oiti.certiface.documentscopy.DocumentscopyActivity
import br.com.oiti.certiface.documentscopy.DocumentscopyErrorCode
import br.com.oiti.certiface.facecaptcha.FaceCaptchaActivity
import br.com.oiti.certiface.facecaptcha.UserData
import br.com.oititec.facecaptchasample.data.CertifaceRepository
import br.com.oititec.facecaptchasample.data.SharedPrefKey
import br.com.oititec.facecaptchasample.data.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sharedPrefManager = SharedPrefManager(this)
    private var repository: CertifaceRepository? = null
    private var appKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateView()
    }

    override fun onResume() {
        super.onResume()
        if (sharedPrefManager.getString(SharedPrefKey.TOKEN) == null) {
            sharedPrefManager.saveString(SharedPrefKey.BASE_URL, ENDPOINT)
            showCredentialsActivity()
        } else {
            repository =
                CertifaceRepository(this, sharedPrefManager.getString(SharedPrefKey.BASE_URL) ?: "")
        }
    }

    private fun populateView() {
        cpf_edit_text.setText(sharedPrefManager.getString(SharedPrefKey.CPF))
        name_edit_text.setText(sharedPrefManager.getString(SharedPrefKey.NAME))
        birthday_edit_text.setText(sharedPrefManager.getString(SharedPrefKey.BIRTHDAY))
    }

    private fun showCredentialsActivity() {
        val intent = Intent(this, CredentialActivity::class.java)
        startActivity(intent)
    }

    private fun generateAppKey(callback: (String) -> Unit) {

        sharedPrefManager.saveString(SharedPrefKey.CPF, cpf_edit_text.text.toString())
        sharedPrefManager.saveString(SharedPrefKey.NAME, name_edit_text.text.toString())
        sharedPrefManager.saveString(SharedPrefKey.BIRTHDAY, birthday_edit_text.text.toString())

        progress_bar.visibility = View.VISIBLE

        repository?.generateAppKey {
            runOnUiThread {
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

    fun onCredentialsClick(view: View) {
        showCredentialsActivity()
    }

    fun onDefaultClick(view: View) {

        generateAppKey {

            val userData = UserData(appKey = it)

            val intent = Intent(this, FaceCaptchaActivity::class.java).apply {
                putExtra(
                    FaceCaptchaActivity.PARAM_ENDPOINT,
                    sharedPrefManager.getString(SharedPrefKey.BASE_URL)
                )
                putExtra(FaceCaptchaActivity.PARAM_USER_DATA, userData)
                putExtra(
                    FaceCaptchaActivity.PARAM_DEBUG_ON,
                    false
                ) // Passar true para mostrar logs na tela
                putExtra(FaceCaptchaActivity.PARAM_SHOW_CONFIRMATION, true)
            }

            startActivityForResult(intent, CAPTCHA_RESULT_REQUEST)
        }
    }

    fun onCustomImageClick(view: View) {

        generateAppKey {

            val userData = UserData(appKey = it)

            val intent = Intent(this, FaceCaptchaActivity::class.java).apply {
                putExtra(
                    FaceCaptchaActivity.PARAM_ENDPOINT,
                    sharedPrefManager.getString(SharedPrefKey.BASE_URL)
                )
                putExtra(FaceCaptchaActivity.PARAM_USER_DATA, userData)
                putExtra(
                    FaceCaptchaActivity.PARAM_DEBUG_ON,
                    false
                ) // Passar true para mostrar logs na tela
                putExtra(FaceCaptchaActivity.PARAM_OVERLAY_IMAGE, R.drawable.custom_overlay)
            }

            startActivityForResult(intent, CAPTCHA_RESULT_REQUEST)
        }
    }

    fun onDocumentscopyClick(view: View) {

        val intent = Intent(this, DocumentscopyActivity::class.java).apply {
            putExtra(
                DocumentscopyActivity.PARAM_ENDPOINT,
                sharedPrefManager.getString(SharedPrefKey.BASE_URL)
            )
            putExtra(DocumentscopyActivity.PARAM_APP_KEY, appKey)
            putExtra(
                DocumentscopyActivity.PARAM_DEBUG_ON,
                false
            ) // Passar true para mostrar logs na tela
        }

        startActivityForResult(intent, DOCUMENTSCOPY_RESULT_REQUEST)
    }

    fun onCustomDocumentscopyClick(view: View) {

        val intent = Intent(this, DocumentscopyActivity::class.java).apply {
            putExtra(
                DocumentscopyActivity.PARAM_ENDPOINT,
                sharedPrefManager.getString(SharedPrefKey.BASE_URL)
            )
            putExtra(DocumentscopyActivity.PARAM_APP_KEY, appKey)
            putExtra(
                DocumentscopyActivity.PARAM_DEBUG_ON,
                false
            ) // Passar true para mostrar logs na tela
            putExtra(
                DocumentscopyActivity.PARAM_CUSTOM_HOME_FRAGMENT,
                R.layout.fragment_doc_home_custom
            )
            putExtra(
                DocumentscopyActivity.PARAM_CUSTOM_CAMERA_FRAGMENT,
                R.layout.fragment_doc_camera_custom
            )
            putExtra(
                DocumentscopyActivity.PARAM_CUSTOM_CAM_INSTRUCTION_SINGLE,
                getString(R.string.center_doc)
            )
            putExtra(
                DocumentscopyActivity.PARAM_CUSTOM_CAM_INSTRUCTION_FRONT,
                getString(R.string.center_front)
            )
            putExtra(
                DocumentscopyActivity.PARAM_CUSTOM_CAM_INSTRUCTION_BACK,
                getString(R.string.center_back)
            )
        }

        startActivityForResult(intent, DOCUMENTSCOPY_RESULT_REQUEST)
    }

    fun onCustomViewClick(view: View) {

        generateAppKey {

            val userData = UserData(appKey = it)

            val intent = Intent(this, FaceCaptchaActivity::class.java).apply {
                putExtra(
                    FaceCaptchaActivity.PARAM_ENDPOINT,
                    sharedPrefManager.getString(SharedPrefKey.BASE_URL)
                )
                putExtra(FaceCaptchaActivity.PARAM_USER_DATA, userData)
                putExtra(
                    FaceCaptchaActivity.PARAM_DEBUG_ON,
                    false
                ) // Passar true para mostrar logs na tela
                putExtra(FaceCaptchaActivity.PARAM_CUSTOM_FRAGMENT, R.layout.fragment_custom)
            }

            startActivityForResult(intent, CAPTCHA_RESULT_REQUEST)
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
    }

    private fun onFaceCaptchaResultCancelled(data: Intent?) {

        val errorMessage = data?.getStringExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR)
        val errorCode =
            data?.getSerializableExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR_CODE) as? FaceCaptchaErrorCode

        errorCode?.let {

            Toast.makeText(
                this,
                "Código de Erro: $it\n\nMensagem: $errorMessage",
                Toast.LENGTH_LONG
            )
                .show()

            when (it) {
                FaceCaptchaErrorCode.INVALID_BUNDLE_PARAMS -> Log.d(
                    TAG,
                    "Error code: INVALID_BUNDLE_PARAMS"
                )
                FaceCaptchaErrorCode.INVALID_APP_KEY -> Log.d(TAG, "Error code: INVALID_APP_KEY")
                FaceCaptchaErrorCode.CERTIFACE_OFF -> Log.d(TAG, "Error code: CERTIFACE_OFF")
                FaceCaptchaErrorCode.NO_FRONT_CAMERA -> Log.d(TAG, "Error code: NO_FRONT_CAMERA")
                FaceCaptchaErrorCode.NO_CAMERA_PERMISSION -> Log.d(
                    TAG,
                    "Error code: NO_CAMERA_PERMISSION"
                )
                FaceCaptchaErrorCode.NO_INTERNET_CONNECTION -> Log.d(
                    TAG,
                    "Error code: NO_INTERNET_CONNECTION"
                )
                FaceCaptchaErrorCode.REQUEST_ERROR -> Log.d(TAG, "Error code: REQUEST_ERROR")
                FaceCaptchaErrorCode.CHALLENGE_INTERRUPTED -> Log.d(
                    TAG,
                    "Error code: CHALLENGE_INTERRUPTED"
                )
                FaceCaptchaErrorCode.LOW_MEMORY -> Log.d(TAG, "Error code: LOW_MEMORY")
                FaceCaptchaErrorCode.ERROR_CAMERA_SETUP -> Log.d(
                    TAG,
                    "Error code: ERROR_CAMERA_SETUP"
                )
                FaceCaptchaErrorCode.ERROR_CAPTURE_PICTURE -> Log.d(
                    TAG,
                    "Error code: ERROR_CAPTURE_PICTURE"
                )
                FaceCaptchaErrorCode.INVALID_CUSTOM_FRAGMENT -> Log.d(
                    TAG,
                    "Error code: INVALID_CUSTOM_FRAGMENT"
                )
            }
        }
    }

    private fun onDocumentscopyResultSuccess() {
        Toast.makeText(this, "Sucesso documentoscopia", Toast.LENGTH_LONG).show()
        Log.d(TAG, "Sucesso documentoscopia")
    }

    private fun onDocumentscopyCancelled(data: Intent?) {

        val errorMessage = data?.getStringExtra(DocumentscopyActivity.PARAM_RESULT_ERROR)
        val errorCode =
            data?.getSerializableExtra(DocumentscopyActivity.PARAM_RESULT_ERROR_CODE) as? DocumentscopyErrorCode

        errorCode?.let {

            Toast.makeText(
                this,
                "Código de Erro: $it\n\nMensagem: $errorMessage",
                Toast.LENGTH_LONG
            )
                .show()

            when (it) {
                DocumentscopyErrorCode.INVALID_BUNDLE_PARAMS -> Log.d(
                    TAG,
                    "Error code: INVALID_BUNDLE_PARAMS"
                )
                DocumentscopyErrorCode.INVALID_APP_KEY -> Log.d(TAG, "Error code: INVALID_APP_KEY")
                DocumentscopyErrorCode.CERTIFACE_OFF -> Log.d(TAG, "Error code: CERTIFACE_OFF")
                DocumentscopyErrorCode.NO_BACK_CAMERA -> Log.d(TAG, "Error code: NO_BACK_CAMERA")
                DocumentscopyErrorCode.NO_CAMERA_PERMISSION -> Log.d(
                    TAG,
                    "Error code: NO_CAMERA_PERMISSION"
                )
                DocumentscopyErrorCode.NO_INTERNET_CONNECTION -> Log.d(
                    TAG,
                    "Error code: NO_INTERNET_CONNECTION"
                )
                DocumentscopyErrorCode.REQUEST_ERROR -> Log.d(TAG, "Error code: REQUEST_ERROR")
                DocumentscopyErrorCode.LOW_MEMORY -> Log.d(TAG, "Error code: LOW_MEMORY")
                DocumentscopyErrorCode.ERROR_CAMERA_SETUP -> Log.d(
                    TAG,
                    "Error code: ERROR_CAMERA_SETUP"
                )
                DocumentscopyErrorCode.ERROR_CAPTURE_PICTURE -> Log.d(
                    TAG,
                    "Error code: ERROR_CAPTURE_PICTURE"
                )
                DocumentscopyErrorCode.INVALID_CUSTOM_HOME_FRAGMENT -> Log.d(
                    TAG,
                    "Error code: INVALID_CUSTOM_HOME_FRAGMENT"
                )
                DocumentscopyErrorCode.INVALID_CUSTOM_CAMERA_FRAGMENT -> Log.d(
                    TAG,
                    "Error code: INVALID_CUSTOM_CAMERA_FRAGMENT"
                )
                DocumentscopyErrorCode.INVALID_CUSTOM_CONFIRMATION_FRAGMENT -> Log.d(
                    TAG,
                    "Error code: INVALID_CUSTOM_CONFIRMATION_FRAGMENT"
                )
                DocumentscopyErrorCode.FACECAPTCHA_NOT_EXECUTED -> Log.d(
                    TAG,
                    "Error code: FACECAPTCHA_NOT_EXECUTED"
                )
            }
        }
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        private const val CAPTCHA_RESULT_REQUEST = 1
        private const val DOCUMENTSCOPY_RESULT_REQUEST = 2

        private const val ENDPOINT = "https://comercial.certiface.com.br:8443"
    }
}
