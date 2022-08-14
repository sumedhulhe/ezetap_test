package co.project.sumedhandroiddemo.dashboard.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import co.project.sumedhandroiddemo.R
import co.project.sumedhandroiddemo.baseclasses.BaseFragment
import co.project.sumedhandroiddemo.dashboard.viewmodel.MainViewModel
import co.project.sumedhandroiddemo.databinding.MainFragmentBinding
import co.project.sumedhandroiddemo.utils.Constants.ApiServiceId.Companion.CARD_API_SERVICE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {
    private lateinit var binding:MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding= DataBindingUtil.inflate(inflater,R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!viewModel.apiResponse.hasObservers()) observeApiResponse()
        if (!viewModel.getErrorResponse().hasObservers()) observeErrorResponse(
                viewModel,
                binding.cardsRecyclerview
        )
        showProgressDialog(getString(R.string.loading))
        viewModel.getTokenForCampaign()
    }
    private fun observeApiResponse() {
        viewModel.apiResponse.observe(viewLifecycleOwner) { apiResponse ->
             dismissProgressDialog()
            if (!apiResponse.isSuccess) {
                showToast(apiResponse.message)
            } else {
                when (apiResponse.serviceID) {
                    CARD_API_SERVICE_ID -> {
                      showToast("Success")
                    }
                }
            }
        }
    }
    override fun onRetry(serviceID: String) {

    }

}