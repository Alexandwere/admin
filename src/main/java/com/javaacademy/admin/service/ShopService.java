package com.javaacademy.admin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.javaacademy.admin.controller.AdminController.FIRST_SHOP_PORT;
import static com.javaacademy.admin.controller.AdminController.SECOND_SHOP_PORT;

@Service
public class ShopService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();

    @SneakyThrows
    public String getShopStatus(OkHttpClient okHttpClient, String port) {
        String statusUrl = "http://localhost:%s/application/status";

        Request request = new Request.Builder()
                .get()
                .url(String.format(statusUrl, port))
                .build();

        try(Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }

    }

    public List<Map<String, Object>> getStatuses() {
        ArrayList<String> shopPorts = new ArrayList<>(List.of(FIRST_SHOP_PORT, SECOND_SHOP_PORT));
        List<Map<String, Object>> statuses = new ArrayList<>();
        shopPorts.forEach(port -> statuses.add(doMap(getShopStatus(client, port))));
        return statuses;
    }

    @SneakyThrows
    private Map<String, Object> doMap(String responseBody) {
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        return objectMapper.readValue(responseBody, typeReference);
    }

}
