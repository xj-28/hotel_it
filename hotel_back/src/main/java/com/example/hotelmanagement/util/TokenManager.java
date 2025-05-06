package com.example.hotelmanagement.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {
    private static final Map<String, Integer> tokenMap = new HashMap<>();

    public static String generateToken(Integer adminId) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, adminId);
        return token;
    }

    public static boolean validateToken(String token) {
        return tokenMap.containsKey(token);
    }

    public static Integer getAdminIdByToken(String token) {
        return tokenMap.get(token);
    }

    public static void removeToken(String token) {
        tokenMap.remove(token);
    }
}