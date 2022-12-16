package jp.co.world.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import jp.co.world.common.ktx.bindView
import jp.co.world.common.ktx.viewLifeCycleOwner


abstract class BaseActivity<ActBinding: ViewDataBinding>: AppCompatActivity{

    constructor(): super()

    constructor(@LayoutRes layout: Int) : super(layout)

    protected lateinit var mBinding: ActBinding

    var isOverrideBack = false

    var beforeProcess: (() -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().inject(this)


        preInitView()

        mBinding = bindView<ActBinding>(getLayoutRes())
        initView()
        initConfig()
        initData()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int


    open fun preInitView() { }

    open fun initView() { }

    open fun initConfig() { }

    open fun initData() { }

    override fun onResume() {
        super.onResume()
        isOverrideBack = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mBinding.isInitialized){
            mBinding.unbind()
        }
    }

    protected inline fun <T: Any?> LiveData<T>.observerKt(crossinline block:(T?) -> Unit) {
        this.observe(viewLifeCycleOwner, Observer { data ->
            block(data)
        })
    }

}