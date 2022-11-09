package top.xherror.homework.Lab4


import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import top.xherror.homework.R

/**
 * Created by huangxin.2020 on 2022/10/30
 * @author huangxin.2020@bytedance.com
 */
class ProgressCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress = 1f   // 0..1f
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
    private var ringWidth = 5.dpFloat
    private var radius = 0f
    private var progressAnimator: ValueAnimator? = null
    private var animatedProgress = 0f

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressCircleView, defStyleAttr, 0)
        radius = ta.getDimension(R.styleable.ProgressCircleView_radius, 0f)
        ta.recycle()
        initRecF(radius)
    }

    private fun initRecF(radius : Float) {
        if (radius <= 0) {
            return
        }

        radiusRecF.apply {
            left = 0f
            top = 0f
            right = radius
            bottom = radius
        }
    }

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = ringWidth
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        circleRecF.apply {
            this.left = width/2 - radius - ringWidth / 2f
            this.top = height/2 - radius - ringWidth / 2f
            this.right = width/2 + radius + ringWidth / 2f
            this.bottom = height/2 + radius + ringWidth / 2f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(circleRecF,-90f,progress*360f,false,paint)
    }


    inline val Number.dpFloat
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(),
            Resources.getSystem().displayMetrics
        )

}