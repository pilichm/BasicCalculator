package pl.pilichm.basiccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import pl.pilichm.basiccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View){
        binding.tvInput.append((view as Button).text)
    }

    fun onClear(view: View){
        binding.tvInput.text = ""
    }

    fun onDecimalPoint(view: View){
        if (binding.tvInput.text.isNotEmpty() && !binding.tvInput.text.contains(".")){
            binding.tvInput.append(".")
        }
    }

    fun onOperator(view: View){
        if (!isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
        }
    }

    fun onEqual(view: View){
        var value = binding.tvInput.text.toString()
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

                    binding.tvInput.text = removeZeroAfterDot((firstValue.toDouble()
                            - secondValue.toDouble()).toString())
                }
                value.contains("*") -> {
                    val splitValue = value.split("*")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    binding.tvInput.text = removeZeroAfterDot((firstValue.toDouble()
                            * secondValue.toDouble()).toString())
                }
                value.contains("/") -> {
                    val splitValue = value.split("/")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    binding.tvInput.text = removeZeroAfterDot((firstValue.toDouble()
                            / secondValue.toDouble()).toString())
                }
                value.contains("+") -> {
                    val splitValue = value.split("+")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    binding.tvInput.text = removeZeroAfterDot((firstValue.toDouble()
                            + secondValue.toDouble()).toString())
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