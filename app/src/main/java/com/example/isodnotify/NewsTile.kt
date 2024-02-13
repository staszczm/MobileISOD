package com.example.isodnotify

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * TODO: document your custom view class.
 */
class NewsTile @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0, defStyleRes: Int = 0): ConstraintLayout (context, attrs, defStyle, defStyleRes) {

    private var newsTitle: TextView

    var news: String = ""
        set(value) {
            field = value
            newsTitle.text = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.sample_news_tile, this, true)
        newsTitle = findViewById(R.id.newsTitle)

        attrs?.let {
            news = "siur"
        }
    }

}