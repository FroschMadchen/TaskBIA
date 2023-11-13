package com.example.taskbia.authorization.screen2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.taskbia.R
import com.example.taskbia.authorization.screen_menu.ChatFragment
import com.example.taskbia.authorization.screen_menu.GroupFragment
import com.example.taskbia.authorization.screen_menu.ProfileFragment
import com.example.taskbia.databinding.FragmentMainActionBinding
import com.google.android.material.navigation.NavigationView


class MainActionFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var binding: FragmentMainActionBinding? = null
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainActionBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding?.apply {
            if (savedInstanceState == null) {
                (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, InboxFragment()).commit()
            }

            binding?.bottomNavigation?.setOnItemSelectedListener { item ->

                when (item.itemId) {
                    R.id.task_m -> replaceFragment(InboxFragment())
                    R.id.group_m -> replaceFragment(GroupFragment())
                    R.id.chat -> replaceFragment(ChatFragment())
                    R.id.profile_m -> replaceFragment(ProfileFragment())
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}



