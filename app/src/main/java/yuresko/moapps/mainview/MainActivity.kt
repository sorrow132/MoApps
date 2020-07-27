package yuresko.moapps.mainview

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_activity.*
import yuresko.moapps.MoAppsApplication
import yuresko.moapps.R
import yuresko.moapps.adapter.MenuListAdapter
import yuresko.moapps.core.base.BaseActivity
import yuresko.moapps.mainview.model.MainMenuState
import yuresko.moapps.mainview.viewmodel.IMainMenuViewModel
import yuresko.moapps.mainview.viewmodel.MainMenuViewModel
import yuresko.moapps.repository.IRepository
import yuresko.moapps.utils.visibleOrGone
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private lateinit var recycler: RecyclerView
    private val adapter = MenuListAdapter(this)

    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var viewModel: IMainMenuViewModel

    @Inject
    lateinit var repository: IRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MoAppsApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        recycler = findViewById(R.id.recyclerViewMain)

        supportActionBar?.title = "Previewer"

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainMenuViewModel(repository) as T
            }
        }).get(MainMenuViewModel::class.java)

        recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                MainMenuState.Loading -> {
                    mainProgressBar.visibleOrGone(true)
                    textViewProgress.visibleOrGone(true)
                    recyclerViewMain.visibleOrGone(false)
                }
                MainMenuState.EmptyInfo -> {
                    Toast.makeText(this, "Список ваших приложений пуст", Toast.LENGTH_SHORT).show()
                }
                is MainMenuState.LoadInfo -> {
                    mainProgressBar.visibleOrGone(false)
                    textViewProgress.visibleOrGone(false)
                    recyclerViewMain.visibleOrGone(true)

                    recycler.adapter = adapter
                    adapter.submitList(state.appsInfo)
                }
                is MainMenuState.Error -> {
                    Toast.makeText(this, state.error.toString(), Toast.LENGTH_SHORT).show()
                    mainProgressBar.visibleOrGone(false)
                    textViewProgress.visibleOrGone(false)
                    recyclerViewMain.visibleOrGone(false)
                }
            }
        })
    }
}