package io.issc.android_tutorial_kt

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.widget.*
import android.widget.CompoundButton.INVISIBLE
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import io.issc.android_tutorial_kt.databinding.ActivityDemoBinding

class DemoActivity: Activity() {
    lateinit var btn:Button
    lateinit var binding: ActivityDemoBinding
    var cnt = 0
    lateinit var txt:TextView
    lateinit var img:ImageButton
    lateinit var switch:Switch
    lateinit var optList:RadioGroup
    lateinit var seekbar:SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        cnt = binding.cnt
        btn = binding.btnSelect
        txt = binding.txtCnt

        binding.edit.setOnKeyListener(object: OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                TODO("Not yet implemented")
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //TODO: 结束编辑
                } else {
                    //继续编辑
                    //
                }
            }

        })


        btn.setOnClickListener{
            var c = binding.cnt ?: 0
            c += 1
            binding.cnt = c
            txt.text = "" + c
            Log.i("main", "cnt = " + c)

            binding.edit.text
        }

        txt = binding.txtCnt
        switch = binding.switchBtn
        switch.setOnCheckedChangeListener{p0, checked->
                if (checked) {
                    Log.i("demoacti", "switch on")
                    img.visibility = View.VISIBLE
                }
                else
                {
                    Log.i("demoacti", "switch off")
                    img.visibility = View.INVISIBLE
                }
        }

        optList = binding.optList

        optList.setOnCheckedChangeListener { p0, btnId ->
            if (btnId == R.id.opt_1) {

            } else if (btnId == R.id.opt_2) {

            } else if (btnId == R.id.opt_3) {

            } else {
                //DO nothing
            }
        }

        seekbar = binding.seekbar

        seekbar.setOnSeekBarChangeListener(object:OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })

        img = binding.img

        var str = "hello"
        var bytes = str.toByteArray()
        img.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
//        img.setImageResource(R.drawable.ic_launcher_background)

        btn.setOnClickListener{
            cnt ++
        }
    }
}