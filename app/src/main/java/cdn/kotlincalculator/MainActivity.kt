package cdn.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.util.Vector
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var memory =  Vector<String>();

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

        actiondiv_x.setOnClickListener{
            try {
                //tutaj należy wprowadzić 1 podzielone przez input(ZROBIONE)

                placeholder.text = "1/${placeholder.text}"

            }
            catch(e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }

        sqrt.setOnClickListener{
            try {
                placeholder.text = "sqrt(${placeholder.text})"
            }
            catch(e: Exception) {
                android.widget.Toast.makeText(this, e.message, android.widget.Toast.LENGTH_SHORT).show()

                android.util.Log.d("EXCEPTION", "Message: sqrt error!")
            }
        }

        actionModulo.setOnClickListener{
            try {
                placeholder.text = "${placeholder.text}" + "%"
            }
            catch(e: Exception) {
                android.widget.Toast.makeText(this, e.message, android.widget.Toast.LENGTH_SHORT).show()

                android.util.Log.d("EXCEPTION", "Message: sqrt error!")
            }
        }

        plus_minus.setOnClickListener{
            try {
                if(Integer.parseInt(placeholder.text.toString()) > 0){
                    placeholder.text = "-${placeholder.text}"
                }
                else{
                    placeholder.text = "${placeholder.text.drop(1)}"
                }
            }
            catch(e: Exception) {
                android.widget.Toast.makeText(this, e.message, android.widget.Toast.LENGTH_SHORT).show()

                android.util.Log.d("EXCEPTION", "Message: +- error!")
            }
        }

        Memory_c.setOnClickListener {
            memory.clear()
        }

        Memory_r.setOnClickListener {
            try{
                placeholder.append(memory.lastElement())
                answer.text = memory.lastElement()
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
            catch (e: Exception){
                android.widget.Toast.makeText(this, e.message, android.widget.Toast.LENGTH_SHORT).show()

                android.util.Log.d("EXCEPTION", "Message: Memory is empty!")
            }
        }

        Memory_s.setOnClickListener {
            try{
                placeholder.append(memory.lastElement())
                answer.text = memory.lastElement()
            }
            catch (e: Exception){
                android.widget.Toast.makeText(this, e.message, android.widget.Toast.LENGTH_SHORT).show()

                android.util.Log.d("EXCEPTION", "Message: Memory is empty!")
            }
        }

        Memory_plus.setOnClickListener {
            val answer_text = answer.text
            memory.addElement(answer_text.toString())
        }

        Memory_minus.setOnClickListener {
            if (!memory.isEmpty()) {
                memory.removeAt(0)
            }
        }

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
