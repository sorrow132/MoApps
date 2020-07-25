package yuresko.moapps

import android.app.Application
import yuresko.moapps.di.AppComponent
import yuresko.moapps.di.DaggerAppComponent

class MoAppsApplication : Application() {

    val component: AppComponent = DaggerAppComponent
        .builder()
        .build()

}