package com.example.explorelayout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


const val RESULT = "result"

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.textView)
        val resultButton = findViewById<Button>(R.id.button)

        val text = "${intent.extras?.get(EMAIL).toString()} ${intent.extras?.get(PASS).toString()}"
        textView.text = text

        resultButton.setOnClickListener { setResult(text) }
    }

    private fun setResult(result: String) {
        val intent = Intent()
        intent.putExtra(RESULT, result)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}