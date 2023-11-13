package com.example.taskbia.authorization.screen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.authorization.screen2.adapter.FragAdapter
import com.example.taskbia.databinding.FragmentInboxBinding


class InboxFragment : Fragment() {
    private var _binding: FragmentInboxBinding? = null
    private lateinit var mController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInboxBinding.inflate(inflater).also { _binding = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mController = findNavController()

        val fragmentAdapter = FragAdapter((activity as AppCompatActivity).supportFragmentManager)
        fragmentAdapter.addFragment(
            AllTasksFragment(),
            context?.getString(R.string.task_incoming).toString()
        )
        fragmentAdapter.addFragment(
            AtWorkFragment(),
            context?.getString(R.string.task_user).toString()
        )

        _binding?.apply {
            viewPager.adapter = fragmentAdapter
            tablayout.setupWithViewPager(viewPager)
        }
    }
}