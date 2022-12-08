package com.example.explorelayout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

const val LOGIN = "test@mail.com"
const val PASSWORD = "123"

const val EMAIL = "email"
const val PASS = "pass"

class MainActivity : AppCompatActivity() {

    private lateinit var appName: TextView

    //    Обязательно лаунчер сохраняем в переменную класса
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onActivityResult(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appName = findViewById(R.id.appName)
        val email = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.loginButton)
        button.setOnClickListener {
            validate(email.text.toString(), password.text.toString())
        }
    }

    private fun validate(email: String, pass: String) {
        if (email.isNotBlank() && pass.isNotBlank()) {
            if (email == LOGIN && pass == PASSWORD) {
                val bundle = Bundle()
                bundle.putString(EMAIL, email)
                bundle.putString(PASS, pass)
                goToNext(bundle)
            } else {
                Toast.makeText(this, "Incorrect email or password!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Email or password is empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToNext(bundle: Bundle) {
//        val intent = Intent(this, SecondActivity::class.java)
//        intent.putExtras(bundle)
//        startActivity(intent)

//        Запуск активити для получения результата
        openSomeActivityForResult(bundle)
    }

    //    Запуск активити для получения результата
    private fun openSomeActivityForResult(bundle: Bundle) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtras(bundle)
        launcher.launch(intent)
    }

    private fun onActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            intent?.let { resultAction(it) }
        }
    }

    private fun resultAction(intent: Intent) {
        val text = intent.extras?.get(RESULT).toString()
        appName.text = text
    }
}