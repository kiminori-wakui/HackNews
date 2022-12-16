package jp.co.world.common.base

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.WindowManager


abstract class BottomSheetFragmentFullScreen : BottomSheetDialogFragment() {

    private var mBinding: ViewDataBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = bindView(view, savedInstanceState)
        mBinding?.lifecycleOwner = viewLifecycleOwner
        initConfig()
        initData()
        initView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding

    open fun initConfig() { }

    open fun initData() { }

    open fun initView() { }



    protected fun <T: Any?> LiveData<T>.observeKt(block:(T?) -> Unit) {
        this.observe(viewLifecycleOwner, Observer { data ->
            // block.invoke(data)
            block(data)
        })
    }

}