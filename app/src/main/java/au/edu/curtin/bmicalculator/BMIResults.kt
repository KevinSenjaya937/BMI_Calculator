package au.edu.curtin.bmicalculator

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import org.w3c.dom.Text
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

        val zoomInBtn = findViewById<Button>(R.id.increaseSizeBtn)
        val zoomOutBtn = findViewById<Button>(R.id.decreaseSizeBtn)
        val yourWeightText = findViewById<TextView>(R.id.yourWeightText)
        val yourHeightText = findViewById<TextView>(R.id.yourHeightText)
        val yourBMIText = findViewById<TextView>(R.id.yourBMIText)

        weightText.text = weight.toString()
        heightText.text = height.toString()

        weightUOMText.text = InputBodyData.setWeightUOMSystem(system)
        heightUOMText.text = InputBodyData.setHeightUOMSystem(system)

        val bmi = InputBodyData.calculateBMI(system, weight, height)
        val bmiStatus = generateBMIStatus(bmi)

        bmiResultText.text = "%.2f".format(bmi)
        bmiStatusText.text = bmiStatus

        bmiStatusText.background = bmiStatusColour(bmiStatus)

        fun decreaseSize(){
            weightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, weightText.textSize*0.9F)
            heightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightText.textSize*0.9F)
            weightUOMText.setTextSize(TypedValue.COMPLEX_UNIT_PX, weightUOMText.textSize*0.9F)
            heightUOMText.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightUOMText.textSize*0.9F)
            bmiResultText.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiResultText.textSize*0.9F)
            bmiStatusText.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiStatusText.textSize*0.9F)
            yourWeightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, yourWeightText.textSize*0.9F)
            yourHeightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, yourHeightText.textSize*0.9F)
            yourBMIText.setTextSize(TypedValue.COMPLEX_UNIT_PX, yourBMIText.textSize*0.9F)
        }

        fun increaseSize(){
            weightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, weightText.textSize*1.1F)
            heightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightText.textSize*1.1F)
            weightUOMText.setTextSize(TypedValue.COMPLEX_UNIT_PX, weightUOMText.textSize*1.1F)
            heightUOMText.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightUOMText.textSize*1.1F)
            bmiResultText.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiResultText.textSize*1.1F)
            bmiStatusText.setTextSize(TypedValue.COMPLEX_UNIT_PX, bmiStatusText.textSize*1.1F)
            yourWeightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, yourWeightText.textSize*1.1F)
            yourHeightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, yourHeightText.textSize*1.1F)
            yourBMIText.setTextSize(TypedValue.COMPLEX_UNIT_PX, yourBMIText.textSize*1.1F)
        }

        zoomOutBtn.setOnClickListener { decreaseSize() }

        zoomInBtn.setOnClickListener { increaseSize() }



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