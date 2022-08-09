package pl.pilichm.basiccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        val tvInput = findViewById<TextView>(R.id.tv_input)
        tvInput.append((view as Button).text)
    }

    fun onClear(view: View){
        val tvInput = findViewById<TextView>(R.id.tv_input)
        tvInput.text = ""
    }

    fun onDecimalPoint(view: View){
        val tvInput = findViewById<TextView>(R.id.tv_input)
        if (tvInput.text.isNotEmpty() && !tvInput.text.contains(".")){
            tvInput.append(".")
        }
    }

    fun onOperator(view: View){
        val tvInput = findViewById<TextView>(R.id.tv_input)
        if (!isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
        }
    }

    fun onEqual(view: View){
        val tvInput = findViewById<TextView>(R.id.tv_input)
        var value = tvInput.text.toString()
        var prefix = ""
        try {
            if (value.startsWith("-")){
                prefix = "-"
                value = value.substring(1)
            }

            when {
                value.contains("-") -> {
                    val splitValue = value.split("-")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvInput.text = removeZeroAfterDot((firstValue.toDouble() - secondValue.toDouble()).toString())
                }
                value.contains("*") -> {
                    val splitValue = value.split("*")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvInput.text = removeZeroAfterDot((firstValue.toDouble() * secondValue.toDouble()).toString())
                }
                value.contains("/") -> {
                    val splitValue = value.split("/")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvInput.text = removeZeroAfterDot((firstValue.toDouble() / secondValue.toDouble()).toString())
                }
                value.contains("+") -> {
                    val splitValue = value.split("+")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvInput.text = removeZeroAfterDot((firstValue.toDouble() + secondValue.toDouble()).toString())
                }
            }
        } catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        } else {
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }

    private fun removeZeroAfterDot(value: String): String{
        var result = value
        if (result.contains(".0")){
            result = result.substring(0, value.length-2)
        }
        return result
    }
}