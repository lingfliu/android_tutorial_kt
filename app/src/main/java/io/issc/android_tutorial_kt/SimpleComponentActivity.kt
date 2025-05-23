package io.issc.android_tutorial_kt

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.google.android.material.chip.ChipGroup
import io.issc.android_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


class SimpleComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivitySimpleComponentBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    lateinit var seekBar:SeekBar
    lateinit var img:ImageView
    var clickCount = ClickCount(1)

    val taskResultList = ArrayList<Future<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn

        img = binding.img
        seekBar = binding.seekbar
        binding.clickCount = clickCount

        btn.setOnClickListener {
            clickCount.cnt = (clickCount.cnt.toInt()+1).toString()
        }

//        var imgData = ByteArray(10000)
//        img.setImageBitmap(BitmapFactory.decodeByteArray(imgData, 0, imgData.size))

        binding.checkbox.setOnClickListener {
            if (it.isActivated) {
            }
        }

        binding.toggle.setOnClickListener {
            if (it.isActivated){

            }
        }


        seekBar.setOnSeekBarChangeListener(object:OnSeekBarChangeListener{
            override fun onProgressChanged(v: SeekBar?, p: Int, fromUser: Boolean) {
//                Log.d("simple", progress.toString())
            }

            override fun onStartTrackingTouch(v: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(v: SeekBar?) {
//                TODO("Not yet implemented")
            }
        })

        binding.chipGrp.setOnCheckedChangeListener(object : ChipGroup.OnCheckedChangeListener{
            /**
             * Called when the checked chip has changed. When the selection is cleared, checkedId is [ ][View.NO_ID].
             *
             * @param group the group in which the checked chip has changed
             * @param checkedId the unique identifier of the newly checked chip
             */
            override fun onCheckedChanged(group: ChipGroup, checkedId: Int) {
            }
        })

        binding.radios.setOnCheckedChangeListener(object :OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, id: Int) {
                if (id == R.id.radio_1) {
                    Log.d("simple", "selected option 1")
                }
                else if (id == R.id.radio_2) {
                    Log.d("simple", "selected option 2")
                }
            }
        })
    }
}