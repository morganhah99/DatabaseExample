package com.logical.mydictionary.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.logical.mydictionary.adapter.SearchAdapterTrial
import com.logical.mydictionary.databinding.FragmentSearchTrialBinding
import com.logical.mydictionary.util.Constants.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchTrialBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapterTrial

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        _binding = FragmentSearchTrialBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val searchView = binding.searchView
        recyclerView = binding.searchRecycleView
        searchAdapter = SearchAdapterTrial()
        recyclerView.adapter = searchAdapter
        searchView.setOnQueryTextListener(this)
        searchViewModel.longForm.observe(viewLifecycleOwner) {
            searchAdapter.setData(it)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null)
            getData(query)
        binding.searchView.setQuery("", false)
        binding.searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun getData(query: String) {
        lifecycleScope.launch {
            searchViewModel.readSavedWords.observeOnce { database ->
                var inDatabase = false
                if (database.isNotEmpty()) {
                    database.forEach { wordEntity ->
                        wordEntity.resultModel.forEach { resultItemModel ->
                            if (resultItemModel.sf == query.uppercase()) {
                                Toast.makeText(
                                    context,
                                    "data coming from database",
                                    Toast.LENGTH_SHORT
                                ).show()
                                searchAdapter.setData(wordEntity.resultModel)
                                inDatabase = true
                            }
                        }
                    }

                }
                if (!inDatabase) {
                    searchViewModel.getAcronym(query)
                    Toast.makeText(
                        context,
                        "data coming from api",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        }

    }

}