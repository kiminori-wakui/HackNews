package jp.co.world.common.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0004B\u0011\b\u0016\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u001a\u001a\u00020\u0006H\'J\b\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016J\u0012\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\nH\u0014J\b\u0010\"\u001a\u00020\nH\u0014J\b\u0010#\u001a\u00020\nH\u0016J4\u0010$\u001a\u00020\n\"\u0004\b\u0001\u0010%*\b\u0012\u0004\u0012\u0002H%0&2\u0016\b\u0004\u0010\'\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H%\u0012\u0004\u0012\u00020\n0(H\u0084\b\u00f8\u0001\u0000R\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006)"}, d2 = {"Ljp/co/world/common/base/BaseActivity;", "ActBinding", "Landroidx/databinding/ViewDataBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "layout", "", "(I)V", "beforeProcess", "Lkotlin/Function0;", "", "getBeforeProcess", "()Lkotlin/jvm/functions/Function0;", "setBeforeProcess", "(Lkotlin/jvm/functions/Function0;)V", "isOverrideBack", "", "()Z", "setOverrideBack", "(Z)V", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "getLayoutRes", "initConfig", "initData", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "preInitView", "observerKt", "T", "Landroidx/lifecycle/LiveData;", "block", "Lkotlin/Function1;", "common_debug"})
public abstract class BaseActivity<ActBinding extends androidx.databinding.ViewDataBinding> extends androidx.appcompat.app.AppCompatActivity {
    protected ActBinding mBinding;
    private boolean isOverrideBack = false;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> beforeProcess;
    
    public BaseActivity() {
        super();
    }
    
    public BaseActivity(@androidx.annotation.LayoutRes
    int layout) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    protected final ActBinding getMBinding() {
        return null;
    }
    
    protected final void setMBinding(@org.jetbrains.annotations.NotNull
    ActBinding p0) {
    }
    
    public final boolean isOverrideBack() {
        return false;
    }
    
    public final void setOverrideBack(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getBeforeProcess() {
        return null;
    }
    
    public final void setBeforeProcess(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.annotation.LayoutRes
    public abstract int getLayoutRes();
    
    public void preInitView() {
    }
    
    public void initView() {
    }
    
    public void initConfig() {
    }
    
    public void initData() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    protected final <T extends java.lang.Object>void observerKt(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<T> $this$observerKt, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
}