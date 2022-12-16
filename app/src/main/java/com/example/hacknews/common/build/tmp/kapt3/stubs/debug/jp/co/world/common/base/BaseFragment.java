package jp.co.world.common.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH&J\b\u0010\u001e\u001a\u00020\u0004H\'J\b\u0010\u001f\u001a\u00020\fH\u0016J\b\u0010 \u001a\u00020\fH\u0016J&\u0010!\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010&\u001a\u00020\fH\u0016J\b\u0010\'\u001a\u00020\fH\u0016J\b\u0010(\u001a\u00020\fH\u0016J\u001a\u0010)\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J.\u0010*\u001a\u00020\f\"\u0004\b\u0000\u0010+*\b\u0012\u0004\u0012\u0002H+0,2\u0014\u0010-\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H+\u0012\u0004\u0012\u00020\f0.H\u0004R\u0017\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00078F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006/"}, d2 = {"Ljp/co/world/common/base/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "layout", "", "(I)V", "baseActivity", "Ljp/co/world/common/base/BaseActivity;", "getBaseActivity", "()Ljp/co/world/common/base/BaseActivity;", "mAfterProcess", "Lkotlin/Function0;", "", "getMAfterProcess", "()Lkotlin/jvm/functions/Function0;", "setMAfterProcess", "(Lkotlin/jvm/functions/Function0;)V", "mBinding", "Landroidx/databinding/ViewDataBinding;", "mIsResumed", "", "getMIsResumed", "()Z", "setMIsResumed", "(Z)V", "bindView", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "getLayoutRes", "initConfig", "initData", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onPause", "onResume", "onViewCreated", "observeKt", "T", "Landroidx/lifecycle/LiveData;", "block", "Lkotlin/Function1;", "common_debug"})
public abstract class BaseFragment extends androidx.fragment.app.Fragment {
    private androidx.databinding.ViewDataBinding mBinding;
    private boolean mIsResumed = true;
    @org.jetbrains.annotations.NotNull
    private kotlin.jvm.functions.Function0<kotlin.Unit> mAfterProcess;
    
    public BaseFragment() {
        super();
    }
    
    public BaseFragment(@androidx.annotation.LayoutRes
    int layout) {
        super();
    }
    
    public final boolean getMIsResumed() {
        return false;
    }
    
    public final void setMIsResumed(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getMAfterProcess() {
        return null;
    }
    
    public final void setMAfterProcess(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final jp.co.world.common.base.BaseActivity<?> getBaseActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void onPause() {
    }
    
    @androidx.annotation.LayoutRes
    public abstract int getLayoutRes();
    
    @org.jetbrains.annotations.NotNull
    public abstract androidx.databinding.ViewDataBinding bindView(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState);
    
    public void initConfig() {
    }
    
    public void initData() {
    }
    
    protected final <T extends java.lang.Object>void observeKt(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<T> $this$observeKt, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
}