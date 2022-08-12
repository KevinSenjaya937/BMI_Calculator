package au.edu.curtin.bmicalculator

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import kotlin.math.round

class BMIResults : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiresults)

        val intent = intent
        val system = SelectUOMSystem.getSystem(intent)
        val weight = InputBodyData.getWeight(intent)
        val height = InputBodyData.getHeight(intent)

        val weightText = findViewById<TextView>(R.id.weightText)
        val heightText = findViewById<TextView>(R.id.heightText)

        val weightUOMText = findViewById<TextView>(R.id.weightUOMText)
        val heightUOMText = findViewById<TextView>(R.id.heightOUMText)

        val bmiResultText = findViewById<TextView>(R.id.BMIResultText)
        val bmiStatusText = findViewById<TextView>(R.id.BMIStatusText)

        weightText.text = weight.toString()
        heightText.text = height.toString()

        weightUOMText.text = InputBodyData.setWeightUOMSystem(system)
        heightUOMText.text = InputBodyData.setHeightUOMSystem(system)

        val bmi = calculateBMI(weight, height/100.0)
        val bmiStatus = generateBMIStatus(bmi)

        bmiResultText.text = "%.2f".format(bmi)
        bmiStatusText.text = bmiStatus

        bmiStatusText.background = bmiStatusColour(bmiStatus)

    }

    companion object {
        fun calculateBMI(weight: Double, height: Double): Double {
            return round(weight / (height * height))
        }

        fun generateBMIStatus(bmi: Double) : String {
            return when  {
                bmi < 18.5          -> "Underweight"
                bmi in 18.5..24.9   -> "Healthy Weight"
                bmi in 25.0..29.9   -> "Overweight but not obese"
                bmi in 30.0..34.9   -> "Obese Class 1"
                bmi in 35.0..39.9   -> "Obese Class 2"
                else                -> "Obese Class 3"
            }
        }

        fun bmiStatusColour(bmiStatus: String) : Drawable {
            return when (bmiStatus) {
                "Underweight"               -> R.color.underweight.toDrawable()
                "Healthy Weight"            -> R.color.healthy_weight.toDrawable()
                "Overweight but not obese"  -> R.color.overweight_not_obese.toDrawable()
                "Obese Class 1"             -> R.color.obese_1.toDrawable()
                "Obese Class 2"             -> R.color.obese_2.toDrawable()
                else                        -> R.color.obese_3.toDrawable()
            }
        }
    }
}