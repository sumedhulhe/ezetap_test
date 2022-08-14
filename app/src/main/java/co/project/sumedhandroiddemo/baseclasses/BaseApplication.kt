package co.project.sumedhandroiddemo.baseclasses

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.plugins.RxJavaPlugins


@HiltAndroidApp
class BaseApplication : Application() {



    companion object {
        lateinit var myInstance: BaseApplication

        fun getInstance(): BaseApplication {
            return myInstance
        }
    }

    override fun onCreate() {

        super.onCreate()
        myInstance = this
        RxJavaPlugins.setErrorHandler {}

    }


}