package jp.co.world.common.widget

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout


class TabVp2Mediator(private val tab: TabLayout,
                     private val vp2: ViewPager2,
                     private val config:((TabLayout, ViewPager2) -> Unit) ? = null ,
                    private val onSelect: (index: Int) -> Unit) {

    private val map = mutableMapOf<TabLayout.Tab?, Int>()

    private val pageChangerCallBack = object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            tab.getTabAt(position)?.select()
        }
    }

    init {
        val tabCnt = tab.tabCount

        for(ii in 0..tabCnt){
            val item = tab.getTabAt(ii)
            map[item] = ii
        }
    }

    fun attach(){
        config?.invoke(tab, vp2)
        vp2.registerOnPageChangeCallback(pageChangerCallBack)
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(item: TabLayout.Tab?) {

                val index = item?.position ?: 0

                vp2.currentItem = map[item] ?: error("tab position $index no pager")
                onSelect(index)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // tab?.getIcon()?.setColorFilter(Color.parseColor("#ec7e8c"), PorterDuff.Mode.SRC_IN);
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {   }

        } )
    }

    fun detach() {
        vp2.unregisterOnPageChangeCallback(pageChangerCallBack)
    }

}