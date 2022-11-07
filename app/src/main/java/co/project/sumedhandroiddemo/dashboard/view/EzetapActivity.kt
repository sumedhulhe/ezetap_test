package co.project.sumedhandroiddemo.dashboard.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import co.project.sumedhandroiddemo.R
import co.project.sumedhandroiddemo.baseclasses.BaseActivity
import co.project.sumedhandroiddemo.dashboard.model.EzetapResponse
import co.project.sumedhandroiddemo.databinding.ActivityEzetapBinding
import co.project.sumedhandroiddemo.databinding.CustomButtonBinding
import co.project.sumedhandroiddemo.databinding.CustomEdittextBinding
import co.project.sumedhandroiddemo.databinding.CustomTextviewBinding
import co.project.sumedhandroiddemo.networkmodule.utils.Constants.ApiMethod.Companion.INTENT_DATA_KEY
import com.squareup.picasso.Picasso

class EzetapActivity : BaseActivity() {
    private lateinit var binding: ActivityEzetapBinding

    override fun onRetry(serviceID: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= DataBindingUtil.setContentView(this,R.layout.activity_ezetap)
   var data= intent.getParcelableExtra<EzetapResponse>(INTENT_DATA_KEY)
        supportActionBar.apply { title=data?.headingText }

        Picasso
                .get()
                .load(data?.logoUrl)
                .into(binding.imageView)
        data?.uidata?.forEach{
            when(it?.uitype){
                "label"->{inflateTextview(it)}
                "edittext"->{inflateEdittext(it)}
                "button"->{inflateButton(it)}
            }
            it?.uitype
        }

    }

    fun inflateEdittext(uiData: EzetapResponse.Uidata){
        var  mBinding = CustomEdittextBinding.inflate(LayoutInflater.from(this))
        mBinding.data=uiData
        binding.ezetapCl.addView(mBinding.root)
    }
    fun inflateTextview(uiData: EzetapResponse.Uidata){
        var  mBinding = CustomTextviewBinding.inflate(LayoutInflater.from(this))
        mBinding.data=uiData
        binding.ezetapCl.addView(mBinding.root)
    }
    fun inflateButton(uiData: EzetapResponse.Uidata){
        var  mBinding = CustomButtonBinding.inflate(LayoutInflater.from(this))
        mBinding.data=uiData
        binding.ezetapCl.addView(mBinding.root)
    }

}