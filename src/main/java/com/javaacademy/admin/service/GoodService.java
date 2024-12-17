package com.javaacademy.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.admin.dto.GoodDto;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoodService {
    private final String PATCH_URL = "http://localhost:%s/shop/good";

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public void patchGood(OkHttpClient client, GoodDto goodDto, String port) {
        byte[] body = objectMapper.writeValueAsBytes(goodDto);

        Request request = new Request.Builder()
                .patch(RequestBody.create(body))
                .url(String.format(PATCH_URL, port))
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
    }

}
