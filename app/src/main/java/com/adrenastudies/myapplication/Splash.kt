package com.adrenastudies.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adrenastudies.myapplication.utils.Functions
import java.util.*

class Splash : AppCompatActivity() {

    val ctx:Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        timer()
    }

    private fun timer() {
        val timer = Timer()
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread { Functions.changeActivity(ctx, MainActivity::class.java) }
            }
        }
        timer.schedule(task, 2000)
    }

}