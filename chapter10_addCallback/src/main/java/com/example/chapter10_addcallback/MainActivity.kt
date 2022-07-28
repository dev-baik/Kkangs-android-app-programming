package com.example.chapter10_addcallback

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.chapter10_addcallback.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            showToast()
        }
    }

    fun showToast() {
        val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            toast.addCallback(
                object : Toast.Callback() {
                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("kkang", "toast.shown")
                    }

                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("kkang", "toast hidden")
                    }
                })
        }
        toast.show()
    }
}