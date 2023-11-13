package com.example.taskbia.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.taskbia.R
import com.example.taskbia.databinding.ActivityAuthorizationBinding

class ActivityAuthorization : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorizationBinding
    private lateinit var mNavConverter: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mNavConverter = Navigation.findNavController(this, R.id.nav_host_fragment)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

}