package jp.co.world.common.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u000bH\'J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\u001a\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J.\u0010\u001b\u001a\u00020\r\"\u0004\b\u0000\u0010\u001c*\b\u0012\u0004\u0012\u0002H\u001c0\u001d2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u001c\u0012\u0004\u0012\u00020\r0\u001fH\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Ljp/co/world/common/base/BottomSheetFragmentFullScreen;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "mBinding", "Landroidx/databinding/ViewDataBinding;", "bindView", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "getLayoutRes", "", "initConfig", "", "initData", "initView", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onViewCreated", "setupFullHeight", "bottomSheet", "observeKt", "T", "Landroidx/lifecycle/LiveData;", "block", "Lkotlin/Function1;", "common_debug"})
public abstract class BottomSheetFragmentFullScreen extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    private androidx.databinding.ViewDataBinding mBinding;
    
    public BottomSheetFragmentFullScreen() {
        super();
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
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setupFullHeight(android.view.View bottomSheet) {
    }
    
    @java.lang.Override
    public void onDestroy() {
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
    
    public void initView() {
    }
    
    protected final <T extends java.lang.Object>void observeKt(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<T> $this$observeKt, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
}