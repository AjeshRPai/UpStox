package com.android.spacexclient.di

import com.android.upstox.Mapper.StockDomainMapper
import com.android.upstox.data.StockRepository
import com.android.upstox.data.StocksApi
import com.android.upstox.di.MapperModule
import com.android.upstox.di.RetrofitModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class, MapperModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRocketRepo(stocksApi: StocksApi,
                          stockDomainMapper: StockDomainMapper)
       =  StockRepository(stocksApi, stockDomainMapper)


}