package top.xherror.homework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import top.xherror.homework.Lab1.Lab1Activity
import top.xherror.homework.Lab2.Lab2Activity
import top.xherror.homework.Lab3.Lab3Activity
import top.xherror.homework.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater) //FirstLayoutBinding bind to name
        setContentView(binding.root)

        binding.buttonLab1.setOnClickListener {
            startActivity(Intent(this,Lab1Activity::class.java))
        }

        binding.buttonLab2.setOnClickListener {
            startActivity(Intent(this, Lab2Activity::class.java))
        }

        binding.buttonLab3.setOnClickListener {
            startActivity(Intent(this, Lab3Activity::class.java))
        }

    }
}