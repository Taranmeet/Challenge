package com.paradox.challenge.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.use
import com.paradox.challenge.R

/**
 * Custom View class that provides support for icon, title and sub title
 */
class CustomizableGenericButton @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var view: View =
        LayoutInflater.from(context).inflate(R.layout.customizable_generic_button, this, true)
    private var title = view.findViewById<TextView>(R.id.tv_title)
    private var subTitle = view.findViewById<TextView>(R.id.tv_sub_title)
    private var icon = view.findViewById<ImageView>(R.id.iv_icon)

    init {
        context?.theme?.obtainStyledAttributes(attrs, R.styleable.CustomGenericButton, 0, 0)?.use {
            var size = convertPixelsToDp(
                it.getDimension(
                    R.styleable.CustomGenericButton_titleTextSize,
                    14f
                ), context
            )
            if (size > 0) {
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
            }

            title.setTextColor(it.getColor(R.styleable.CustomGenericButton_titleTextColor, 0))
            title.visibility = it.getInteger(R.styleable.CustomGenericButton_titleTextVisibility, 0)
            title.text = it.getString(R.styleable.CustomGenericButton_titleText)

            size = convertPixelsToDp(
                it.getDimension(
                    R.styleable.CustomGenericButton_subTitleTextSize,
                    14f
                ), context
            )
            if (size > 0) {
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
            }

            subTitle.setTextColor(it.getColor(R.styleable.CustomGenericButton_subTitleTextColor, 0))
            subTitle.visibility =
                it.getInteger(R.styleable.CustomGenericButton_subTitleTextVisibility, 0)

            icon.visibility =
                it.getInteger(R.styleable.CustomGenericButton_subTitleTextVisibility, 8)
            icon.setImageResource(
                it.getResourceId(
                    R.styleable.CustomGenericButton_iconSrc,
                    R.drawable.ic_launcher_foreground
                )
            )
            subTitle.text = it.getString(R.styleable.CustomGenericButton_subTitleText)

            isClickable = true

            isFocusable = true
        }
    }

    private fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}