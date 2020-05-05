package com.adrenastudies.myapplication

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toolbar
import androidx.navigation.ui.setupActionBarWithNavController
import java.util.Observer


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.appbarlayout_tool_bar)
        toolbar.setTitle("This is toolbar.");
        setSupportActionBar(toolbar);*/

    }

}
