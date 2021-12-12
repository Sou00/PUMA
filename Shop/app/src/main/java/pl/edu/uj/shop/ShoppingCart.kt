package pl.edu.uj.shop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1 -> {
                    val labelka = findViewById<TextView>(R.id.labelka)
                    labelka.text = data?.getStringExtra("ProductName") ?: "Nie bangla!!!"
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}