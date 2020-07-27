package yuresko.moapps.auth

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import yuresko.moapps.MoAppsApplication
import yuresko.moapps.R
import yuresko.moapps.auth.viewmodel.AuthViewModel
import yuresko.moapps.auth.viewmodel.IAuthViewModel
import yuresko.moapps.core.base.BaseActivity
import yuresko.moapps.mainview.MainActivity
import yuresko.moapps.repository.IRepository
import yuresko.moapps.utils.visibleOrGone
import javax.inject.Inject

class Login : BaseActivity() {
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var singInButton: Button
    private lateinit var loginProgress: ProgressBar
    private lateinit var textProgress: TextView
    private lateinit var infoDialog: TextView

    @Inject
    lateinit var repository: IRepository

    private lateinit var viewModel: IAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MoAppsApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        loginField = findViewById(R.id.et_login)
        passwordField = findViewById(R.id.et_password)
        singInButton = findViewById(R.id.btn_sing_in)
        loginProgress = findViewById(R.id.login_progressBar)
        textProgress = findViewById(R.id.login_progressBar_text)
        infoDialog = findViewById(R.id.logInformation)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return AuthViewModel(repository) as T
            }
        }).get(AuthViewModel::class.java)

        singInButton.setOnClickListener {
            viewModel.authentication(loginField.text.toString(), passwordField.text.toString())
        }

        viewModel
            .isLoading
            .observe(this, Observer { isLoading ->
                loginProgress.visibleOrGone(isLoading)
            })

        viewModel
            .openNextScreen
            .observe(this, Observer { _ ->
                viewModel.isErrorResponse.observe(this, Observer {
                    if (it) {
                        Toast.makeText(this, "Invalid login or password", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                })

            })

        viewModel
            .error
            .observe(this, Observer(::displayHostError))

        infoDialog.setOnClickListener {
            val myDialogFragment = InformationDialog()
            val manager = supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }
    }
}