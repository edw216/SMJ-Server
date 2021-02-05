package com.experiencers.server.smj.configuration;


import com.experiencers.server.smj.configuration.support.SignInFailureHandler;
import com.experiencers.server.smj.configuration.support.SignInSuccessHandler;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.enumerate.Role;
import com.experiencers.server.smj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Configuration
    @RequiredArgsConstructor
    @Order(1)
    public static class FormSecurityConfigurer extends WebSecurityConfigurerAdapter{
        private static final String LOGIN_URL_PATH = "/auth/sign_in";
        private static final String LOGIN_PROCESS_URL_PATH = "/auth/validation";
        private static final String LOGOUT_URL_PATH = "/auth/sign_out";
        private static final String SUCCESS_REDIRECT_URL = "/";
        private static final String USERNAME_KEY = "email";
        private static final String PASSWORD_KEY = "pw";

        @Autowired
        private MemberRepository memberRepository;

        private final JwtAuthTokenProvider jwtTokenProvider;
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("start basic security");
            http
                    .authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                    UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(
                            "/static/**",
                            "/**/favicon.ico",
                            "/v2/api-docs",
                            "/error",
                            LOGIN_URL_PATH).permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().usernameParameter(USERNAME_KEY) // Parameter is received from view
                    .passwordParameter(PASSWORD_KEY)
                    .loginPage(LOGIN_URL_PATH)
                    .loginProcessingUrl(LOGIN_PROCESS_URL_PATH) // Request receiving from form submit
                    .successHandler(new SignInSuccessHandler(SUCCESS_REDIRECT_URL))
                    .failureHandler(new SignInFailureHandler(LOGIN_URL_PATH))
                    .and()
                    .logout().logoutUrl(LOGOUT_URL_PATH)
                    .clearAuthentication(true) // Authentication object remove from securityContext
                    .invalidateHttpSession(true) // HttpSession remove
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl(LOGIN_URL_PATH);

            /*http
                    .httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);
                    /*.authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);*/
        }

        @Override
        protected AuthenticationManager authenticationManager() throws Exception {
            return new AuthenticationManager() {
                @Override
                public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                    String email = (String) authentication.getPrincipal();
                    Member member = memberRepository.findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException(email));

                    List<GrantedAuthority> roles = new ArrayList<>();
                    roles.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
                    return new UsernamePasswordAuthenticationToken(member.getEmail(), "", roles);
                }
            };
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                    System.out.println(email);
                    Member member = memberRepository.findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException(email));

                    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
                    //jwtTokenProvider.createToken(email,"ROLE_ADMIN");
                    return new User(member.getEmail(), "", grantedAuthorities);
                }
            };
        }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/static/**",
                            "/**/favicon.ico",
                            "/h2-console/**",
                            "/webjars/**",
                            "/api/**"
                    );
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        }
    }
    /*
    @RequiredArgsConstructor
    @Configuration
    @Order(2)
    public static class JwtSecurity extends WebSecurityConfigurerAdapter{

        private final JwtAuthTokenProvider jwtTokenProvider;
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("start jwt security");
            http
                    .httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                    UsernamePasswordAuthenticationFilter.class);
                    /*.authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);
        }
    }*/



}
