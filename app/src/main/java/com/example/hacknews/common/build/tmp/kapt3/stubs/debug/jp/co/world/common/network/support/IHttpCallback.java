package jp.co.world.common.network.support;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H&J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H&\u00a8\u0006\u0007"}, d2 = {"Ljp/co/world/common/network/support/IHttpCallback;", "", "onFailed", "", "error", "onSuccess", "data", "common_debug"})
public abstract interface IHttpCallback {
    
    public abstract void onSuccess(@org.jetbrains.annotations.Nullable
    java.lang.Object data);
    
    public abstract void onFailed(@org.jetbrains.annotations.Nullable
    java.lang.Object error);
}