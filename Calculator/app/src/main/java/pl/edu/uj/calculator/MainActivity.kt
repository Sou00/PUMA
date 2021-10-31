package pl.edu.uj.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: android.view.View) {
        val labelka = findViewById<TextView>(R.id.labelka)
        val button = view as Button

        if(labelka.text=="0"){
            labelka.text = button.text
        }
        else{
            var s =labelka.text.toString()
            s += button.text
        }
    }
}