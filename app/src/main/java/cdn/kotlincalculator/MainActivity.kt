package cdn.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        num0.setOnClickListener { appendVal("0", false, true) }
        num1.setOnClickListener { appendVal("1", false, true) }
        num2.setOnClickListener { appendVal("2", false, true) }
        num3.setOnClickListener { appendVal("3", false, true) }
        num4.setOnClickListener { appendVal("4", false, true) }
        num5.setOnClickListener { appendVal("5", false, true) }
        num6.setOnClickListener { appendVal("6", false, true) }
        num7.setOnClickListener { appendVal("7", false, true) }
        num8.setOnClickListener { appendVal("8", false, true) }
        num9.setOnClickListener { appendVal("9", false, true) }
        numDot.setOnClickListener { appendVal(".", false, true) }
        //Operators
        clear_c.setOnClickListener { appendVal("", true, false) }
        actionDivide.setOnClickListener { appendVal(" / ", false, false) }
        actionMultiply.setOnClickListener { appendVal(" * ", false, false) }
        actionMinus.setOnClickListener { appendVal(" - ", false, false) }
        actionAdd.setOnClickListener { appendVal(" + ", false, false) }
//        plus_minus.setOnClickListener {
//            if(placeholder.text.toString().size() != )
//        }
//        sqrt.setOnClickListener{appendVal(" \u221a ", false)}

        actionBack.setOnClickListener {
            val expression = placeholder.text.toString()
            if (expression.isNotEmpty()) {
                placeholder.text = expression.substring(0, expression.length - 1)
            }


        }

        actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
                    answer.text = longResult.toString()
                } else
                    answer.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();

                Log.d("EXCEPTION", "Message: ${e.message}")
            }

        }

//        plus_minus.setOnClickListener {
//            val expression = answer.text.toString().toDouble()
//            if(expression > 0 )
//            {
//                answer.text.firs
//            }
//        }


    }



    fun appendVal(string: String, isClear: Boolean, isNumber: Boolean) {
        if (isClear) {
            placeholder.text = ""
            answer.text = ""
//            placeholder.append(string)
        } else {
            placeholder.append(string)
            if (isNumber)
            {
                answer.append(string)
            }
            else{
                answer.text = ""
            }

        }
    }

}
