package com.socketfit.androidapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.room.Room
import com.socketfit.androidapp.R
import com.socketfit.androidapp.database.AppDatabase
import com.socketfit.androidapp.database.UserInfo
import com.socketfit.androidapp.util.SavePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PersonnelInfoActivity : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase // Declare the database instance at the class level
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_info)
        val signUpButton = findViewById<Button>(R.id.btnSignup)
        val heightEditText = findViewById<EditText>(R.id.heightEditText)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val amputationEditText = findViewById<EditText>(R.id.amputationEditText)
        val prostheticEditText = findViewById<EditText>(R.id.prostheticEditText)
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "SocketFit" // Replace with your actual database name
        ).build()
        // Retrieve userInfo from the Intent extras
        val userInfo = intent.getSerializableExtra("userInfo") as? UserInfo

        signUpButton.setOnClickListener {
            val heightCm = heightEditText.text.toString()
            val weightKg = weightEditText.text.toString()
            val amputationType = amputationEditText.text.toString()
            val prostheticType = prostheticEditText.text.toString()

            // Validate that all fields are necessary and not empty
            if (heightCm.isEmpty() || weightKg.isEmpty() || amputationType.isEmpty() || prostheticType.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // All fields are filled, proceed with updating and saving data

                // Update userInfo with the values from the EditText fields
                userInfo?.heightCm = heightCm
                userInfo?.weightKg = weightKg
                userInfo?.amputationType = amputationType
                userInfo?.prostheticType = prostheticType

                // Save the updated userInfo to the Room database
                GlobalScope.launch {
                    val intent = Intent(this@PersonnelInfoActivity, MainActivity::class.java)
                    userInfo?.let { it1 -> appDatabase.userInfoDao().insertUserInfo(it1) }
                    SavePreferences(this@PersonnelInfoActivity).setEmail(userInfo?.email)
                    startActivity(intent)
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@PersonnelInfoActivity,
                            "User information saved to database",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            }
        }

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

    }
}




