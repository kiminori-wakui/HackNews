package jp.co.world.common.ktx

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner


fun <T : ViewDataBinding> Activity.bindView(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout)
}
fun <T : ViewDataBinding> Activity.bindView(view: View): T? {
    return DataBindingUtil.bind<T>(view)
}

fun Activity.immediateStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
        window.insetsController?.let {
            it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        //            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
        //                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        //                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
    }
}

fun Activity.dismissKeyBoard(view: View){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

    val ComponentActivity.viewLifeCycleOwner: LifecycleOwner
    get() = this

    val Activity.context: Context
    get() = this
//endregion
