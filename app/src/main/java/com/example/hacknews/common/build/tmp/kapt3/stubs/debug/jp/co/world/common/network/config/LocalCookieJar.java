package jp.co.world.common.network.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\n\u001a\u00020\u000eH\u0016JD\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\u00122\u001a\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u00122\u0006\u0010\u0014\u001a\u00020\u0007H\u0002J\u001e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0016J\u001e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0007R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Ljp/co/world/common/network/config/LocalCookieJar;", "Lokhttp3/CookieJar;", "()V", "clientCookiesStore", "", "", "Ljava/util/ArrayList;", "Lokhttp3/Cookie;", "serverCookiesStore", "getCookie", "url", "key", "loadForRequest", "", "Lokhttp3/HttpUrl;", "putCookie", "", "storedCookieList", "Lkotlin/collections/ArrayList;", "serverCookieList", "newCookie", "saveFromResponse", "cookies", "setCookie", "value", "cookie", "Companion", "common_debug"})
public final class LocalCookieJar implements okhttp3.CookieJar {
    private final java.util.Map<java.lang.String, java.util.ArrayList<okhttp3.Cookie>> serverCookiesStore = null;
    private final java.util.Map<java.lang.String, java.util.ArrayList<okhttp3.Cookie>> clientCookiesStore = null;
    @org.jetbrains.annotations.NotNull
    public static final jp.co.world.common.network.config.LocalCookieJar.Companion Companion = null;
    
    public LocalCookieJar() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.util.List<okhttp3.Cookie> loadForRequest(@org.jetbrains.annotations.NotNull
    okhttp3.HttpUrl url) {
        return null;
    }
    
    @java.lang.Override
    public void saveFromResponse(@org.jetbrains.annotations.NotNull
    okhttp3.HttpUrl url, @org.jetbrains.annotations.NotNull
    java.util.List<okhttp3.Cookie> cookies) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCookie(@org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return null;
    }
    
    public final void setCookie(@org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    public final void setCookie(@org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    okhttp3.Cookie cookie) {
    }
    
    private final void putCookie(java.util.ArrayList<okhttp3.Cookie> storedCookieList, java.util.ArrayList<okhttp3.Cookie> serverCookieList, okhttp3.Cookie newCookie) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Ljp/co/world/common/network/config/LocalCookieJar$Companion;", "", "()V", "common_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}