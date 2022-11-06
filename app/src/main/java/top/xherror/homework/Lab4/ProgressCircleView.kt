package top.xherror.homework.Lab4


import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout

/**
 * Created by huangxin.2020 on 2022/10/30
 * @author huangxin.2020@bytedance.com
 */
class ProgressCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var progress = 0f   // 0..1f
        set(value) {
            field = when {
                value < 0 -> 0f
                value > 1f -> 1f
                else -> value
            }
            invalidate()
        }

    private var circleRecF = RectF()
    private var radiusRecF = RectF()
    private var ringWidth = 2.dpFloat
    private var progressAnimator: ValueAnimator? = null
    private var animatedProgress = 0f

    init {

    }

    private fun initRecF(w: Int, h: Int) {
        if (w <= 0 || h <= 0) {
            return
        }

        circleRecF.apply {
            left = ringWidth / 2f
            top = 0f + ringWidth / 2f
            right = w - ringWidth / 2f
            bottom = h - ringWidth / 2f
        }

        radiusRecF.apply {
            left = 0f
            top = 0f
            right = w.toFloat()
            bottom = h.toFloat()
        }
    }

    val paint = Paint().apply {
        strokeWidth = ringWidth
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }


    inline val Number.dpFloat
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(),
            Resources.getSystem().displayMetrics
        )

}