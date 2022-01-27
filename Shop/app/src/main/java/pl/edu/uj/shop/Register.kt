package pl.edu.uj.shop

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.realm.Realm


class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var db: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        db = Realm.getDefaultInstance()

    }

    fun registerClicked(view: android.view.View) {
        val email = findViewById<EditText>(R.id.register_Email)
        val password = findViewById<EditText>(R.id.register_Password)
        if(email.text.toString().trim().isNotEmpty() && password.text.toString().trim().isNotEmpty()) {

            auth.createUserWithEmailAndPassword(
                email.text.trim().toString(),
                password.text.trim().toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val intent = Intent(this, MainMenu::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }
                }

        }

    }
    fun backClicked(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}