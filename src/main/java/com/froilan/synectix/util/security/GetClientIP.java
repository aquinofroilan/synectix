package com.froilan.synectix.util.security;

import jakarta.servlet.http.HttpServletRequest;

public class GetClientIP {
    public static String extractClientIp(HttpServletRequest request) {
        String[] headersToCheck = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        for (String header : headersToCheck) {
            String ipList = request.getHeader(header);
            if (ipList != null && !ipList.isEmpty() && !"unknown".equalsIgnoreCase(ipList)) {
                return ipList.split(",")[0].trim(); // In case of multiple IPs
            }
        }

        return request.getRemoteAddr(); // Fallback
    }
}
