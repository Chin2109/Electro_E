package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//Yêu cầu muốn truy cập endpoints nhất định thì cần jwt phù hợp (Authorization: Author type: Bearer token)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
//    @Value("${jwt.signer-key}")
//    private String signerKey;
//
//    @Autowired
//    private AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //1. Quản lý truy cập các endpoints
        httpSecurity.authorizeHttpRequests(request ->
                        request

                                .anyRequest().permitAll()
        );

//        //2. Bảo Spring Security: đây là Resource Server (Nhận kèm Bearer Token)
//        //Khi nhận Token, xử lý bằng customJWTDecoder() để decode
//        //Xử lý xong thì map claims sang GrantedAuthority bằng jwtAuthenticationConverter()
//        httpSecurity.oauth2ResourceServer(oath2 ->
//                oath2.jwt(jwtConfigurer ->
//                        jwtConfigurer.decoder(customJwtDecoder())
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//        );
//
//        //3. Handling các exception của security jwt (user có token nhưng không đủ quyền; user chưa đăng nhập; token invalid)
//        //Các exception được throw từ layer security thì ko catch ở global exception handling được
//        httpSecurity
//                .exceptionHandling(exception ->
//                        exception.accessDeniedHandler(customAccessDeniedHandler())
//                                .authenticationEntryPoint(customAuthenticationEntryPoint())
//                );
//
//        // Disable CSRF vì nó là để bảo vệ browser khi browser gửi cookie lên server
//        // JWT được gửi qua Authorization header (không phải cookie),
//        // nên trình duyệt không tự động gửi thông tin xác thực → không cần CSRF.
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//
        return httpSecurity.build();
    }
//
//    @Bean
//    public JwtDecoder customJwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//        NimbusJwtDecoder nimbusDecoder = NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//
//        //introspect token để đảm bảo token ko bị revoke trước khi decode
//        return (String token) -> {
//
//            try {
//                var response = authenticationService.introspect(
//                        IntrospectRequest.builder()
//                                .token(token)
//                                .build()
//                );
//
//                if (!response.isValid()) {
//                    throw new JwtException("Token is invalid!");
//                }
//
//                // Token hợp lệ → decode bằng NimbusJwtDecoder
//                return nimbusDecoder.decode(token);
//
//            } catch (ParseException | JOSEException e) {
//                throw new JwtException(e.getMessage());
//            }
//        };
//    }
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//
//            //Lấy role từ claim "role" rồi thêm vào authorities
//            //@PreAuthorize("hasRole('XXX')"): check trong list authorities có tồn tại ROLE_XXX ko
//            List<String> roles = jwt.getClaimAsStringList("role");
//            if(roles != null) {
//                authorities.addAll(
//                        roles.stream()
//                                .map(r -> new SimpleGrantedAuthority(r))
//                                .collect(Collectors.toList())
//                );
//            }
//
//            //Lấy permission từ claim "permission" rùi thêm vào authorities
//            //@PreAuthorize("hasAuthority('YYY')"): check trong authorities có tồn tại YYY hok
//            List<String> permissions = jwt.getClaimAsStringList("permission");
//            if(permissions != null) {
//                authorities.addAll(
//                        permissions.stream()
//                                .map(p -> new SimpleGrantedAuthority(p))
//                                .collect(Collectors.toList())
//                );
//            }
//
//            return authorities;
//        });
//
//        return converter;
//    }
//
//    @Bean
//    public AccessDeniedHandler customAccessDeniedHandler() {
//        return (request, response, accessDeniedException) -> {
//            response.setStatus(HttpStatus.FORBIDDEN.value());
//            response.setContentType("application/json");
//
//            ApiResponse apiResponse = ApiResponse.builder()
//                    .code(ErrorCode.UNAUTHORIZED.getCode())
//                    .message(ErrorCode.UNAUTHORIZED.getMessage())
//                    .build();
//
//            ObjectMapper mapper = new ObjectMapper();
//            String responseBody = mapper.writeValueAsString(apiResponse);
//
//            response.getWriter().write(responseBody);
//        };
//    }
//
//    @Bean
//    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
//        return (request, response, authException) -> {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//
//            ApiResponse apiResponse = ApiResponse.builder()
//                    .code(ErrorCode.UNAUTHENTICATED.getCode())
//                    .message(ErrorCode.UNAUTHENTICATED.getMessage())
//                    .build();
//
//            ObjectMapper mapper = new ObjectMapper();
//            String responseBody = mapper.writeValueAsString(apiResponse);
//
//            response.getWriter().write(responseBody);
//        };
//        }
    }