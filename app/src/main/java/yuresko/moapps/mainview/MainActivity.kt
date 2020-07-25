package yuresko.moapps.mainview

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import yuresko.moapps.MoAppsApplication
import yuresko.moapps.R
import yuresko.moapps.auth.Login
import yuresko.moapps.core.base.BaseActivity
import yuresko.moapps.mainview.model.MainMenuState
import yuresko.moapps.mainview.viewmodel.IMainMenuViewModel
import yuresko.moapps.mainview.viewmodel.MainMenuViewModel
import yuresko.moapps.repository.IRepository
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var viewModel: IMainMenuViewModel

    @Inject
    lateinit var repository: IRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MoAppsApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val intent = Intent(this, Login::class.java)
        startActivity(intent)

        supportActionBar?.title = "Previewer"

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainMenuViewModel(repository) as T
            }
        }).get(MainMenuViewModel::class.java)


    }

    fun observeLiveData() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                MainMenuState.Loading -> {

                }
                MainMenuState.EmptyInfo -> {

                }
                is MainMenuState.LoadInfo -> {

                }
                is MainMenuState.Error -> {

                }
            }
        })
    }
}