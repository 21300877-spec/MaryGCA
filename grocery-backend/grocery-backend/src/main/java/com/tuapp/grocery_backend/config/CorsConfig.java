@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        
        // Aquí agregamos tu URL de Azure Static Web Apps y localhost para pruebas
        config.setAllowedOrigins(List.of(
            "https://calm-pond-088b02b1e.7.azurestaticapps.net",
            "http://localhost:3000",
            "http://localhost:8080"
        )); 
        
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
