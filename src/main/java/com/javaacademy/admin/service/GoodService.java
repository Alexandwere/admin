package com.javaacademy.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.admin.dto.GoodDto;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
public class GoodService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();

    @SneakyThrows
    public void patchGood(GoodDto goodDto, String port) {
        String patchUrl = "http://localhost:%s/shop/good";
        byte[] body = objectMapper.writeValueAsBytes(goodDto);

        Request request = new Request.Builder()
                .patch(RequestBody.create(body))
                .url(String.format(patchUrl, port))
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        response.close();
    }

}
