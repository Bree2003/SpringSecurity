package springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import springbootsecurity.service.UserDetailServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
//para poder utilizar las anotaciones en el controller es necesario esta anotación
@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    AuthenticationConfiguration authenticationConfiguration;

    //le vamos a indicar que en anotaciones permitiremos las rutas en el controller
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
////        httpSecurity
////                .authorizeHttpRequests(auth -> auth
////                        .anyRequest().authenticated()
////                );
//
//        return httpSecurity
//                //Desactiva la protección CSRF
//                .csrf(csrf -> csrf.disable())
//                // Habilita la autenticación básica HTTP.
//                .httpBasic(Customizer.withDefaults())
//                //Configura la política de creación de sesiones para que sea sin estado (stateless).
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                //Configura la autorización para las solicitudes HTTP
//                .authorizeHttpRequests(http -> {
//                    // configurar endpoints publicos
//                    http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
//                    //configurar los endpoints privados
//                    http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("READ");
//                      http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAnyAuthority("READ", "CREATE");
//                    //configurar el resto de endpoints - NO ESPECIFICADOS
//                    //http.anyRequest().authenticated();
//                    http.anyRequest().denyAll();
//                })
//                .build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
////        List<UserDetails> userDetailsList = new ArrayList<>(Arrays.asList(
////                User.withUsername("brisa")
////                        .password("1234")
////                        .roles("ADMIN")
////                        .authorities("READ", "CREATE")
////                        .build(),
////                User.withUsername("sasha")
////                        .password("4567")
////                        .roles("USER")
////                        .authorities("READ")
////                        .build()
////        ));
//        UserDetails userDetails = User.withUsername("brisa")
//                .password("1234")
//                .roles("ADMIN")
//                .authorities("READ", "CREATE")
//                .build();
//
////        return new InMemoryUserDetailsManager(userDetailsList);
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

}
