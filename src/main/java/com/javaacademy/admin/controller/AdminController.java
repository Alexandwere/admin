package com.javaacademy.admin.controller;

import com.javaacademy.admin.dto.GoodDto;
import com.javaacademy.admin.service.GoodService;
import com.javaacademy.admin.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/status")
    @Operation(summary = "Получение статуса", description = "Получение всех статусов")
    @ApiResponse(responseCode = "200 - успешно",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = GoodDto.class)))})
    public List<Map<String, Object>> getStatus() {
        return shopService.getStatuses();
    }

    @PatchMapping("/good")
    @Operation(summary = "Изменение товара", description = "Изменение цены товара")
    public void updateGood(@RequestBody @Parameter(description = "Обновленный товар") GoodDto newGoodDto) {
        goodService.patchGood(newGoodDto, FIRST_SHOP_PORT);
        goodService.patchGood(newGoodDto, SECOND_SHOP_PORT);
    }

}
