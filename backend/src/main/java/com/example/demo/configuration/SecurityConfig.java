package com.example.demo.configuration;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.auth.IntrospectRequest;
import com.example.demo.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Bật @PreAuthorize
public class SecurityConfig {

    // Sửa lại cho đúng tên biến trong .env
    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    private AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF cho JWT
                .authorizeHttpRequests(request -> request
                        // Cho phép các request public (Login, Introspect, các API xem sản phẩm công khai)
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/auth/register",
                                "/api/v1/auth/login",
                                "/api/v1/auth/introspect",
                                "/api/v1/auth/logout"
                        ).permitAll()

                        // Các request khác bắt buộc phải đăng nhập
                        .anyRequest().permitAll()
                );

        // Cấu hình Resource Server để xử lý Bearer Token
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(customAuthenticationEntryPoint()) // Nên tách class riêng cho sạch
        );

        return httpSecurity.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Lấy chuỗi "ROLE_ADMIN PRODUCT_READ..." từ claim "scope"
            String scope = jwt.getClaimAsString("scope");
            if (scope == null || scope.isEmpty()) return List.of();

            // Tách chuỗi bằng dấu cách và map sang SimpleGrantedAuthority
            return Arrays.stream(scope.split(" "))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return converter;
    }

    @Bean
    public JwtDecoder customJwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        NimbusJwtDecoder nimbusDecoder = NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();

        return token -> {
            try {
                // Gọi introspect của Service để check Redis (Logout)
                var response = authenticationService.introspect(new IntrospectRequest(token));
                if (!response.isValid()) {
                    throw new JwtException("Token đã bị hủy (Logged out) hoặc không hợp lệ");
                }
                return nimbusDecoder.decode(token);
            } catch (Exception e) {
                throw new JwtException(e.getMessage());
            }
        };
    }

    // Tự định nghĩa EntryPoint để trả về JSON chuẩn cho FE dễ xử lý
    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ApiResponse<?> apiResponse = ApiResponse.builder()
                    .message("Bạn chưa đăng nhập hoặc Token hết hạn")
                    .build();

            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        };
    }
}