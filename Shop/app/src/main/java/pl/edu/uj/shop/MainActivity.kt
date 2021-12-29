package pl.edu.uj.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loginClicked(view: android.view.View) {
        val login = findViewById<EditText>(R.id.editTextPersonName)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
    fun registerClicked(view: android.view.View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}