package com.mobile.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MainRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MainViewModel
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MyViewModelFactory
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.RetrofitService

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.getAllProfileDetails()

        viewModel.profileData.observe(this) {
            System.out.println(">>>>>>>>>>Radha>>>>"+it)
        }

        viewModel._errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {

        })


    }
}