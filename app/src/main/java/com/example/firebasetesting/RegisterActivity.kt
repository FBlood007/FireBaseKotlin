package com.example.firebasetesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var register : Button
   // private lateinit var signin : TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        name = findViewById(R.id.FullName)
        username = findViewById(R.id.EmailAddress1)
        password = findViewById(R.id.Password1)
        register = findViewById(R.id.Register)
        //signin = findViewById(R.id.sigin)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            val usernameinput = username.text.toString()
            val passinput =password.text.toString()
            registerUserInFirebase(usernameinput,passinput)

        }

    }

    private fun registerUserInFirebase(usernameinput: String, passinput: String) {
        auth.createUserWithEmailAndPassword(usernameinput,passinput).addOnCompleteListener {
            if (it.isSuccessful){


                Toast.makeText(this@RegisterActivity,"Register Successfully",Toast.LENGTH_LONG).show()
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@RegisterActivity,"$usernameinput is already registered",Toast.LENGTH_LONG).show()
            }
        }
    }

}