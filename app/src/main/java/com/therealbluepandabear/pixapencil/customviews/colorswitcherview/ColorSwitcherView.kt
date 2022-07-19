package com.therealbluepandabear.pixapencil.customviews.colorswitcherview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Shader.TileMode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.therealbluepandabear.pixapencil.R

class ColorSwitcherView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var isPrimarySelected: Boolean = true

    private var primaryColor: Int = Color.BLACK
    private var secondaryColor: Int = Color.BLUE

    private var orientationLandscape: Boolean = false

    /** Primary paints **/
    private val borderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.darker_gray)
        strokeWidth = toDp(5)
        style = Paint.Style.STROKE
    }

    private val primaryPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val secondaryPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    /** Gradient paints (landscape false) **/
    private val gradientPaintShaderColors = intArrayOf(
        Color.parseColor("#ff0100"),
        Color.parseColor("#ffff00"),
        Color.parseColor("#00ff00"),
        Color.parseColor("#add8e6"),
        Color.parseColor("#0000ff"),
        Color.parseColor("#ff19fa"),
        Color.parseColor("#ff0210")
    )

    private val gradientPaintLandscapeFalseRectF = RectF(
        0f,
        toDp(55),
        toDp(100),
        toDp(70)
    )

    private val gradientPaintLandscapeFalseShader = LinearGradient(
        0f,
        toDp(50),
        toDp(100),
        toDp(50),
        gradientPaintShaderColors,
        null,
        TileMode.CLAMP
    )

    private val gradientPaintLandscapeFalse = Paint().apply {
        shader = gradientPaintLandscapeFalseShader
    }

    /** Gradient paints (landscape true) **/
    private val gradientPaintLandscapeTrueRectF = RectF(
        toDp(55),
        toDp(0),
        toDp(70),
        toDp(100)
    )

    private val gradientPaintLandscapeTrueShader = LinearGradient(
        toDp(55),
        toDp(0),
        toDp(55),
        toDp(100),
        gradientPaintShaderColors,
        null,
        TileMode.CLAMP
    )

    private val gradientPaintLandscapeTrue = Paint().apply {
        shader = gradientPaintLandscapeTrueShader
    }

    private val insetStroke = (borderPaint.strokeWidth / 2)

    private var onColorPickerTapped: () -> Unit = { }

    private fun toDp(px: Int): Float {
        return px * resources.displayMetrics.density
    }

    fun getIsPrimarySelected(): Boolean {
        return isPrimarySelected
    }

    fun setPrimaryColor(primaryColor: Int) {
        this.primaryColor = primaryColor
        invalidate()
    }

    fun setSecondaryColor(secondaryColor: Int) {
        this.secondaryColor = secondaryColor
        invalidate()
    }

    fun setOnColorPickerTapped(onColorPickerTapped: () -> Unit) {
        this.onColorPickerTapped = onColorPickerTapped
    }

    fun setIsPrimarySelected(isPrimarySelected: Boolean) {
        this.isPrimarySelected = isPrimarySelected
        invalidate()
    }

    // To be safe: we define the init block after all functions and variables
    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ColorSwitcherView,
            0, 0).apply {
            try {
                isPrimarySelected = getBoolean(R.styleable.ColorSwitcherView_isPrimarySelected, isPrimarySelected)

                primaryColor = getInt(R.styleable.ColorSwitcherView_primaryColor, primaryColor)
                secondaryColor = getInt(R.styleable.ColorSwitcherView_secondaryColor, secondaryColor)

                orientationLandscape = getBoolean(R.styleable.ColorSwitcherView_orientationLandscape, orientationLandscape)
            } finally {
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (!orientationLandscape) {
            toDp(100)
        } else {
            toDp(50 + 20)
        }

        val height = if (!orientationLandscape) {
            toDp(50 + 20)
        } else {
            toDp(100)
        }

        setMeasuredDimension(width.toInt(), height.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            primaryPaint.color = primaryColor
            secondaryPaint.color = secondaryColor

            if (!orientationLandscape) {
                drawRect(
                    0f,
                    0f,
                    toDp(50),
                    toDp(50),
                    primaryPaint)
                drawRect(
                    toDp(100),
                    0f,
                    toDp(50),
                    toDp(50),
                    secondaryPaint)

                drawRoundRect(
                    gradientPaintLandscapeFalseRectF,
                    10f,
                    10f,
                    gradientPaintLandscapeFalse)

                if (isPrimarySelected) {
                    drawRect(
                        0f + insetStroke,
                        0f + insetStroke,
                        toDp(50) - insetStroke,
                        toDp(50) - insetStroke,
                        borderPaint)
                } else if (!isPrimarySelected) {
                    drawRect(
                        toDp(50) + insetStroke,
                        0f + insetStroke,
                        toDp(100) - insetStroke,
                        toDp(50) - insetStroke,
                        borderPaint)
                }
            } else {
                drawRect(
                    0f,
                    0f,
                    toDp(50),
                    toDp(50),
                    primaryPaint)
                drawRect(
                    0f,
                    toDp(100),
                    toDp(50),
                    toDp(50),
                    secondaryPaint)

                drawRoundRect(
                    gradientPaintLandscapeTrueRectF,
                    10f,
                    10f,
                    gradientPaintLandscapeTrue)

                if (isPrimarySelected) {
                    drawRect(
                        0f + insetStroke,
                        0f + insetStroke,
                        toDp(50) - insetStroke,
                        toDp(50) - insetStroke,
                        borderPaint)
                } else if (!isPrimarySelected) {
                    drawRect(
                        0f + insetStroke,
                        toDp(100) - insetStroke,
                        toDp(50) - insetStroke,
                        toDp(50) + insetStroke,
                        borderPaint)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility") /** I will un-suppress in future commits **/
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val xOrY = if (!orientationLandscape) {
            event.x
        } else {
            event.y
        }

        val inverse = if (xOrY == event.x) {
            event.y
        } else {
            event.x
        }

        if ((xOrY in toDp(50)..toDp(100) - insetStroke) && isPrimarySelected
            && inverse !in toDp(50)..toDp(70)) {
            isPrimarySelected = false
            invalidate()
        } else if ((xOrY !in toDp(50)..toDp(100) - insetStroke) && !isPrimarySelected
                    && inverse !in toDp(50)..toDp(70)) {
            isPrimarySelected = true
            invalidate()
        } else if ((inverse in toDp(55)..toDp(70))) {
            onColorPickerTapped.invoke()
        }

        return true
    }
}