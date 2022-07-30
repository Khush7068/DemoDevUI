package com.android.demodevui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.android.demodevui.databinding.ActivityMainBinding
import com.android.demodevui.databinding.ActivityMoreControlBinding
import com.masoudss.lib.SeekBarOnProgressChanged
import com.masoudss.lib.WaveformSeekBar
import com.masoudss.lib.utils.Utils
import com.masoudss.lib.utils.WaveGravity
import java.util.*

class MoreControlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoreControlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreControlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.waveformSeekBar.apply {
            progress = 33.2F
            waveWidth = Utils.dp(this@MoreControlActivity, 5)
            waveGap = Utils.dp(this@MoreControlActivity, 2)
            waveMinHeight = Utils.dp(this@MoreControlActivity, 5)
            waveCornerRadius = Utils.dp(this@MoreControlActivity, 2)
            waveGravity = WaveGravity.BOTTOM
            waveBackgroundColor = ContextCompat.getColor(this@MoreControlActivity, R.color.white)
            waveProgressColor = ContextCompat.getColor(this@MoreControlActivity, R.color.green)
            sample = getDummyWaveSample()
            marker = getDummyMarkerSample()
            onProgressChanged = object : SeekBarOnProgressChanged {
                override fun onProgressChanged(
                    waveformSeekBar: WaveformSeekBar,
                    progress: Float,
                    fromUser: Boolean
                ) {
                    if (fromUser)
                        binding.waveProgress.progress = progress.toInt()
                }
            }
        }

        binding.waveWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.waveformSeekBar.waveWidth =
                    progress / 100F * Utils.dp(this@MoreControlActivity, 20)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.waveCornerRadius.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.waveformSeekBar.waveCornerRadius =
                    progress / 100F * Utils.dp(this@MoreControlActivity, 10)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.waveGap.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.waveformSeekBar.waveGap = progress / 100F * Utils.dp(this@MoreControlActivity, 10)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.waveProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.waveformSeekBar.progress = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.waveMaxProgress.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.waveProgress.max = progress
                binding.waveformSeekBar.maxProgress = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.visibleProgress.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.waveformSeekBar.visibleProgress = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.gravityRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = binding.gravityRadioGroup.findViewById(checkedId) as RadioButton
            val index = binding.gravityRadioGroup.indexOfChild(radioButton)
            binding.waveformSeekBar.waveGravity = when (index) {
                0 -> WaveGravity.TOP
                1 -> WaveGravity.CENTER
                else -> WaveGravity.BOTTOM
            }
        }

        binding.waveColorRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = binding.waveColorRadioGroup.findViewById(checkedId) as RadioButton
            val index = binding.waveColorRadioGroup.indexOfChild(radioButton)
            binding.waveformSeekBar.waveBackgroundColor = when (index) {
                0 -> ContextCompat.getColor(this, R.color.pink)
                1 -> ContextCompat.getColor(this, R.color.yellow)
                else -> ContextCompat.getColor(this, R.color.white)
            }
        }

        binding.progressColorRadioGroup.setOnCheckedChangeListener { _, checkedId ->

            val radioButton = binding.progressColorRadioGroup.findViewById(checkedId) as RadioButton
            val index = binding.progressColorRadioGroup.indexOfChild(radioButton)
            binding.waveformSeekBar.waveProgressColor = when (index) {
                0 -> ContextCompat.getColor(this, R.color.red)
                1 -> ContextCompat.getColor(this, R.color.blue)
                else -> ContextCompat.getColor(this, R.color.green)
            }
        }

    }

    private fun getDummyWaveSample(): IntArray {
        val data = IntArray(50)
        for (i in data.indices)
            data[i] = Random().nextInt(data.size)

        return data
    }

    private fun getDummyMarkerSample(): HashMap<Float, String> {
        val map = hashMapOf<Float, String>()
        map[binding.waveformSeekBar.maxProgress / 2] = "Love"
        map[binding.waveformSeekBar.maxProgress / 4] = "Khush"
        return map
    }
}