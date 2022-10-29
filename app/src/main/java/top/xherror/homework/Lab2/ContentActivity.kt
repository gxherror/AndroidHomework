package top.xherror.homework.Lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import top.xherror.homework.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name=intent.getStringExtra("param1")
        val num=intent.getIntExtra("param2",-1)
        binding.textView.text="这是从  item $num 进入的  Activity"
        binding.button.setOnClickListener {
            val text=binding.editText.text.toString()
            if (text!=""){
                val intent=Intent()
                intent.putExtra("param1",text)
                intent.putExtra("param2",num)
                setResult(RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this,"please input something!!!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}