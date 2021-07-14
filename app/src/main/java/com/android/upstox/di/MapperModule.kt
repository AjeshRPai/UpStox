package com.android.upstox.di

import com.android.upstox.Mapper.StockDomainMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Singleton
    @Provides
    fun getMapper(): StockDomainMapper = StockDomainMapper()


}