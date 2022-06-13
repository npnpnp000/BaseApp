package com.appa.view.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.appa.R
import com.appa.application.UserApplication
import com.appa.viewModel.ViewModelFactory
import com.appa.databinding.FragmentMainBinding
import com.appa.model.entities.Message
import com.appa.viewModel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var binding: FragmentMainBinding


    lateinit var mainViewModel: MainViewModel
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userApplication = requireActivity().application as UserApplication

        viewModelFactory = ViewModelFactory(userApplication.repository,null)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        observerTest()

//        mainViewModel.insertMessageToRoom(Message((0..100).random().toString()))

        return binding.root
    }

    private fun observerTest() {
        mainViewModel.allMessagesLiveData.observe(viewLifecycleOwner, { list ->
            Log.e("allMessagesLiveData.observe",list.toString())
        })
    }


}