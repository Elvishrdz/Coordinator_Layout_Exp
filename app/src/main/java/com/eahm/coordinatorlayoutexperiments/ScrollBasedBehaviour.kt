package com.eahm.coordinatorlayoutexperiments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ScrollBasedBehaviour : AppCompatActivity() {

    /* Scroll-Based behaviors are mostly used behaviors. Here we scroll one view to overlap
    on to the other view. One common example of Scroll-Based behavior is Collapsing Toolbar Activity */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_based_behaviour)
    }



}