package com.logical.mydictionary.ui.description

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.logical.mydictionary.R
import com.logical.mydictionary.adapter.MyAdapter_trial
import com.logical.mydictionary.adapter.SearchAdapterTrial
import com.logical.mydictionary.databinding.FragmentDescriptionBinding
import com.logical.mydictionary.databinding.FragmentSearchTrialBinding
import com.logical.mydictionary.model.ResultItemModel
import com.logical.mydictionary.ui.search.SearchViewModel
import com.logical.mydictionary.util.Constants
import com.logical.mydictionary.util.Constants.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var descriptionAdapter: SearchAdapterTrial

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        val root: View = binding.root
        recyclerView=binding.searchRecycleView
        descriptionAdapter= SearchAdapterTrial()
        recyclerView.adapter=descriptionAdapter
        Log.e("output","inside Description")

        val args = arguments
        val itemName: String? = args?.getString(Constants.ARGS_KEY_DESCRIPTION)
        getData(itemName!!)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData(query: String) {
        lifecycleScope.launch {
            searchViewModel.readSavedWords.observeOnce { database ->
                if (database.isNotEmpty()) {
                    database.forEach {
                        val shortForm= it.resultModel[0].sf
                        if(shortForm==query)
                            descriptionAdapter.setData(it.resultModel)
                        Log.e("output","sf    $shortForm")
                        Log.e("output","query    $query")

                    }

                }

            }
        }
    }
}