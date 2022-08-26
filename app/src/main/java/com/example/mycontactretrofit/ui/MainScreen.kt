package com.example.mycontactretrofit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mycontactretrofit.R
import com.example.mycontactretrofit.databinding.ScreenMainBinding
import com.example.mycontactretrofit.presenter.MainViewModel
import com.example.mycontactretrofit.presenter.impl.MainViewModelImpl
import com.example.mycontactretrofit.ui.adapter.ContactAdapter

class MainScreen : Fragment(R.layout.screen_main) {
    private val adapter = ContactAdapter()
    private val viewBinding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val navController by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.addLiveData.observe(this) {
            navController.navigate(R.id.action_mainScreen_to_addScreen)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.container.adapter = adapter
        setOnClickListener()
        subscribeDataObserver()

    }

    private fun setOnClickListener() {
        viewBinding.btnAdd.setOnClickListener {
            viewModel.add()
        }
        viewBinding.btnRefresh.setOnClickListener {
            viewModel.fetch()
        }
        viewBinding.btnAdd.setOnClickListener {
            viewModel.add()
        }
    }

    private fun subscribeDataObserver() {
        viewModel.listLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner){

        }

    }

}