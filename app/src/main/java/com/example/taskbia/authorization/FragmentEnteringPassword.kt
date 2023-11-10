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
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.databinding.FragmentEnteringPasswordBinding
import com.google.android.material.snackbar.Snackbar


class FragmentEnteringPassword : Fragment() {

    private var binding: FragmentEnteringPasswordBinding? = null
    lateinit var mController: NavController
    val passwordRight = "111111"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEnteringPasswordBinding.inflate(inflater).also { binding = it }.root

    private fun <T> views(block: FragmentEnteringPasswordBinding.() -> T): T? = binding?.block()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mController = findNavController()
        var passwordUser = ""
        views {
            button.isEnabled = false
            password.setOtpCompletionListener{
                Log.d("Actual Value", it)
                passwordUser = it.toString()
                password.requestFocus()
                Log.i("password_length_2","${passwordUser.count()} : $passwordUser ")

                if(passwordUser.count() == passwordRight.count() ){
                    button.isEnabled = true

                    button.background.setTint(ContextCompat.getColor(requireContext(),R.color.black))
                    button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    button.setOnClickListener {
                        if (passwordUser == passwordRight){
                            mController.navigate(R.id.action_fragmentEnteringPassword_to_mainActionFragment)
                            password.setText("")
                        }else{
                            constLayoutFrame.setBackgroundResource(R.drawable.shape_rounded_variants)
                            // constLayoutFrame.background.setTint(R.drawable.shape) //setTint(R.drawable.shape)
                            passwordError.visibility = View.VISIBLE
                            button.isEnabled = true
                            button.background.setTint(ContextCompat.getColor(requireContext(),R.color.Gray10))
                            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.Gray40))

                            val toast = Toast.makeText(context, "Правильный пароль: 111111", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()

                        }
                    }
                }
            }



        imageViewBack.setOnClickListener {
            mController.navigate(R.id.action_fragmentEnteringPassword_to_fragmentEnteringNumber)
        }



        }

    }

    override fun onStart() {
        super.onStart()
        activity?.window
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
       views { password.requestFocus() }
    }
}
