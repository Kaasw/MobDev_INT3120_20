package com.example.week1

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.week1.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val numPick = findViewById<NumberPicker>(R.id.numPick)
        numPick.minValue = 0
        numPick.maxValue = 1000
        val textView = findViewById<TextView>(R.id.textView3)
        val button = findViewById<Button>(R.id.button1)
        var amount = numPick.value
        val inputText = findViewById<EditText>(R.id.textInputEditText1)

        button.setOnClickListener {
            val field = inputText.text.toString().toIntOrNull()
            if (field == null) {
                amount = amount + numPick.value
                textView.text = "$$amount"
            } else {
                amount = amount + numPick.value + field!!
                textView.text = "$$amount"
            }


        }

    }



}