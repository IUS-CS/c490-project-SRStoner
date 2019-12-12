package com.samst.tourguide

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(){
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {view ->
            signIn(view,"user@company.com", "pass")
        }

        var restaurant_btn = findViewById<Button>(R.id.restaurant_button_view)
        var attractions_btn = findViewById<Button>(R.id.attractions_button_view)
        var map_btn = findViewById<Button>(R.id.map_button_view)
        var about_btn = findViewById<Button>(R.id.about_button_view)

        restaurant_btn.setOnClickListener {
            val restaurant = Intent(this, RestaurantActivity::class.java)
            startActivity(restaurant)
        }//setting intent for onClicked button

        attractions_btn.setOnClickListener {
            val video = Intent(this, AttractionsActivity::class.java)
            startActivity(video)
        }

        map_btn.setOnClickListener {
            val map = Intent(this, MapActivity::class.java)
            startActivity(map)
        }

        about_btn.setOnClickListener {
            Toast.makeText(this, "Small little app about some Attractions and Restaurants in Paris!", Toast.LENGTH_LONG).show()
        }
    }
    // main on create

    fun signIn(view: View,email: String, password: String){
        showMessage(view,"Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                var intent = Intent(this, LoggedInActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)

            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })

    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

} // class
