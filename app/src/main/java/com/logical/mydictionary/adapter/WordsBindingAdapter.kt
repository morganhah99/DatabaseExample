package com.logical.mydictionary.adapter

import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.logical.mydictionary.R
import com.logical.mydictionary.model.ResultItemModel
import com.logical.mydictionary.model.ResultModel
import com.logical.mydictionary.util.Constants


class WordsBindingAdapter {
    companion object {
        @BindingAdapter("onItemClickListener")
        @JvmStatic
        fun onItemClickListener(itemLayout: CardView, result: ResultItemModel) {
            itemLayout.setOnClickListener {
                try {
                    val args = Bundle()
                    args.putParcelable(Constants.ARGS_KEY, result)
                    itemLayout.findNavController().navigate(
                        R.id.action_navigation_search_to_navigation_details,
                        args
                    )
                } catch (e: Exception) {
                    Log.i("onItemClickListener", e.message.toString())
                }
            }
        }


        @BindingAdapter("onDescriptionClickListener")
        @JvmStatic
        fun onDescriptionClickListener(itemLayout: CardView, result: String) {
            itemLayout.setOnClickListener {
                try {
                    val args = Bundle()
                    args.putString(Constants.ARGS_KEY_DESCRIPTION, result)
                    itemLayout.findNavController().navigate(
                        R.id.action_savedFragment_to_descriptionFragment,
                        args
                    )
                } catch (e: Exception) {
                    Log.i("onClickListener", e.message.toString())
                }
            }
        }


    }
}