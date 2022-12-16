package com.zsk.common.network.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u0000 +2\u00020\u0001:\u0003*+,B\"\u0012\u001b\b\u0002\u0010\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\b\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\nJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0012H\u0002J&\u0010\u0019\u001a\u00020\u00042\n\u0010\u0016\u001a\u00060\u001aj\u0002`\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J&\u0010 \u001a\u00020\u00042\n\u0010\u0016\u001a\u00060\u001aj\u0002`\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J&\u0010\"\u001a\u00020\u00042\n\u0010\u0016\u001a\u00060\u001aj\u0002`\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u001c\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\nH\u0002J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\fJ\u001a\u0010\'\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0012H\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/zsk/common/network/config/KtHttpLogInterceptor;", "Lokhttp3/Interceptor;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)V", "MILLIS_PATTERN", "", "colorLevel", "Lcom/zsk/common/network/config/KtHttpLogInterceptor$ColorLevel;", "logLevel", "Lcom/zsk/common/network/config/KtHttpLogInterceptor$LogLevel;", "logTag", "level", "decodeUrlStr", "url", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logBasicRep", "strb", "Ljava/lang/StringBuffer;", "response", "logBasicReq", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "request", "Lokhttp3/Request;", "connection", "Lokhttp3/Connection;", "logBodyReq", "logHeadersRep", "logHeadersReq", "logIt", "any", "", "tempLevel", "logRequest", "logResponse", "tag", "ColorLevel", "Companion", "LogLevel", "common_debug"})
public final class KtHttpLogInterceptor implements okhttp3.Interceptor {
    private com.zsk.common.network.config.KtHttpLogInterceptor.LogLevel logLevel = com.zsk.common.network.config.KtHttpLogInterceptor.LogLevel.NONE;
    private com.zsk.common.network.config.KtHttpLogInterceptor.ColorLevel colorLevel = com.zsk.common.network.config.KtHttpLogInterceptor.ColorLevel.DEBUG;
    private java.lang.String logTag = "<KtHttp>";
    private java.lang.String MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @org.jetbrains.annotations.NotNull
    public static final com.zsk.common.network.config.KtHttpLogInterceptor.Companion Companion = null;
    private static final java.lang.String TAG = "<KtHttp>";
    
    public KtHttpLogInterceptor() {
        super();
    }
    
    public KtHttpLogInterceptor(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super com.zsk.common.network.config.KtHttpLogInterceptor, kotlin.Unit> block) {
        super();
    }
    
    /**
     * 设置logLevel
     */
    @org.jetbrains.annotations.NotNull
    public final com.zsk.common.network.config.KtHttpLogInterceptor logLevel(@org.jetbrains.annotations.NotNull
    com.zsk.common.network.config.KtHttpLogInterceptor.LogLevel level) {
        return null;
    }
    
    /**
     * 设置colorLevel
     */
    @org.jetbrains.annotations.NotNull
    public final com.zsk.common.network.config.KtHttpLogInterceptor colorLevel(@org.jetbrains.annotations.NotNull
    com.zsk.common.network.config.KtHttpLogInterceptor.ColorLevel level) {
        return null;
    }
    
    /**
     * 设置Log的Tag
     */
    @org.jetbrains.annotations.NotNull
    public final com.zsk.common.network.config.KtHttpLogInterceptor logTag(@org.jetbrains.annotations.NotNull
    java.lang.String tag) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
    
    /**
     * 记录请求日志
     */
    private final void logRequest(okhttp3.Request request, okhttp3.Connection connection) {
    }
    
    private final void logBodyReq(java.lang.StringBuilder strb, okhttp3.Request request, okhttp3.Connection connection) {
    }
    
    private final void logHeadersReq(java.lang.StringBuilder strb, okhttp3.Request request, okhttp3.Connection connection) {
    }
    
    private final void logBasicReq(java.lang.StringBuilder strb, okhttp3.Request request, okhttp3.Connection connection) {
    }
    
    /**
     * 记录响应日志
     */
    private final void logResponse(okhttp3.Response response) {
    }
    
    private final void logHeadersRep(okhttp3.Response response, java.lang.StringBuffer strb) {
    }
    
    private final void logBasicRep(java.lang.StringBuffer strb, okhttp3.Response response) {
    }
    
    /**
     * 对于url编码的string解码
     */
    private final java.lang.String decodeUrlStr(java.lang.String url) {
        return null;
    }
    
    /**
     * 打印日志
     */
    private final void logIt(java.lang.Object any, com.zsk.common.network.config.KtHttpLogInterceptor.ColorLevel tempLevel) {
    }
    
    /**
     * 打印日志范围
     */
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/zsk/common/network/config/KtHttpLogInterceptor$LogLevel;", "", "(Ljava/lang/String;I)V", "NONE", "BASIC", "HEADERS", "BODY", "common_debug"})
    public static enum LogLevel {
        /*public static final*/ NONE /* = new NONE() */,
        /*public static final*/ BASIC /* = new BASIC() */,
        /*public static final*/ HEADERS /* = new HEADERS() */,
        /*public static final*/ BODY /* = new BODY() */;
        
        LogLevel() {
        }
    }
    
    /**
     * Log颜色等级，应用于android Logcat分为 v、d、i、w、e
     */
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/zsk/common/network/config/KtHttpLogInterceptor$ColorLevel;", "", "(Ljava/lang/String;I)V", "VERBOSE", "DEBUG", "INFO", "WARN", "ERROR", "common_debug"})
    public static enum ColorLevel {
        /*public static final*/ VERBOSE /* = new VERBOSE() */,
        /*public static final*/ DEBUG /* = new DEBUG() */,
        /*public static final*/ INFO /* = new INFO() */,
        /*public static final*/ WARN /* = new WARN() */,
        /*public static final*/ ERROR /* = new ERROR() */;
        
        ColorLevel() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/zsk/common/network/config/KtHttpLogInterceptor$Companion;", "", "()V", "TAG", "", "toDateTimeStr", "millis", "", "pattern", "common_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String toDateTimeStr(long millis, @org.jetbrains.annotations.NotNull
        java.lang.String pattern) {
            return null;
        }
    }
}