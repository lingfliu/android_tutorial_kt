package io.issc.android_tutorial_kt

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.CompoundButton.INVISIBLE
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.issc.android_tutorial_kt.databinding.ActivityDemoBinding

class ComposeBaseActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainPage()
        }
    }

    @Composable
    fun MainPage() {
        Column (
            modifier = Modifier.clickable {
                onTouched()
            }
        ) {
            Text("1")
            Text("2")
            Text("3")
        }
    }

    fun onTouched() {

    }
}