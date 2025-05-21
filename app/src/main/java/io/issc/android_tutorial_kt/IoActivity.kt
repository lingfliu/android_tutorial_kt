package io.issc.android_tutorial_kt

import android.content.pm.PackageManager.PERMISSION_DENIED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.*
import androidx.room.Room
import io.issc.android_tutorial_kt.model.AppDatabase
import io.issc.android_tutorial_kt.model.UserInfo
import io.issc.android_tutorial_kt.databinding.ActivityIoBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Future
import kotlin.random.Random


/**
 * 自定义View示例
 */
class IoActivity : AppCompatActivity() {
    lateinit var binding: ActivityIoBinding
    lateinit var btn:Button


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i("ioacti", "onRequestPermissionsResult"+ " " + grantResults[0].toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn = binding.btn

        btn.setOnClickListener{

            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            }
            else {
                Log.i("ioacti", "permission granted")
            }

////
//            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
//                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
//            }

            //file r/w demo
            val extDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
            val file = File(extDir, "test3.json")
            if (!file.exists()) {
                file.createNewFile()
            }
            file.writeBytes("test 1234".toByteArray())
            file.writeText("test 1234", Charsets.UTF_8)
            val str = file.readLines()[0]
            Log.d("ioacti", ""+str)
        }


        //preferences demo
        val preferences = getSharedPreferences("io.issc.android_tutorial_kt", MODE_PRIVATE)

        val isFirstOpen = preferences.getBoolean("isFirstOpen", true)
        if (isFirstOpen) {
            preferences.edit().putBoolean("isFirstOpen", false).apply()
        }

        //room demo
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user_info")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        val userInfoDao = db.userInfoDao()

        MainScope().launch {
            userInfoDao.save(UserInfo(null, "admin", "admin", "admin"))
            userInfoDao.update(UserInfo(1, "admin", "admin", "admin"))

            val uinfo = userInfoDao.findByName("admin")
            userInfoDao.deleteById(uinfo.id!!)

            Log.d("IoActivity", "user info: " + uinfo.name)
        }.start()

        Thread({
            //尽量避免使用Thread
            //需要显式管理生命周期
            //阻塞发生，可能会造成泄漏
        }).start()

    }
}