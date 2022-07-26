package com.example.chapter08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.chapter08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 뒤로가기 버튼을 누른 시각을 저장하는 속성
    var initTime = 0L

    // 멈춘 시각을 저장하는 속성
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            startButton.setOnClickListener {
                chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                chronometer.start()
                stopButton.isEnabled = true
                resetButton.isEnabled = true
                startButton.isEnabled = false
            }

            stopButton.setOnClickListener {
                pauseTime = chronometer.base - SystemClock.elapsedRealtime()
                binding.chronometer.stop()
                stopButton.isEnabled = false
                resetButton.isEnabled = true
                startButton.isEnabled = true
            }

            resetButton.setOnClickListener {
                pauseTime = 0L
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.stop()
                stopButton.isEnabled = false
                resetButton.isEnabled = false
                startButton.isEnabled = true
            }
        }
    }

    // 뒤로가기 버튼 이벤트 핸들러
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 뒤로가기 버튼 눌렀을 때 처리
        if (keyCode === KeyEvent.KEYCODE_BACK) {
            // 뒤로가기 버튼을 처음 눌렀거나 누른 지 3초가 지났을 때 처리
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}