package co.project.sumedhandroiddemo.dashboard.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import co.project.sumedhandroiddemo.R
import co.project.sumedhandroiddemo.baseclasses.BaseActivity
import co.project.sumedhandroiddemo.dashboard.model.EzetapResponse
import co.project.sumedhandroiddemo.dashboard.viewmodel.MainViewModel
import co.project.sumedhandroiddemo.networkmodule.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onRetry(serviceID: String) {
        showProgressDialog(getString(R.string.loading))
        viewModel.getCustomUi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (!viewModel.apiResponse.hasObservers()) observeApiResponse()
        if (!viewModel.getErrorResponse().hasObservers()) observeErrorResponse(
                viewModel,
               findViewById(R.id.container)
        )
        showProgressDialog(getString(R.string.loading))
        viewModel.getCustomUi()
    }
    private fun observeApiResponse() {
        viewModel.apiResponse.observe(this) { apiResponse ->
            dismissProgressDialog()
            if (!apiResponse.isSuccess) {
                showToast(apiResponse.message)
            } else {
                when (apiResponse.serviceID) {
                    Constants.ApiServiceId.CARD_API_SERVICE_ID -> {
                        var intent= Intent(this,EzetapActivity::class.java)
                        intent.putExtra(Constants.ApiMethod.INTENT_DATA_KEY,apiResponse.result as EzetapResponse)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}