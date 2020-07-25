package yuresko.moapps.di.modules

import dagger.Module
import dagger.Provides
import yuresko.moapps.network.ApiService
import yuresko.moapps.repository.IRepository
import yuresko.moapps.repository.RepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(network: ApiService): IRepository {
        return RepositoryImpl(network)
    }
}