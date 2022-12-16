package jp.co.world.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BottomSheetFragment : BottomSheetDialogFragment() {

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

    override fun onDestroy() {
        super.onDestroy()

        mBinding?.unbind()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding

    open fun initConfig() { }

    open fun initView() { }

    open fun initData() { }

    protected fun <T: Any?> LiveData<T>.observeKt(block:(T?) -> Unit) {
        this.observe(viewLifecycleOwner, Observer { data ->
//            block.invoke(data)
            block(data)
        })
    }
}