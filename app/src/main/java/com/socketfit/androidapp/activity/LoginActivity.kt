package com.socketfit.androidapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.socketfit.androidapp.R
import com.socketfit.androidapp.database.AppDatabase
import com.socketfit.androidapp.util.SavePreferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase // Declare the database instance at the class level
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var signUpTextView = findViewById<TextView>(R.id.signUpTextView)
        signUpTextView.setOnClickListener {
            var intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "SocketFit" // Replace with your actual database name
        ).build()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform a Room database query to check if the email and password match
            GlobalScope.launch {
                val userInfo = appDatabase.userInfoDao().getUserByEmailAndPassword(email, password)

                if (userInfo != null) {
                    SavePreferences(this@LoginActivity).setEmail(email)
                    // Login successful: email and password match a user
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Login failed: show an error message (e.g., with a Toast)
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}