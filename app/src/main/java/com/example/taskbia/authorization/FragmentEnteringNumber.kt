package com.example.taskbia.authorization


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.databinding.FragmentEnteringNumberBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.Mask


class FragmentEnteringNumber : Fragment() {

    private var binding: FragmentEnteringNumberBinding? = null
    lateinit var mController: NavController
    var result = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEnteringNumberBinding.inflate(inflater).also { binding = it }.root

    private fun <T> views(block: FragmentEnteringNumberBinding.() -> T): T? = binding?.block()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mController = findNavController()
      /*  getActivity()?.getWindow()
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        */
        views {
            button.isEnabled = false
            editNumber.requestFocus()

            val editText = editNumber
            val listener = MaskedTextChangedListener(
                "+7 ([000]) [000] [00] [00]",
                true,
                editText,
                null,
                object : MaskedTextChangedListener.ValueListener {
                    @SuppressLint("ResourceAsColor")
                    override fun onTextChanged(
                        maskFilled: Boolean,
                        extractedValue: String,
                        formattedValue: String,
                        tailPlaceholder: String
                    ) {
                        result = editText.text.toString()
                        Log.i("getNumberFun_FEN", "${result.length} : $result")
                        if (result.count() >= 18) {
                            button.isEnabled = true
                            button.background.setTint(ContextCompat.getColor(requireContext(), R.color.black))
                            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                            button.setOnClickListener {
                                mController.navigate(R.id.action_fragmentEnteringNumber_to_fragmentEnteringPassword)
                                editText.setText("")
                            }
                        } else {
                            Log.i("_if_else_FEN", "${result.length}")
                        }
                    }
                }
            )
            editText.addTextChangedListener(listener)
            Log.i("check_", "${result.length}")
        }
    }
    override fun onStart() {
        super.onStart()
        activity?.window
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        views { editNumber.requestFocus() }
    }
}
