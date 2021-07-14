package com.android.upstox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.upstox.databinding.StockItemBinding
import com.android.upstox.presentation.StockModel
import timber.log.Timber

class StockAdapter() : ListAdapter<StockModel, RocketViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(
            StockItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val currentItem: StockModel = getItem(position)
        val binding = holder.binding

        Timber.e(currentItem.toString())

        binding.Symbol.text = currentItem.name
        binding.ltp.text = String.format(currentItem.ltp.toString())
        binding.pl.text = String.format(currentItem.profitOrLoss.toString())
        binding.qty.text = currentItem.quantity.toString()
    }
}

class RocketViewHolder(val binding: StockItemBinding) : RecyclerView.ViewHolder(binding.root)

class DiffCallback : DiffUtil.ItemCallback<StockModel>() {
    override fun areItemsTheSame(oldItem: StockModel, newItem: StockModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: StockModel, newItem: StockModel): Boolean {
        return oldItem == newItem
    }

}
