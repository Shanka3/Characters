package com.solera.characters.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solera.characters.view.fragments.CharactersFragment
import com.solera.characters.view.fragments.StaffFragment
import com.solera.characters.view.fragments.StudentsFragment

class TabLayoutAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CharactersFragment()
            }

            1 -> {
                StaffFragment()
            }

            2 -> {
                StudentsFragment()
            }

            else -> {
                Fragment()
            }
        }
    }

}