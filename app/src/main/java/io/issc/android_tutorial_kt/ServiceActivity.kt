package io.issc.android_tutorial_kt

import android.animation.ObjectAnimator
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.media.session.MediaSession.Token
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.os.IBinder
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.issc.android_tutorial_kt.databinding.ActivityServiceBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Future
import kotlin.random.Random


/**
 * 自定义View示例
 */
class ServiceActivity : AppCompatActivity() {
    lateinit var binding: ActivityServiceBinding
    lateinit var btnService:Button

    val coroutineTester = CoroutineTester()
    val threadPool = ThreadPool.getInstance()

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnService = binding.btnService

        btnService.setOnClickListener { view ->
            val intent = Intent(this, MainService::class.java)
            intent.putExtra("data", "hello service")

            //启动服务
            startService(intent)

            //绑定服务
            val serviceConnection = object:ServiceConnection{
                override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                    val mainService = (binder as MainService.MainBinder).getService()

                    MainScope().launch {
                        mainService.task()
                    }

//                    coroutineTester.submit{
//                        val result = mainService.task()
//                    }

                }

                override fun onServiceDisconnected(p0: ComponentName?) {
                }
            }
//            val serviceConnection = object:ServiceConnection{
//                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                    val binder = service as MainService.MainBinder
//                    val mainService = binder.getService()
//
//                    //用线程池调用task
////                    threadPool.submit{
////                        val result = mainService.task()
////                    }
//
//                    //用协程调用task
//                    coroutineTester.submit{
//                        val result = mainService.task()
//                    }
//                }
//
//                override fun onServiceDisconnected(name: ComponentName?) {
//                    Log.i("MainServiceConnection", "onServiceDisconnected")
//                }
//            }

            bindService(intent, serviceConnection, BIND_AUTO_CREATE)
        }
    }
}