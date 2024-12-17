package com.javaacademy.admin.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.admin.dto.GoodDto;
import com.javaacademy.admin.service.GoodService;
import com.javaacademy.admin.service.ShopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
@Tag(name = "Shop controller", description = "Контроллер для работы с магазином")
public class AdminController {
    public static final String FIRST_SHOP_PORT = "8081";
    public static final String SECOND_SHOP_PORT = "8082";

    private final ShopService shopService;
    private final GoodService goodService;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper;

    @GetMapping("/status")
    public List<Map<String, Object>> getStatus() {
        ArrayList<String> shopPorts = new ArrayList<>(List.of(FIRST_SHOP_PORT, SECOND_SHOP_PORT));
        List<Map<String, Object>> statuses = new ArrayList<>();
        shopPorts.forEach(port -> statuses.add(getMap(shopService.getStatusFirstShop(client, port))));
        return statuses;
    }

    @PatchMapping("/good")
    public void updateGood(@RequestBody GoodDto newGoodDto) {
        goodService.patchGood(client, newGoodDto, FIRST_SHOP_PORT);
        goodService.patchGood(client, newGoodDto, SECOND_SHOP_PORT);
    }

    @SneakyThrows
    private Map<String, Object> getMap(String responseBody) {
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        return objectMapper.readValue(responseBody, typeReference);
    }
}
