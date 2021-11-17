package pl.edu.uj.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var number = "0"
    var isSecond = false
    var operator = ' '
    var isDouble = false
    var equalPressed = false
    var operatorPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: android.view.View) {
        val labelka = findViewById<TextView>(R.id.labelka)
        val button = view as Button

        if(labelka.text == "0" || labelka.text == "Error" || equalPressed || operatorPressed){
            labelka.text = button.text
            equalPressed = false
            operatorPressed = false
        }
        else{
            var s = labelka.text.toString()
            s += button.text
            labelka.text = s
        }
    }

    fun operatorClick(view: android.view.View) {
        val button = view as Button
        if(!operatorPressed){
            val labelka = findViewById<TextView>(R.id.labelka)
            if (!isSecond) {
                operator = button.text.toString()[0]
                number = labelka.text.toString()
            } else {
                equalClick(view)
                operator = button.text.toString()[0]
            }
            isSecond = true
            isDouble = false
            operatorPressed = true
        }
        else{
            operator = button.text.toString()[0]
        }
    }

    fun equalClick(view: android.view.View) {
        if (isSecond) {
            val labelka = findViewById<TextView>(R.id.labelka)
            var numberDouble = number.toDouble()
            var number1Double = labelka.text.toString().toDouble()
            if (operator == '/') {
                if(number1Double != 0.0) {
                    numberDouble /= number1Double
                    number = numberDouble.toString()
                    labelka.text = number
                }
                else{
                    labelka.text = "Error"
                }
            } else if (operator == 'X') {
                numberDouble *= number1Double
                number = numberDouble.toString()
                labelka.text = number
            } else if (operator == '-') {
                numberDouble -= number1Double
                number = numberDouble.toString()
                labelka.text = number
            } else if (operator == '+') {
                numberDouble += number1Double
                number = numberDouble.toString()
                labelka.text = number
            }
            if(numberDouble - numberDouble.toInt() == 0.0){
                labelka.text = numberDouble.toInt().toString()
            }
            isSecond = false
            isDouble = false
            equalPressed = true

        }
    }

    fun deleteClick(view: android.view.View) {
        val labelka = findViewById<TextView>(R.id.labelka)
        labelka.text = "0"
        isSecond = false
        isDouble = false
        operatorPressed = false
        equalPressed = false
        number = "0"
    }

    fun commaClick(view: android.view.View) {
        if(!isDouble){
            val labelka = findViewById<TextView>(R.id.labelka)
            if(!equalPressed && !operatorPressed){
                var s = labelka.text.toString()
                s += "."
                labelka.text = s
            }
            else{
                labelka.text = "0."
            }
            isDouble = true
            equalPressed = false
            operatorPressed = false
        }

    }
}