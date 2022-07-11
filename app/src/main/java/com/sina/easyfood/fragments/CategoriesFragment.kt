package com.sina.easyfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sina.easyfood.R
import com.sina.easyfood.activities.MainActivity
import com.sina.easyfood.adapters.CategoriesAdapter
import com.sina.easyfood.databinding.FragmentCategoriesBinding
import com.sina.easyfood.videoModel.HomeViewModel

class CategoriesFragment : Fragment() {
    private lateinit var binding:FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var viewModel:HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =(activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecycleView()

        observeCategories()


    }


    private fun observeCategories() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun prepareRecycleView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
    }


}