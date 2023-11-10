package com.example.taskbia.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.taskbia.R
import com.example.taskbia.databinding.ActivityAuthorizationBinding

class ActivityAuthorization : AppCompatActivity() {
    private var _binding: ActivityAuthorizationBinding? = null
    private lateinit var mNavConverter: NavController
    val mBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mNavConverter = Navigation.findNavController(this, R.id.nav_host_fragment)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}