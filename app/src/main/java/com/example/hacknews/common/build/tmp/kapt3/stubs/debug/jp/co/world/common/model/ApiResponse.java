package jp.co.world.common.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0004*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0003\u0082\u0001\u0003\u0005\u0006\u0007\u00a8\u0006\b"}, d2 = {"Ljp/co/world/common/model/ApiResponse;", "T", "", "()V", "Companion", "Ljp/co/world/common/model/ApiEmptyResponse;", "Ljp/co/world/common/model/ApiErrorResponse;", "Ljp/co/world/common/model/ApiSuccessResponse;", "common_debug"})
public abstract class ApiResponse<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull
    public static final jp.co.world.common.model.ApiResponse.Companion Companion = null;
    
    private ApiResponse() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ \u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\n\"\u0004\b\u0001\u0010\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\f\u00a8\u0006\r"}, d2 = {"Ljp/co/world/common/model/ApiResponse$Companion;", "", "()V", "create", "Ljp/co/world/common/model/ApiErrorResponse;", "T", "errorCode", "", "error", "", "Ljp/co/world/common/model/ApiResponse;", "response", "Lretrofit2/Response;", "common_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final <T extends java.lang.Object>jp.co.world.common.model.ApiResponse<T> create(@org.jetbrains.annotations.NotNull
        retrofit2.Response<T> response) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final <T extends java.lang.Object>jp.co.world.common.model.ApiErrorResponse<T> create(int errorCode, @org.jetbrains.annotations.NotNull
        java.lang.Throwable error) {
            return null;
        }
    }
}