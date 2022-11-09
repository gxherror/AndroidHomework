package top.xherror.homework.Lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import top.xherror.homework.R
import top.xherror.homework.databinding.ActivityLab4Binding
import kotlin.math.roundToInt


class Lab4Activity : AppCompatActivity() {
    private var _binding: ActivityLab4Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val uploadHandler by lazy {
        Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                UploadManager.UPLOAD_START -> toast("Upload Start")
                UploadManager.UPLOAD_FAIL -> toast("Upload Failed")
                UploadManager.UPLOAD_SUCCESS -> toast("Upload Success")
                UploadManager.UPLOAD_PROGRESS -> {
                    val progress = msg.obj as Float
                    binding.progressCircleView.progress = progress
                    val time : Int = (progress*60.0).toInt()
                    binding.showTime.text = time.toString()
                }
            }
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLab4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        UploadManager.setHandler(uploadHandler)
        var isStart = false
        binding.startCancelButton.setOnClickListener {
            if (!isStart){
                UploadManager.startUpload()
                binding.startCancelButton.text = "Cancel!"
                isStart = !isStart
            }else{
                UploadManager.cancelUpload()
                binding.startCancelButton.text = "Start!"
                isStart = !isStart
            }
        }
        var isPause = false
        binding.resumePauseButton.setOnClickListener {
            if (!isPause){
                UploadManager.pauseUpload()
                binding.resumePauseButton.text = "Resume!"
                isPause = !isPause
            }else{
                UploadManager.resumeUpload()
                binding.resumePauseButton.text = "Pause!"
                isPause = !isPause
            }
        }
    }

    private fun toast(text: String) {
        val toast = Toast.makeText(this,"", Toast.LENGTH_SHORT)
        toast.setText(text)
        toast.show()
    }
}