package jp.co.world.common.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0011\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Ljp/co/world/common/network/KtRetrofit;", "", "()V", "jar", "Ljp/co/world/common/network/config/LocalCookieJar;", "getJar", "()Ljp/co/world/common/network/config/LocalCookieJar;", "mOkClient", "Lokhttp3/OkHttpClient;", "retrofit", "Lretrofit2/Retrofit;", "retrofitBuilder", "Lretrofit2/Retrofit$Builder;", "kotlin.jvm.PlatformType", "getService", "T", "serviceClazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "initConfig", "baseUrl", "", "okClient", "common_debug"})
public final class KtRetrofit {
    @org.jetbrains.annotations.NotNull
    public static final jp.co.world.common.network.KtRetrofit INSTANCE = null;
    @org.jetbrains.annotations.NotNull
    private static final jp.co.world.common.network.config.LocalCookieJar jar = null;
    private static final okhttp3.OkHttpClient mOkClient = null;
    private static final retrofit2.Retrofit.Builder retrofitBuilder = null;
    private static retrofit2.Retrofit retrofit;
    
    private KtRetrofit() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final jp.co.world.common.network.config.LocalCookieJar getJar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final jp.co.world.common.network.KtRetrofit initConfig(@org.jetbrains.annotations.NotNull
    java.lang.String baseUrl, @org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okClient) {
        return null;
    }
    
    public final <T extends java.lang.Object>T getService(@org.jetbrains.annotations.NotNull
    java.lang.Class<T> serviceClazz) {
        return null;
    }
}