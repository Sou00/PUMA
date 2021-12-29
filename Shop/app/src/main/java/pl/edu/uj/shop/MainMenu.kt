package pl.edu.uj.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun productListClicked(view: android.view.View) {
        val intent = Intent(this, ProductList::class.java)
        startActivity(intent)
    }
    fun logoutClicked(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun faqClicked(view: android.view.View) {
        val intent = Intent(this, FAQList::class.java)
        startActivity(intent)
    }
    fun contactClicked(view: android.view.View) {
        val intent = Intent(this, ContactList::class.java)
        startActivity(intent)
    }
    fun cartClicked(view: android.view.View) {
        val intent = Intent(this, ShoppingCart::class.java)
        startActivity(intent)
    }
}