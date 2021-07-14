package com.android.upstox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.spacexclient.presentation.viewmodel.StocksListingViewModel
import com.android.upstox.databinding.ActivityMainBinding
import com.android.upstox.presentation.StockModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: StocksListingViewModel

    private lateinit var binding: ActivityMainBinding

    private val adapter = StockAdapter()

    private val linearLayoutManager = LinearLayoutManager(this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val filterUiStatusKey = "Filter Status"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        (applicationContext as UpStoxApplication).component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[StocksListingViewModel::class.java]

        viewModel.getAllRockets().observe(this) {
            it?.let {
                when (it) {
                    is UIState.Success -> showData(it.data)
                    is UIState.Error -> showError(it.message)
                    is UIState.Loading -> showLoading()
                }
            }
        }
        setUpList()
        setUpRefreshListener()
        setUpRetryButton()
    }


    private fun setUpRetryButton() {
        binding.button.setOnClickListener {
            viewModel.getStocks()
        }
    }

    private fun hideRefreshing() {
        binding.refreshRockets.isRefreshing = false
    }


    private fun setUpList() {
        binding.rocketList.adapter = adapter
        binding.rocketList.layoutManager = linearLayoutManager

    }

    private fun setUpRefreshListener() {
        binding.refreshRockets.setOnRefreshListener { viewModel.getStocks() }
    }


    private fun showRefreshing() {
        binding.refreshRockets.isRefreshing = true
    }

    private fun showRefreshingError(message: String) {
        binding.refreshRockets.isRefreshing = false

        val snackbar = Snackbar
            .make(binding.refreshRockets, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun showLoading() {
        binding.listData.isGone = true
        binding.errorLayout.isGone = true

        binding.loading.isVisible = true
    }

    private fun showError(message: String) {
        binding.listData.isGone = true
        binding.loading.isGone = true

        binding.errorLayout.isVisible = true
        binding.errorMessage.text = message
    }

    private fun showData(list: List<StockModel>) {
        binding.loading.isGone = true
        binding.errorLayout.isGone = true
        binding.listData.isVisible = true
        Timber.e("print list $list")

        adapter.submitList(list)
    }

}