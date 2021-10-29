package com.tadfas.testproject

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.tadfas.testproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backgroundOne: ImageView = findViewById(R.id.background_one)
        val backgroundTwo: ImageView = findViewById(R.id.background_two)

        val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = 10000L
        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            val width: Int = backgroundOne.width
            val translationX = width * progress
            backgroundOne.translationX = translationX
            backgroundTwo.translationX = translationX - width
        }
        animator.start()

    }
}