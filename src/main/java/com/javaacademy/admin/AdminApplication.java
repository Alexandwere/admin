package com.javaacademy.admin;

import com.javaacademy.admin.service.GoodService;
import com.javaacademy.admin.service.ShopService;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AdminApplication.class, args);
	}

}
