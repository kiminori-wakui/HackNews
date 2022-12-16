package jp.co.world.common.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H&J,\u0010\u0006\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH&J&\u0010\r\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH&J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\tH\u0016\u00a8\u0006\u0011"}, d2 = {"Ljp/co/world/common/network/HttpApi;", "", "cancelAllRequest", "", "cancelRequest", "tag", "get", "params", "", "", "urlStr", "callback", "Ljp/co/world/common/network/support/IHttpCallback;", "getSync", "post", "body", "postSync", "common_debug"})
public abstract interface HttpApi {
    
    public abstract void get(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr, @org.jetbrains.annotations.NotNull
    jp.co.world.common.network.support.IHttpCallback callback);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSync(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr);
    
    public abstract void post(@org.jetbrains.annotations.NotNull
    java.lang.Object body, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr, @org.jetbrains.annotations.NotNull
    jp.co.world.common.network.support.IHttpCallback callback);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object postSync(@org.jetbrains.annotations.NotNull
    java.lang.Object body, @org.jetbrains.annotations.NotNull
    java.lang.String urlStr);
    
    public abstract void cancelRequest(@org.jetbrains.annotations.NotNull
    java.lang.Object tag);
    
    public abstract void cancelAllRequest();
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object getSync(@org.jetbrains.annotations.NotNull
        jp.co.world.common.network.HttpApi $this, @org.jetbrains.annotations.NotNull
        java.util.Map<java.lang.String, ? extends java.lang.Object> params, @org.jetbrains.annotations.NotNull
        java.lang.String urlStr) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object postSync(@org.jetbrains.annotations.NotNull
        jp.co.world.common.network.HttpApi $this, @org.jetbrains.annotations.NotNull
        java.lang.Object body, @org.jetbrains.annotations.NotNull
        java.lang.String urlStr) {
            return null;
        }
    }
}