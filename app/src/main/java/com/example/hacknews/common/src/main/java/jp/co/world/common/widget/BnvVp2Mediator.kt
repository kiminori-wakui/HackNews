package jp.co.world.common.widget

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView


class BnvVp2Mediator(private val bnv: BottomNavigationView,
                     private val vp2: ViewPager2,
                    private val config:((BottomNavigationView, ViewPager2) -> Unit) ? = null ) {

    private val map = mutableMapOf<MenuItem, Int>()

    init {
        bnv.menu.forEachIndexed { index, item ->
            map[item] = index
        }
    }

    fun attach(){
        config?.invoke(bnv, vp2)
        vp2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bnv.selectedItemId = bnv.menu.getItem(position).itemId
            }
        })

        bnv.setOnNavigationItemSelectedListener { item ->
            val sel = map[item]
            if(sel != null){
                vp2.setCurrentItem(sel, false)
            }
            // vp2.currentItem = map[item] ?: error("Bnv item id ${item.itemId} no responding ViewPager2")

            true
        }
    }

}