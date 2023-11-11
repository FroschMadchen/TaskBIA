package com.example.taskbia.authorization.screen2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.authorization.UserTasksFragment
import com.example.taskbia.authorization.screen2.adapter.FragAdapter
import com.example.taskbia.databinding.FragmentInboxBinding
import com.example.taskbia.databinding.FragmentMainActionBinding


class InboxFragment : Fragment() {
    private lateinit var mToolbar: Toolbar
    private var binding: FragmentInboxBinding? = null
    lateinit var mController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View = FragmentInboxBinding.inflate(inflater).also { binding = it }.root

    private fun <T> views(block: FragmentInboxBinding.() -> T): T? = binding?.block()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mController = findNavController()

        views {
          /*  mToolbar = binding!!.toolbar

            (activity as AppCompatActivity).setSupportActionBar(mToolbar)*/
            // val fragmentAdapter = activity?.let { FragAdapter(it.supportFragmentManager) }
            val fragmentAdapter = FragAdapter((activity as AppCompatActivity).supportFragmentManager)
            fragmentAdapter.addFragment(AllTasksFragment(), "Входящие")
            fragmentAdapter.addFragment(AtWorkFragment(), "В работе")


            viewPager.adapter = fragmentAdapter
            tablayout.setupWithViewPager(viewPager)

                //mToolbar.setupWithViewPager(viewPager)


        }
    }

}