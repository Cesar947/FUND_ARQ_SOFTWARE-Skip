package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.simplife.skip.R

class PassengerListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_passenger_list, container, false)

        val startViajeButton = view.findViewById<Button>(R.id.startviaje_btn)

        startViajeButton.setOnClickListener {
            loadFragment(EnViajeFragment())
        }


        return view
    }

    fun loadFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction().also{
                fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragment_container_register, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


}