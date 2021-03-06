package com.greenfox.szilvi.chatapp;

import ch.qos.logback.classic.Level;
import com.greenfox.szilvi.chatapp.model.RequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class P2PChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2PChatAppApplication.class, args);
	}
}
