package com.socketfit.androidapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.room.Room
import com.socketfit.androidapp.R
import com.socketfit.androidapp.database.AppDatabase
import com.socketfit.androidapp.database.UserInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signUpButton = findViewById<AppCompatButton>(R.id.btnContinue)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Create a UserInfo object
                val userInfo = UserInfo(
                    email = email, firstName = firstName, lastName = lastName, password = password
                )

                // Create an Intent to start PersonnelInfoActivity and pass userInfo as an extra
                val intent = Intent(this, PersonnelInfoActivity::class.java)
                intent.putExtra("userInfo", userInfo)

                // Start PersonnelInfoActivity with the Intent
                startActivity(intent)
            }
        }
    }
}
