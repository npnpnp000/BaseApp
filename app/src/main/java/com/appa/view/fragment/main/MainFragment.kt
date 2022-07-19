package com.appa.view.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appa.R
import com.appa.application.UserApplication
import com.appa.viewModel.ViewModelFactory
import com.appa.databinding.FragmentMainBinding
import com.appa.model.entities.Message
import com.appa.view.adapters.ListMessageAdapter
import com.appa.viewModel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var userApplication: UserApplication

    private lateinit var listAdapter: ListMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userApplication = requireActivity().application as UserApplication

        viewModelFactory = ViewModelFactory(userApplication.repository,null)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setList()

        observerTest()

        insertToRoomTest()

        return binding?.root
    }

    private fun setList() {
        listAdapter = ListMessageAdapter()
        binding?.listItem?.apply {
            layoutManager = LinearLayoutManager(requireContext())  // default layoutManager
            adapter = listAdapter
        }

    }

    private fun insertToRoomTest() {
        mainViewModel.apply {
            insertMessageToRoom(
                Message(
                    (0..100).random().toString()    // Add random number between 0 to 99
                )
            )
        }
    }

    private fun observerTest() {
        mainViewModel.allMessagesLiveData.observe(viewLifecycleOwner) { list ->
            Log.e("allMessagesLiveData.observe", list.toString())

            listAdapter.submitList(list)
        }
    }


}