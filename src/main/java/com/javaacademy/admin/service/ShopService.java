package com.javaacademy.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    private final String STATUS_URL = "http://localhost:%s/application/status";

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Operation(summary = "Получение статуса", description = "Получение всех статусов")
    public String getStatusFirstShop(OkHttpClient okHttpClient, String port) {
        Request request = new Request.Builder()
                .get()
                .url(String.format(STATUS_URL, port))
                .build();
        return okHttpClient.newCall(request).execute().body().string();
    }

}
