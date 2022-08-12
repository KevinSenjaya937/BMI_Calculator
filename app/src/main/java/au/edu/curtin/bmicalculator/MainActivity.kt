package au.edu.curtin.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val yesBtn = findViewById<Button>(R.id.yesBtn)
        val noBtn = findViewById<Button>(R.id.noBtn)


        yesBtn.setOnClickListener {
            val intent = Intent(this, SelectUOMSystem::class.java)
            startActivity(intent)
        }

        noBtn.setOnClickListener {

            val snack = Snackbar.make(it, "You may not proceed", Snackbar.LENGTH_LONG)
            snack.show()
        }
    }
}