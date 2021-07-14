package com.android.upstox.di

import com.android.spacexclient.di.RepositoryModule
import com.android.upstox.GetStocksUseCase
import com.android.upstox.GetStocksUseCaseImpl
import com.android.upstox.data.StockRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    @Singleton
    fun getStocksUseCase(repository: StockRepository):GetStocksUseCase {
        return GetStocksUseCaseImpl(repository)
    }
}