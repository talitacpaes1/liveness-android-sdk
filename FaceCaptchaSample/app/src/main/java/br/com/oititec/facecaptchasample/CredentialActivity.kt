package br.com.oititec.facecaptchasample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.oititec.facecaptchasample.data.CertifaceRepository
import br.com.oititec.facecaptchasample.data.SharedPrefKey
import br.com.oititec.facecaptchasample.data.SharedPrefManager
import kotlinx.android.synthetic.main.activity_credential.*

class CredentialActivity : AppCompatActivity() {

    private val sharedPrefManager: SharedPrefManager by lazy { SharedPrefManager(this) }
    private var repository: CertifaceRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)
        populateView()
    }

    private fun populateView() {
        base_url_edit_text.setText(sharedPrefManager.getString(SharedPrefKey.BASE_URL))
        username_edit_text.setText(sharedPrefManager.getString(SharedPrefKey.USERNAME))
    }

    fun onSaveClick(view: View) {

        save_button.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE

        repository = CertifaceRepository(this, base_url_edit_text.text.toString())

        repository?.authenticate(
            username_edit_text.text.toString(),
            password_edit_text.text.toString()
        ) {
            runOnUiThread {
                if (it != null) {
                    sharedPrefManager.saveString(SharedPrefKey.TOKEN, it)
                    sharedPrefManager.saveString(
                        SharedPrefKey.USERNAME,
                        username_edit_text.text.toString()
                    )
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao autenticar", Toast.LENGTH_LONG).show()
                    save_button.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                }
            }
        }
    }
}
