package com.adrenastudies.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.adrenastudies.myapplication.MainActivity
import com.adrenastudies.myapplication.R
import com.adrenastudies.myapplication.utils.Functions
import java.util.*

class Splash : Fragment() {

    private fun timer(view:View) {
        val timer = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread { Navigation.findNavController(view).navigate(R.id.action_splash_to_list_user) }
            }
        }
        timer.schedule(task, 2000)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        timer(view)
        return view
    }
}