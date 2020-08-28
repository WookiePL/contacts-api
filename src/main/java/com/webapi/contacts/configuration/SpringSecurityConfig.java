package com.webapi.contacts.configuration;

import com.webapi.contacts.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    private UserDetailsService userDetailsService;
//
//    public SpringSecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Autowired
//    UserRepository userRepository;
//
//    // Create 2 users for demo
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
////        auth.inMemoryAuthentication()
////                .withUser("user").password("{noop}password").roles("USER")
////                .and()
////                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//            auth.userDetailsService()
//    }
//
//    // Secure the endpoins with HTTP Basic authentication
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/contacts/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/contacts").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/contacts/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/contacts/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/contacts/**").hasRole("ADMIN")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();
//    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return username -> {
////            User myUser = this.userRepository.findByUsername(username);
////            return new MyUserDetails(myUser);
////        };
////    }
//
//    /*@Bean
//    public UserDetailsService userDetailsService() {
//        //ok for demo
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("password").roles("USER").build());
//        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
//        return manager;
//    }*/

    @Autowired
    private WebApplicationContext applicationContext;
    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    private AuthenticationSuccessHandlerImpl successHandler;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(CustomUserDetailsService.class);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(authenticationProvider())
                .jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/resources/**");

        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login")
//                .permitAll()
//                .and()
//                .formLogin()
//                .permitAll()
//                 //  .successHandler(successHandler)
//                .and()
//                .csrf()
//                .disable();

//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/contacts/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/contacts").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/contacts/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/contacts/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/contacts/**").hasRole("ADMIN")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();

//        http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin();

        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // (3)
                .anyRequest().authenticated() // (4)
                .and()
                .formLogin() // (5)
                .loginPage("/login") // (5)
                .permitAll()
                .and()
                .logout() // (6)
                .permitAll()
                .and().csrf().disable()
                .httpBasic(); // (7)

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

//    @Bean
//    public static NoOpPasswordEncoder encoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
