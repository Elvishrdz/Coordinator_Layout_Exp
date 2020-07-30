package com.eahm.coordinatorlayoutexperiments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_coordination_with_animation.*
import kotlin.math.abs

class CoordinationWithAnimation : AppCompatActivity() {

    private val TAG = "CoordinationAnimation"

    /* appBarExpanded is a boolean flag to know when AppBarLayout is expanded or collapsed */
    private var appBarExpanded : Boolean = false

    /* collapsedMenu is a global variable of type Menu. It allows us to keep a copy of the original Menu.*/
    private var toolbarMenu : Menu? = null

    /* reference to the menu items */
    private var menuInfo : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordination_with_animation)

        /* Once the AppBar collapses, we will present the FAB’s action in the toolbar!
          To do this we’ll need a listener first. We have to listen to the when the AppBarLayout
          expands and collapses. To be precise, we need an OffsetChangedListener.

          If the AppBarLayout’s ‘verticalOffset’ is zero, then its fully expanded. So when the
          verticalOffset is almost equal to the fully expanded height, add the action to
          Toolbar’s menu.
        */
        setSupportActionBar(toolbarAnim) // Don't forget this!
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /* Using Palette API in the collapsing toolbar*/
        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.bg_dark_forest
        )

        /*
        The palette library attempts to extract the following six color profiles:
            Light Vibrant
            Vibrant
            Dark Vibrant
            Light Muted
            Muted
            Dark Muted
        */
        Palette.from(bitmap).generate { palette ->
            palette?.getVibrantColor(resources.getColor(R.color.purple)).let {
                if(it != null){
                    collapsingToolbarAnim.setStatusBarScrimColor(it)
                }
            }
            palette?.getLightMutedColor(resources.getColor(R.color.purple)).let {
                if(it != null){
                    collapsingToolbarAnim.setContentScrimColor(it)
                }
            }

        }

        appbarAnim.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                // Vertical offset == 0 indicates appBar is fully expanded.

                // verticalOffset returns negative values, so we wrap it with Math.abs(verticalOffset)
                if (abs(verticalOffset) > 200) {
                    //Log.i(TAG, "Appbar is collapsed!")
                    appBarExpanded = false

                    menuInfo?.isVisible = true

                    /* is called every time our AppBarLayout’s height crosses a threshold (200) */
                    invalidateOptionsMenu()
                } else {
                    //Log.i(TAG, "Appbar is expanded!")
                    appBarExpanded = true

                    menuInfo?.isVisible = false

                    /* is called every time our AppBarLayout’s height crosses a threshold (200) */
                    invalidateOptionsMenu()
                }
            }
        )
    }

    /* We’re already calling invalidateOptionsMenu() in the scroll listener.
    This will trigger the onPrepareOptionsMenu() method. Hence, we’ll add
    our dynamic Menu logic here.*/
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        /* After onPrepareOptionsMenu(), the onCreateOptionsMenu() is called.
        What do we need to do in the expanded state? The FAB becomes visible,
        which means we should hide the ‘Add’ action from Toolbar menu.*/

        if (toolbarMenu != null &&
            (!appBarExpanded || toolbarMenu!!.size() != 1)) {
            //collapsed

           /* toolbarMenu!!.add("Add")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)*/
            //toolbarMenu?.findItem(R.id.menu_info)?.isVisible = true

        }
        else {
            // Expanded
            if(false){} // to avoid the code correction
        }

        /* So now, when the FAB hides, its Action will be added to Toolbar Menu.
        When the AppBarLayout collapses and FAB hides, the ‘Add’ action becomes
        visible in the Toolbar Menu.*/
        return super.onPrepareOptionsMenu(toolbarMenu);
    }

    /* invalidateOptionsMenu() helps update our Toolbar Menu. But we need to tell
    our Menu when to add and remove our extra Action.*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /* Since onCreateOptionsMenu() inflates the original Menu again, we don’t
        have to worry about manually removing it.*/
        menuInflater.inflate(R.menu.menu_coordination_animation, menu)
        toolbarMenu = menu

        menuInfo = toolbarMenu?.findItem(R.id.menu_info)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)  {
            R.id.menu_info ->{
                Toast.makeText(this, "Information!", Toast.LENGTH_SHORT).show()
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

}