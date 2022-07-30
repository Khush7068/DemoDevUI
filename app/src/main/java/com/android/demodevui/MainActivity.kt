package com.android.demodevui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.android.demodevui.databinding.ActivityMainBinding
import com.masoudss.lib.SeekBarOnProgressChanged
import com.masoudss.lib.WaveformSeekBar
import com.masoudss.lib.utils.Utils
import com.masoudss.lib.utils.WaveGravity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.waveformSeekBar.apply {
            progress = 33.2F
            waveWidth = Utils.dp(this@MainActivity, 5)
            waveGap = Utils.dp(this@MainActivity, 2)
            waveMinHeight = Utils.dp(this@MainActivity, 5)
            waveCornerRadius = Utils.dp(this@MainActivity, 2)
            waveGravity = WaveGravity.BOTTOM
            waveBackgroundColor = ContextCompat.getColor(this@MainActivity, R.color.white)
            waveProgressColor = ContextCompat.getColor(this@MainActivity, R.color.green)
            sample = getDummyWaveSample()
        }

        binding.btnMore.setOnClickListener {
            startActivity(Intent(this,MoreControlActivity::class.java))
        }
    }

    private fun getDummyWaveSample(): IntArray {
        val data = IntArray(90)
        for (i in data.indices)
            data[i] = Random().nextInt(data.size)

        return data
    }
}