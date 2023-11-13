package com.example.taskbia.authorization

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.databinding.FragmentEnteringPasswordBinding
import com.google.android.material.snackbar.Snackbar

const val passwordCorrect: String = "111111"

class FragmentEnteringPassword : Fragment() {

    private var binding: FragmentEnteringPasswordBinding? = null
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEnteringPasswordBinding.inflate(inflater).also { binding = it }.root


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        var passwordUser = ""
        binding?.apply {
            button.isEnabled = false
            password.setOtpCompletionListener {
                Log.d("Actual Value", it)
                passwordUser = it.toString()
                password.requestFocus()
                Log.i("password_length_2", "${passwordUser.count()} : $passwordUser ")

                if (passwordUser.count() == passwordCorrect.count()) {
                    button.isEnabled = true

                    button.background.setTint(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black
                        )
                    )
                    button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    button.setOnClickListener {
                        if (passwordUser == passwordCorrect) {
                            navController.navigate(R.id.action_fragmentEnteringPassword_to_mainActionFragment)
                            password.setText("")
                        } else {
                            constLayoutFrame.setBackgroundResource(R.drawable.shape_rounded_variants)
                            passwordError.isVisible = true
                            button.isEnabled = true
                            button.background.setTint(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.Gray10
                                )
                            )
                            button.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.Gray40
                                )
                            )
                            Toast.makeText(
                                context,
                                "${context?.getString(R.string.password)} $passwordCorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            imageViewBack.setOnClickListener {
                navController.navigate(R.id.action_fragmentEnteringPassword_to_fragmentEnteringNumber)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        binding?.apply { password.requestFocus() }
    }
}
