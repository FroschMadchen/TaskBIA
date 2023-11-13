package com.example.taskbia.authorization


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.databinding.FragmentEnteringNumberBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener


private const val NUMBER: Int = 18

class FragmentEnteringNumber : Fragment() {

    private var _binding: FragmentEnteringNumberBinding? = null
    private lateinit var mController: NavController
    private var result: String = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEnteringNumberBinding.inflate(inflater).also { _binding = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mController = findNavController()

        _binding?.apply {
            button.isEnabled = false
            editNumber.requestFocus()

            val listener = MaskedTextChangedListener(
                "+7 ([000]) [000] [00] [00]",
                true,
                editNumber,
                null,
                object : MaskedTextChangedListener.ValueListener {
                    override fun onTextChanged(
                        maskFilled: Boolean,
                        extractedValue: String,
                        formattedValue: String,
                        tailPlaceholder: String
                    ) {
                        result = editNumber.text.toString()
                        Log.i("getNumberFun_FEN", "${result.length} : $result")
                        if (result.count() >= NUMBER) {
                            button.isEnabled = true
                            button.background.setTint(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.black
                                )
                            )
                            button.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )
                            button.setOnClickListener {
                                mController.navigate(R.id.action_fragmentEnteringNumber_to_fragmentEnteringPassword)
                                editNumber.setText("")
                            }
                        } else {
                            Log.i("_if_else_FEN", "${result.length}")
                        }
                    }
                }
            )
            editNumber.addTextChangedListener(listener)
            Log.i("check_", "${result.length}")
        }
    }

    override fun onStart() {
        super.onStart()

        requireActivity().window
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        _binding?.apply { editNumber.requestFocus() }
    }
}
