package jp.co.world.common.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 32\u00020\u0001:\u00013B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u001f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H 0\u001f\"\u0006\b\u0000\u0010 \u0018\u00012\u0006\u0010!\u001a\u00020\"H\u0086\bJ!\u0010\u001e\u001a\u00020\u001b\"\u0006\b\u0000\u0010 \u0018\u00012\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0086\bJ$\u0010%\u001a\u0004\u0018\u00010\"2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\'2\u0006\u0010!\u001a\u00020\"J,\u0010%\u001a\u00020\u001b2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\'2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010(\u001a\u0004\u0018\u00010\"J\u0006\u0010)\u001a\u00020\nJ\u000e\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\nJ \u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J$\u0010,\u001a\u0004\u0018\u00010\"2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\'2\u0006\u0010.\u001a\u00020\"J!\u0010/\u001a\u0004\u0018\u00010\"*\u00020\u00062\b\b\u0002\u00100\u001a\u000201H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00102R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2 = {"Ljp/co/world/common/network/OkHttpApi;", "Ljp/co/world/common/network/HttpApi;", "()V", "callMap", "Landroidx/collection/SimpleArrayMap;", "", "Lokhttp3/Call;", "getCallMap", "()Landroidx/collection/SimpleArrayMap;", "defaultClient", "Lokhttp3/OkHttpClient;", "jar", "Ljp/co/world/common/network/config/LocalCookieJar;", "getJar", "()Ljp/co/world/common/network/config/LocalCookieJar;", "mClient", "getMClient", "()Lokhttp3/OkHttpClient;", "setMClient", "(Lokhttp3/OkHttpClient;)V", "maxRetry", "", "getMaxRetry", "()I", "setMaxRetry", "(I)V", "cancelAllRequest", "", "cancelRequest", "tag", "download", "", "T", "urlStr", "", "callback", "Ljp/co/world/common/network/support/IHttpCallback;", "get", "params", "", "getACTK", "getClient", "initConfig", "client", "post", "body", "uri", "call", "async", "", "(Lokhttp3/Call;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "common_debug"})
public final class OkHttpApi implements jp.co.world.common.network.HttpApi {
    private int maxRetry = 0;
    @org.jetbrains.annotations.NotNull
    private final androidx.collection.SimpleArrayMap<java.lang.Object, okhttp3.Call> callMap = null;
    @org.jetbrains.annotations.NotNull
    private final jp.co.world.common.network.config.LocalCookieJar jar = null;
    private final okhttp3.OkHttpClient defaultClient = null;
    @org.jetbrains.annotations.NotNull
    private okhttp3.OkHttpClient mClient;
    @org.jetbrains.annotations.NotNull
    public static final jp.co.world.common.network.OkHttpApi.Companion Companion = null;
    @kotlin.jvm.Volatile
    private static volatile jp.co.world.common.network.OkHttpApi api;
    
    private OkHttpApi() {
        super();
    }
    
    public final int getMaxRetry() {
        return 0;
    }
    
    public final void setMaxRetry(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.collection.SimpleArrayMap<java.lang.Object, okhttp3.Call> getCallMap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final jp.co.world.common.network.config.LocalCookieJar getJar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final okhttp3.OkHttpClient getMClient() {
        return null;
    }
    
    public final void setMClient(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final okhttp3.OkHttpClient getClient() {
        return null;
    }
    
    public final void initConfig(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient client) {
    }
    
    @java.lang.Override
    public void get(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr, @org.jetbrains.annotations.NotNull
    jp.co.world.common.network.support.IHttpCallback callback) {
    }
    
    @java.lang.Override
    public void post(@org.jetbrains.annotations.NotNull
    java.lang.Object body, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr, @org.jetbrains.annotations.NotNull
    jp.co.world.common.network.support.IHttpCallback callback) {
    }
    
    @java.lang.Override
    public void cancelRequest(@org.jetbrains.annotations.NotNull
    java.lang.Object tag) {
    }
    
    @java.lang.Override
    public void cancelAllRequest() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String get(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String post(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
    java.lang.String uri) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getACTK() {
        return null;
    }
    
    private final java.lang.Object call(okhttp3.Call $this$call, boolean async, kotlin.coroutines.Continuation<? super java.lang.String> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getSync(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public java.lang.Object postSync(@org.jetbrains.annotations.NotNull
    java.lang.Object body, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Ljp/co/world/common/network/OkHttpApi$Companion;", "", "()V", "api", "Ljp/co/world/common/network/OkHttpApi;", "getInstance", "common_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @kotlin.jvm.Synchronized
        public final synchronized jp.co.world.common.network.OkHttpApi getInstance() {
            return null;
        }
    }
}