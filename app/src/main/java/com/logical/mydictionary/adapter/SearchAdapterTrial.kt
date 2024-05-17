package com.logical.mydictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.logical.mydictionary.databinding.ItemViewTrialBinding
import com.logical.mydictionary.model.ResultItemModel
import com.logical.mydictionary.model.ResultModel
import com.logical.mydictionary.util.DataDiffUtil

class SearchAdapterTrial : RecyclerView.Adapter<SearchAdapterTrial.MyViewHolder>() {
    private var words = emptyList<ResultItemModel>()

    class MyViewHolder(private val binding: ItemViewTrialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentWord: ResultItemModel, position: Int) {
            binding.model = currentWord
            binding.position = position
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewTrialBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentWord = words[0]
        holder.bind(currentWord, position)
    }

    override fun getItemCount(): Int {
        return if (!words.isNullOrEmpty())
            words[0].lfs.size
        else words.size
    }

    fun setData(newData: ResultModel) {
        val recipesDiffUtil = DataDiffUtil(words, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        words = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}