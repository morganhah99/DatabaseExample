package com.logical.mydictionary.ui.saved

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.logical.mydictionary.adapter.MyAdapter_trial
import com.logical.mydictionary.databinding.FragmentSavedBinding
import com.logical.mydictionary.model.ResultModel
import com.logical.mydictionary.util.Constants.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private lateinit var mAdapter: MyAdapter_trial
    private lateinit var savedViewModel: SavedViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    var resultMode = mutableListOf<ResultModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.x
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        savedViewModel =
            ViewModelProvider(this)[SavedViewModel::class.java]

        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        viewPager = binding.databasePager
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tabLayout = binding.tabLayout
        setAdapter()


        observeDataFromDB()


        return binding.root
    }

    private fun observeDataFromDB() {
        lifecycleScope.launch {
            savedViewModel.readSavedWords.observeOnce{
                if (it.isNotEmpty()) {
                    it.forEach {
                        resultMode.add(it.resultModel)
                    }
                    mAdapter.setData(emptyList())
                    Log.e("output", resultMode.size.toString())
                    mAdapter.setData(resultMode)
                }

            }
        }
    }

    private fun setAdapter() {
        mAdapter = MyAdapter_trial()
        // Log.e("output", it.resultModel.size.toString())
        viewPager.adapter = mAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}