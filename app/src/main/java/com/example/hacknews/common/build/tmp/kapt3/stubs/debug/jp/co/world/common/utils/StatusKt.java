package jp.co.world.common.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\tH\u0007\u001a\'\u0010\n\u001a\u00020\u0007*\u00020\b2\b\b\u0003\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0002\u0010\f\u001a\u001c\u0010\r\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0017\u0010\u0002\u001a\u00020\u0001*\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0010"}, d2 = {"COLOR_TRANSPARENT", "", "statusBarHeight", "Landroid/content/Context;", "getStatusBarHeight", "(Landroid/content/Context;)I", "darkMode", "", "Landroid/app/Activity;", "", "immersive", "color", "(Landroid/app/Activity;ILjava/lang/Boolean;)V", "setTranslucentView", "container", "Landroid/view/ViewGroup;", "common_debug"})
public final class StatusKt {
    private static final int COLOR_TRANSPARENT = 0;
    
    @kotlin.jvm.JvmOverloads
    @android.annotation.SuppressLint(value = {"ObsoleteSdkInt"})
    public static final void immersive(@org.jetbrains.annotations.NotNull
    android.app.Activity $this$immersive) {
    }
    
    @kotlin.jvm.JvmOverloads
    @android.annotation.SuppressLint(value = {"ObsoleteSdkInt"})
    public static final void immersive(@org.jetbrains.annotations.NotNull
    android.app.Activity $this$immersive, @androidx.annotation.ColorInt
    int color) {
    }
    
    @kotlin.jvm.JvmOverloads
    @android.annotation.SuppressLint(value = {"ObsoleteSdkInt"})
    public static final void immersive(@org.jetbrains.annotations.NotNull
    android.app.Activity $this$immersive, @androidx.annotation.ColorInt
    int color, @org.jetbrains.annotations.Nullable
    java.lang.Boolean darkMode) {
    }
    
    public static final int getStatusBarHeight(@org.jetbrains.annotations.Nullable
    android.content.Context $this$statusBarHeight) {
        return 0;
    }
    
    private static final void setTranslucentView(android.content.Context $this$setTranslucentView, android.view.ViewGroup container, int color) {
    }
    
    @kotlin.jvm.JvmOverloads
    public static final void darkMode(@org.jetbrains.annotations.NotNull
    android.app.Activity $this$darkMode) {
    }
    
    @kotlin.jvm.JvmOverloads
    public static final void darkMode(@org.jetbrains.annotations.NotNull
    android.app.Activity $this$darkMode, boolean darkMode) {
    }
}