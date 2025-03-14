package br.com.projetobase.application.config;

import br.com.projetobase.application.config.auth.CustomUserDetailService;
import br.com.projetobase.application.config.auth.JwtAuthFilter;
import br.com.projetobase.application.config.auth.JwtService;
import br.com.projetobase.domain.entity.RolesEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService customUserDetailService;
    private JwtService jwtService;
    private Environment environment;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, customUserDetailService, environment);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (environment.getProperty("spring.profiles.active").equals("test")) {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll();
            return;

        }

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .antMatchers("/usuarios/**").hasAnyRole(RolesEntity.EnumRole.USUARIO_COMUM.name(), RolesEntity.EnumRole.ADMINISTRADOR.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/login",
//                "/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/condiguration/security",
//                "/swagger-ui.html",
//                "/webjars/**")
//                .antMatchers(HttpMethod.POST, "/usuarios");
//    }

}
