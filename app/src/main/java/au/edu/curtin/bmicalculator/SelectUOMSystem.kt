package au.edu.curtin.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectUOMSystem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_uomsystem)

        val metricBtn = findViewById<Button>(R.id.metricBtn)
        val imperialBtn = findViewById<Button>(R.id.imperialBtn)

        metricBtn.setOnClickListener {
            val system = 0
            val intent = Intent(this, InputBodyData::class.java).also {
                it.putExtra("system", system)
            }
            startActivity(intent)

        }

        imperialBtn.setOnClickListener {
            val system = 1
            val intent = Intent(this, InputBodyData::class.java).also {
                it.putExtra("system", system)
            }
            startActivity(intent)
        }


    }
    companion object {
        fun getSystem(intent : Intent): Int {
        return intent.getIntExtra("system", 2) }
    }


}