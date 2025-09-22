package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UsernamePasswordGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String generatePassword() {
        return IntStream.range(0, 10)
                .map(i -> CHARS.charAt(RANDOM.nextInt(CHARS.length())))
                .mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.joining());
    }

    /**
     * base username = firstName.lastName
     * if existsChecker.apply(base) == true => then append numeric suffix starting at 2 until free.
     * existsChecker should return true if username already exists (in trainer OR trainee).
     */
    public String generateUniqueUsername(String firstName, String lastName, Function<String, Boolean> existsChecker) {
        String base = firstName + "." + lastName;
        if (!existsChecker.apply(base)) return base;
        int suffix = 2;
        while (existsChecker.apply(base + suffix)) suffix++;
        return base + suffix;
    }
}