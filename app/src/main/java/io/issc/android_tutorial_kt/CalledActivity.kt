package io.issc.android_tutorial_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import io.issc.android_tutorial_kt.databinding.ActivityCalledBinding


/**
 * 被调用的Activity
 */
class CalledActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalledBinding

    lateinit var btnBack:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalledBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.extras?.let {
            val cnt = it.getInt("cnt")
            Toast.makeText(this, "cnt = $cnt", Toast.LENGTH_SHORT).show()
        }

//        var cnt = intent.getIntExtra("cnt", 0)
//        Toast.makeText(this, "cnt = $cnt", Toast.LENGTH_SHORT).show()

        btnBack = binding.btnBack


        //get request code
//        var requestCode = intent.getIntExtra("requestCode", 0)

        btnBack.setOnClickListener{
            var intent = Intent()
            intent.putExtra("cnt", 10)

            setResult(1, intent)

            finish()
        }

    }

}