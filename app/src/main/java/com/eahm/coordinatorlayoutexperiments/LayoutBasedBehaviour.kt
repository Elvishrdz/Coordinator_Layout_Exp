package com.eahm.coordinatorlayoutexperiments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_layout_based_behaviour_coordinator.*

class LayoutBasedBehaviour : AppCompatActivity() {

    /* We will have two layout files.
       First layout file will be used to display the contents of LayoutBasedBehaviour Activity
       and the second layout file will be used to define CoordinatorLayout and handle the animations
       and transactions of the views in the Activity. So, following is the code for the
       content_main.xml file
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_based_behaviour_coordinator)

        /* Remember!
           Material design guidelines says: Dont move a fab button with a snackbar
           the snackbar should appear on top of the fab button!
         */
        setSupportActionBar(toolbarAnim)

        fabBehaviour.setOnClickListener{
            val snackbar = Snackbar.make(it, "Coordination this animation!", Snackbar.LENGTH_SHORT)
            snackbar.anchorView = fabBehaviour
            snackbar.show()
        }
    }
}