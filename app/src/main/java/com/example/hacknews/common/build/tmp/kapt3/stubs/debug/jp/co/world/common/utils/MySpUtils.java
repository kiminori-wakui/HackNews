package jp.co.world.common.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\nJ\"\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000fJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0014J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0017J\u001c\u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fJ\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001J\u0016\u0010\u001c\u001a\n \u0010*\u0004\u0018\u00010\u001d0\u001d2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\fR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u001f"}, d2 = {"Ljp/co/world/common/utils/MySpUtils;", "", "()V", "kv", "Lcom/tencent/mmkv/MMKV;", "getKv", "()Lcom/tencent/mmkv/MMKV;", "kv$delegate", "Lkotlin/Lazy;", "getBoolean", "", "key", "", "defValue", "getBytes", "", "kotlin.jvm.PlatformType", "getFloat", "", "getInt", "", "getIntMinus", "getLong", "", "getString", "put", "", "value", "remove", "Landroid/content/SharedPreferences$Editor;", "removeValue", "common_debug"})
public final class MySpUtils {
    @org.jetbrains.annotations.NotNull
    public static final jp.co.world.common.utils.MySpUtils INSTANCE = null;
    private static final kotlin.Lazy kv$delegate = null;
    
    private MySpUtils() {
        super();
    }
    
    private final com.tencent.mmkv.MMKV getKv() {
        return null;
    }
    
    public final void put(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.Object value) {
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean defValue) {
        return false;
    }
    
    public final byte[] getBytes(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.Nullable
    byte[] defValue) {
        return null;
    }
    
    public final float getFloat(@org.jetbrains.annotations.NotNull
    java.lang.String key, float defValue) {
        return 0.0F;
    }
    
    public final int getInt(@org.jetbrains.annotations.NotNull
    java.lang.String key, int defValue) {
        return 0;
    }
    
    public final int getIntMinus(@org.jetbrains.annotations.NotNull
    java.lang.String key, int defValue) {
        return 0;
    }
    
    public final long getLong(@org.jetbrains.annotations.NotNull
    java.lang.String key, long defValue) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.String defValue) {
        return null;
    }
    
    public final android.content.SharedPreferences.Editor remove(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return null;
    }
    
    public final void removeValue(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
    }
}