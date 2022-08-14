package co.project.sumedhandroiddemo.dashboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.project.sumedhandroiddemo.R
import co.project.sumedhandroiddemo.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onRetry(serviceID: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit()
        }
    }
}