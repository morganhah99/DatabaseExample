package com.logical.mydictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.logical.mydictionary.R
import com.logical.mydictionary.databinding.ItemView2Binding
import com.logical.mydictionary.databinding.ItemViewTrialBinding
import com.logical.mydictionary.model.ResultItemModel
import com.logical.mydictionary.model.ResultModel
import com.logical.mydictionary.util.DataDiffUtil


class MyAdapter_trial() : RecyclerView.Adapter<MyAdapter_trial.MyViewHolder>() {


private var words = emptyList<ResultModel>()

class MyViewHolder(private val binding: ItemView2Binding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(currentWord: ResultModel) {
        binding.model = currentWord
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemView2Binding.inflate(layoutInflater, parent, false)
            return MyViewHolder(binding)
        }
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder.from(parent)
}

override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentWord = words[position]
    holder.bind(currentWord)
}

override fun getItemCount(): Int {
  return  words.size
}

fun setData(newData: List<ResultModel>) {
    val recipesDiffUtil = DataDiffUtil(words, newData)
    val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
    words = newData
    diffUtilResult.dispatchUpdatesTo(this)
}
}