package com.android.spacexclient.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.spacexclient.presentation.utils.SchedulerProvider
import com.android.upstox.UIState
import com.android.upstox.GetStocksUseCase
import com.android.upstox.presentation.StockModel
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class StocksListingViewModel @Inject constructor(
    val stocksUseCase: GetStocksUseCase,
    val schedulers: SchedulerProvider
) : ViewModel() {

    private val listOfRockets = MutableLiveData<UIState<List<StockModel>>>()

    fun getAllRockets(): LiveData<UIState<List<StockModel>>> {
        return listOfRockets
    }

    private val compositeDisposable = CompositeDisposable()

    init {
        getStocks()
    }

    fun getStocks() {
        listOfRockets.postValue(UIState.Loading)
        val disposable = stocksUseCase()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { handleSuccess(it) },
                { handleFailure(it) }
            )
        compositeDisposable.add(disposable)
    }

    private fun handleSuccess(it: Result<List<StockModel>>) {
        Timber.e("handle success $it")
        if (it.isSuccess)
            listOfRockets.postValue(UIState.Success(it.getOrDefault(listOf())))
        else
            listOfRockets.postValue(UIState.Error(it.exceptionOrNull()?.message?:""))
    }

    private fun handleFailure(it: Throwable) {
        Timber.i("handle Failure Called $it")
        listOfRockets.postValue(UIState.Error(it.message!!))
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}

