package com.eahm.coordinatorlayoutexperiments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/*
    Coordinator Layout is used to manage the transactions and animation of various
    views present in an Activity. Before Coordinator Layout, Frame Layout was used,
    but using more than one views in Frame Layout results in overlapping of views
    over one another
*/
class MainActivity : AppCompatActivity() {

    /* What is Coordinator Layout?
    According to the official documentation of Android:
    CoordinatorLayout is a super-powered FrameLayout.
    At the Google I/O 2015, Google introduced Coordinator Layout to remove the difficulties
    of having more than one layouts in FrameLayout. Now, by using CoordinatorLayout you can
    see how views can interact with each other in a particular layout. CoordinatorLayout controls
    the animation and transactions of various child elements with one another. One question that
    might come in your mind is that how the CoordinatorLayout knows what to do with the child
    present in CoordinatorLayout? Let’s try to find the answer. */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Gradle Dependency
            implementation 'androidx.coordinatorlayout:coordinatorlayout:1.1.0'
        */

        btnGO.setOnClickListener{
            //startActivity(Intent(this, LayoutBasedBehaviour::class.java))
            //startActivity(Intent(this, ScrollBasedBehaviour::class.java))
            //startActivity(Intent(this, Coordination1::class.java))
            startActivity(Intent(this, CoordinationWithAnimation::class.java))
        }

    }

    private fun understandingCoordinatorLayout(){
       /* Behaviors
        Whenever a view is interacting with the other then it is done with the help of Behaviors.
        We create a particular behavior for a particular view and these behaviors are responsible
        for animations between the views. Some of the common Material Design behaviors are sliding
         drawers, swipe-dismissal, floating action button, a sticky button that stick on some other
         view. So, broadly these behaviors can be classified into two types:

            Layout-Based: Layout-Based behaviors are used to shift one view at some other place
                          when you perform a certain task. For example, create a project in Android
                          Studio with Basic Activity template. Now, run the application on your mobile.
                          You will find a Floating Action Button on the screen. Just press the
                          button and you will find a snackbar coming from the bottom of the screen.
                          At the same time, you will notice that the Floating Action Button has moved
                          upwards. After a few seconds the button will again come back to its original
                          position.

            Scroll-Based: Scroll-Based behaviors are the most common use of Coordinator Layout. Remember
                          the example of WhatsApp, that we discussed in the introduction section.
                          When you scroll the profile or contact page in WhatsApp, then you will
                          find that the profile image will be changed to ActionBar. So, these type
                          of behaviors is called Scroll-Based behaviors.
        */
    }
}