package com.android.spacexclient.di

import android.app.Application
import com.android.upstox.MainActivity
import com.android.upstox.di.UseCaseModule
import com.android.upstox.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [UseCaseModule::class,SchedulerModule::class, ViewModelModule::class]
)
interface AppComponent
{
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}