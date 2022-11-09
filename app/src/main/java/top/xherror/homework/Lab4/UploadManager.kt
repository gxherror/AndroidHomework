package top.xherror.homework.Lab4

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.sql.Types.NULL

/**
 * Created by huangxin.2020 on 2022/11/1
 * @author huangxin.2020@bytedance.com
 */
object UploadManager: UploadCallback {

    const val UPLOAD_START = 0
    const val UPLOAD_SUCCESS = 1
    const val UPLOAD_FAIL = 2
    const val UPLOAD_PROGRESS = 3

    private var isUploading = false
    private lateinit var  uploadThread :UploadThread
    private lateinit var  handler: Handler

    fun setHandler(handler: Handler){
        this.handler=handler
    }

    fun startUpload(progress: Float = 1f) {
        if (isUploading) return
        uploadThread = UploadThread(this, handler,progress)
        uploadThread.start()
    }

    fun pauseUpload(){
        uploadThread.interrupt()
    }

    fun cancelUpload(){
        uploadThread.interrupt()
        uploadThread.progress=0f

    }

    fun resumeUpload(){
        val saved = uploadThread.progress
        startUpload(saved)

    }

    override fun onUploadStart() {
        isUploading = true
        Log.d("UploadManager", "onUploadStart")
    }

    override fun onUploadSuccess() {
        isUploading = false
        Log.d("UploadManager", "onUploadSuccess")
    }

    override fun onUploadFailed(e: Exception) {
        isUploading = false
        Log.e("UploadManager", "onUploadFailed: $e")
    }

    override fun onUploadProgress(progress: Float) {
        Log.d("UploadManager", "onUploadProgress: $progress")
    }

    /**
     *
     * 这两种方式都很常见
     * @param callback  回调
     * @param handler   利用Handler发送消息
     **/
    class UploadThread(private val callback: UploadCallback, private val handler: Handler,var progress: Float = 1f): Thread() {
        override fun run() {
            //handler.sendMessage(Message.obtain(handler, UPLOAD_START))
            callback.onUploadStart()
            try {
                upload()
                handler.sendMessage(Message.obtain(handler, UPLOAD_SUCCESS))
                callback.onUploadSuccess()
            } catch (e: Exception) {
                //handler.sendMessage(Message.obtain(handler, UPLOAD_FAIL))
                callback.onUploadFailed(e)
            }
        }

        private fun upload() {
            while (progress > 0f) {
                sleep(1000)
                progress -= 1f/60f
                //progress = 1f.coerceAtMost(progress)
                callback.onUploadProgress(progress)
                sendMessage()
            }
        }

        fun sendMessage(){
            handler.sendMessage(Message.obtain(handler, UPLOAD_PROGRESS, progress))
        }

    }

}

/**
 * 回调也是一种很常见的写法
 */
interface UploadCallback {
    fun onUploadStart()
    fun onUploadSuccess()
    fun onUploadFailed(e: Exception)
    fun onUploadProgress(progress: Float)
}