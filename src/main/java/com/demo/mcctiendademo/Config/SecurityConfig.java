package com.demo.mcctiendademo.Config;

import com.demo.mcctiendademo.Service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Profile({"local","docker"})
public class SecurityConfig {

    /*
        Orden de la peticion http
     */


    /*
        Primer Paso: Pasa por el contenedor de SecurityFilterChain y pasa los filtros
        El SecurityFilterChain verifica las condiciones de los filtros!!, en caso de no tener un configuracion construida pasa
        todos los filtros por defecto (Ver el comentario de abajo para entender mejor como funciona).

        CSRF -> Protege la aplicacion de ataques de tipo CSRF (ataques a formularios en los que el usuario no esta autorizado es por eso
        que funciona en mvc, pero en las api rest es irrelevante)

        CSRF (MVC) -> Tenerlo activado
        CSRF (REST API) -> Tenerlo desactivado

        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS):

            Por defecto al iniciar session en una app te pide las credencias y guarda la session en memoria cuyo dato es algo pesado.
            STATELESS(Sin estado) ->  STATELESS permite que la session no dependa de una archivo en memoria sino de la expiracion del token

        authorizeHttpRequests -> Permite autorizar las rutas que se van a permitir.

        requestMatchers -> Permite especificar las rutas que se van a permitir.
     */

    @Value("${version.api}")
    private String inicialPath;

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET,inicialPath + "/category/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST,inicialPath + "/category/save").hasAuthority("CREATE");
                    auth.requestMatchers(HttpMethod.POST,inicialPath + "/category/saveAll").hasAuthority("CREATE");
                    auth.requestMatchers(HttpMethod.DELETE,inicialPath + "/category/**").hasAuthority("DELETE");
                    auth.anyRequest().denyAll();
                })
                .build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).build();
    }

    /*
        Segundo Paso: Pasa por el AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
        Tercer Paso: Pasa por el AuthenticationProvider que contiene el UserDetailsService y PasswordEncoder
        Esta es la configuracion basica de la AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsServiceImpl userDetailsService
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    /*
        Cuarto Paso: Pasa por el UserDetailsService que es un servicio que crea y registra a los usarios
     */



    /*
        Bonus: No es un paso en sí, es una clase que permite encriptar las contraseñas o data importante se usa tando el UserDetailsService como el AuthenticationProvider
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
        --Configuracion basica para pruebas
        En este ejemplo de SecurityFilterChain las peticion pasa todos los filtros por defecto por que no tiene los filtros construidos.
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http){
            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public AuthenticationProvider authenticationProvider(
                UserDetailsService userDetailsService,
                PasswordEncoder passwordEncoder
        ) {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
            provider.setPasswordEncoder(passwordEncoder);
            return provider;
        }

        @Bean
        public UserDetailsService userDetailsService(){


            List<UserDetails> users = List.of(
                    UserEntity.withUsername("roberto")
                            .password("123")
                            .roles("USER")
                            .authorities("READ","CREATE","DELETE","UPDATE")
                            .build(),

                    UserEntity.withUsername("ana")
                            .password("123")
                            .roles("USER")
                            .authorities("READ","CREATE")
                            .build(),

                    UserEntity.withUsername("carlos")
                            .password("123")
                            .roles("ADMIN")
                            .authorities("READ","CREATE","UPDATE","DELETE")
                            .build(),

                    UserEntity.withUsername("lucia")
                            .password("123")
                            .roles("USER")
                            .authorities("READ")
                            .build(),

                    UserEntity.withUsername("admin")
                            .password("123")
                            .roles("ADMIN")
                            .authorities("READ","CREATE","UPDATE","DELETE")
                            .build()
            );

            return new InMemoryUserDetailsManager(users);

        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }
     */
}
