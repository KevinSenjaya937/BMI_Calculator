package au.edu.curtin.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast


class InputBodyData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_body_data)

        val weightSeekBar = findViewById<SeekBar>(R.id.weightSeekBar)
        val weightBox = findViewById<TextView>(R.id.weightInputBox)
        val weightUnitText = findViewById<TextView>(R.id.weightUnitText)

        val heightSeekBar = findViewById<SeekBar>(R.id.heightSeekBar)
        val heightBox = findViewById<TextView>(R.id.heightInputBox)
        val heightUnitText = findViewById<TextView>(R.id.heightUnitText)

        val selectedUOM = findViewById<TextView>(R.id.selectedUOMText)
        val nextBtn = findViewById<Button>(R.id.nextBtn)

        val intent = intent
        val system = SelectUOMSystem.getSystem(intent)

        selectedUOM.text = setSystemSelected(system)
        weightUnitText.text = setWeightUOMSystem(system)
        heightUnitText.text = setHeightUOMSystem(system)

        weightSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weightBox.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        heightSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                heightBox.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        nextBtn.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "message", Toast.LENGTH_LONG)

            if (weightBox.text.isNotEmpty() && heightBox.text.isNotEmpty()) {
                val weight = weightBox.text.toString().toDouble()
                val height = heightBox.text.toString().toDouble()

                if ((weight in 0.0..300.0) && (height in 0.0..300.0)) {
                    val myIntent = Intent(this, BMIResults::class.java).also {
                        it.putExtra("system", system)
                        it.putExtra("weight", weight)
                        it.putExtra("height", height)
                    }
                    startActivity(myIntent)
                }
                else if (weight !in 0.0..300.0) {
                    toast.setText("Please enter a valid weight")
                    toast.show()
                }
                else if (height !in 0.0..300.0) {
                    toast.setText("Please enter a valid height")
                    toast.show()
                }
            }
            else {
                if (weightBox.text.isEmpty()) {
                    toast.setText("Please enter your weight to proceed")
                    toast.show()
                }
                else {
                    toast.setText("Please enter your height to proceed")
                    toast.show()
                }
            }
        }
    }
    companion object {

        fun getWeight(intent: Intent): Double {
            return intent.getDoubleExtra("weight", 0.0)
        }

        fun getHeight(intent: Intent): Double {
            return intent.getDoubleExtra("height", 0.0)
        }

        fun setWeightUOMSystem(system: Int) : String {
            return if (system == 0){
                "kg"
            } else {
                "lbs"
            }
        }

        fun setHeightUOMSystem(system: Int) : String {
            return if (system == 0){
                "cm"
            } else {
                "inches"
            }

        }

        fun setSystemSelected(system: Int) : String {
            return if (system == 0){
                "METRIC"
            } else {
                "IMPERIAL"
            }
        }
    }

}


