package com.amen.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var outputView: TextView? = null
    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputView = findViewById(R.id.Output_View)
    }

    fun onDigit(view: View){
        outputView?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        outputView?.text = " "
    }

    fun onOperator(view: View){
        outputView?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                outputView?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }




    @SuppressLint("SetTextI18n")
    fun onEqualTo(view: View){
        if(lastNumeric){
            var outputValue = outputView?.text.toString()
            var prefix = ""
            // TODO: From 10:45 onwards, prefix value
            try {
                if (outputValue.startsWith("-")){
                    prefix = "-"
                    outputValue = outputValue.substring(1)
                }
                if (outputValue.contains("-")){
                    val splitValue = outputValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    outputView?.text = removeDecimalDot((one.toDouble() - two.toDouble()).toString())
                }else if (outputValue.contains("+")){
                    val splitValue = outputValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    outputView?.text = removeDecimalDot((one.toDouble() + two.toDouble()).toString())
                }else if (outputValue.contains("*")){
                    val splitValue = outputValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    outputView?.text = removeDecimalDot((one.toDouble() * two.toDouble()).toString())
                }else if (outputValue.contains("/")){
                    val splitValue = outputValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    outputView?.text = removeDecimalDot((one.toDouble()/two.toDouble()).toString())
                }else if (outputValue.contains("%")){
                    val splitValue = outputValue.split("%")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    outputView?.text = removeDecimalDot((two.toDouble()/100).toString())
                }
            }
            catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            outputView?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun removeDecimalDot(result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

}