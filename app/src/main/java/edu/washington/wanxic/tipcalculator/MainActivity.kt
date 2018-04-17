package edu.washington.wanxic.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.text.Editable
import android.text.TextWatcher
import android.R.attr.button


class MainActivity : AppCompatActivity() {
    private lateinit var spinner : Spinner
    private lateinit var button : Button
    private lateinit var amount : EditText
    private lateinit var view : TextView

    private var percents = arrayOf("10%", "15%", "18%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amount = findViewById(R.id.input)

        view = findViewById(R.id.output)

        button = findViewById(R.id.tip)
        button.isEnabled = false

        var tip = 0.15
        spinner = findViewById(R.id.spinner)
        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, percents)
        spinner.adapter = arrayAdapter
        spinner.setSelection(1)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(percents[p2] == "10%") {
                    tip = 0.1
                } else if(percents[p2] == "15%") {
                    tip = 0.15
                } else if(percents[p2] == "18%") {
                    tip = 0.18
                } else {
                    tip = 0.2
                }
            }
        }

        amount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                // TODO Auto-generated method stub
                button.isEnabled = true

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
                // TODO Auto-generated method stub

            }

            override fun afterTextChanged(s: Editable) {
                var num = s.toString()
                if(!num.contains("$")) {
                    amount.setText("$" + num)
                } else if(num.contains(".")) {
                    var decimal = num.split(".")
                    var digit = decimal[1]
                    if(digit.length > 2) {
                        amount.setText(decimal[0] + "." + digit.substring(0, 2))
                    }
                }
                amount.setSelection(amount.text.length)
            }
        })

        button.setOnClickListener {
            var get = amount.text.toString()
            var num = get.substring(1, get.length).toDouble() * tip
            var result = "%.2f".format(num)
            Toast.makeText(this, "$" + result, Toast.LENGTH_LONG).show()
        }

    }
}
