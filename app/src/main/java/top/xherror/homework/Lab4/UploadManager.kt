package top.xherror.homework.Lab4

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

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

    fun startUpload(handler: Handler) {
        if (isUploading) return
        UploadThread(this, handler).start()
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
    private class UploadThread(private val callback: UploadCallback, private val handler: Handler): Thread() {

        private var progress = 0f   // 0..1f

        override fun run() {
            progress = 0f
            handler.sendMessage(Message.obtain(handler, UPLOAD_START))
            callback.onUploadStart()
            try {
                upload()
                handler.sendMessage(Message.obtain(handler, UPLOAD_SUCCESS))
                callback.onUploadSuccess()
            } catch (e: Exception) {
                handler.sendMessage(Message.obtain(handler, UPLOAD_FAIL))
                callback.onUploadFailed(e)
            }
        }

        /** 模拟下载过程，每300ms下载10% **/
        private fun upload() {
            while (progress < 1) {
                sleep(300)
                progress += 0.1f
                progress = 1f.coerceAtMost(progress)
                callback.onUploadProgress(progress)
                handler.sendMessage(Message.obtain(handler, UPLOAD_PROGRESS, progress))
            }
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