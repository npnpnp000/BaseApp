package com.appa.view.fragment.splash

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.appa.R
import com.appa.application.UserApplication
import com.appa.databinding.FragmentSplashBinding
import com.appa.viewModel.MainViewModel
import com.appa.viewModel.SplashViewModel
import com.appa.viewModel.ViewModelFactory
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

import com.appa.view.activity.MainActivity


class SplashFragment() : Fragment(R.layout.fragment_splash) {

    lateinit var binding: FragmentSplashBinding

    lateinit var splashViewModel: SplashViewModel
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userApplication = requireActivity().application as UserApplication
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        viewModelFactory = ViewModelFactory(userApplication.repository, connectivityManager)
        splashViewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        observerTest()

        splashViewModel.checkInternetConnection()

        observerInternetConnection()

//        splashViewModel.getRequest()


        return binding.root
    }

    private fun observerInternetConnection() {
        splashViewModel.errorLiveData.observe(viewLifecycleOwner, { error ->
            Log.e("errorLiveData.observe", error.toString())

            if (error == null) {
                Handler(Looper.myLooper()!!).postDelayed({
                    val action =
                        SplashFragmentDirections.actionSplashFragmentToMainFragment()   // set action navigate to main fragment
                    findNavController().navigate(action)                                 // navigate using the action
                }, 3000)
            } else {

                startErrorDialog(error)
            }
        })
    }

    private fun startErrorDialog(error: String) {
        val alertDialog: android.app.AlertDialog = android.app.AlertDialog.Builder(context).create()
        alertDialog.setTitle(getString(R.string.error))
        alertDialog.setMessage(error)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok)
        ) { dialog, which ->
                dialog.dismiss()
                activity?.finish()
        }
        alertDialog.show()
    }

    private fun observerTest() {
        splashViewModel.allCommentsLiveData.observe(viewLifecycleOwner, { list ->
            Log.e("allCommentsLiveData.observe", list.toString())

            val action =
                SplashFragmentDirections.actionSplashFragmentToMainFragment()   // set action navigate to main fragment
            findNavController().navigate(action)                                 // navigate using the action        })
        })
    }
}
