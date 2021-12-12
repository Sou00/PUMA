package pl.edu.uj.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ProductList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
    }

    fun backClicked(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun addClicked(view: android.view.View) {
        val buttonID = view.id.toString()
        var productName = ""

        if(buttonID == "imageButton"){
            productName = "Product 1"
        }
        else if(buttonID == "imageButton2"){
            productName = "Product 2"

        }

    //    if(buttonID == "imageButton"){
    //       var button = findViewById<ImageButton>(R.id.imageButton)
    //    }
    //    if(buttonID == "imageButton2"){
    //        var button = findViewById<ImageButton>(R.id.imageButton2)
    //    }
    //    if(buttonID == "imageButton3"){
    //        button = findViewById(R.id.imageButton3)
    //    }


        val button = findViewById<ImageButton>(R.id.buttonID)


        val intent = Intent(this, ShoppingCart::class.java).apply {
            putExtra("ProductName",productName)
        }
        startActivity(intent)
    }
}