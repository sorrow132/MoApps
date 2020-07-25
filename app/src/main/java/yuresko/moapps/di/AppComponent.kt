package yuresko.moapps.di

import dagger.Component
import yuresko.moapps.auth.Login
import yuresko.moapps.di.modules.NetworkModule
import yuresko.moapps.di.modules.RepositoryModule
import yuresko.moapps.mainview.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(provider: Login)
    fun inject(provider: MainActivity)

}