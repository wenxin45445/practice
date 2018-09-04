package com.meng.configuration;
//
//package com.example.readinglist;
//
//import com.example.readinglist.ReaderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.Arrays;
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private ReaderRepository readerRepository;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        // 禁用csrf，放开post，也可以在模板中使用token
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/").access("hasRole('READER')")
//                .antMatchers("/**").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=true");
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return readerRepository.findById(username).orElse(null);
//            }
//        });
//    }
//
//    @Configuration
//    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                    .passwordEncoder(new BCryptPasswordEncoder())
//                    .withUser("meng")
//                    .password(new BCryptPasswordEncoder().encode("meng"))
//                    .roles("ADMIN");
//
//            auth.inMemoryAuthentication()
//                    .passwordEncoder(new BCryptPasswordEncoder())
//                    .withUser("craig3")
//                    .password(new BCryptPasswordEncoder().encode("password")).
//                    roles("ADMIN");
//
//            auth.inMemoryAuthentication()
//                    .passwordEncoder(new BCryptPasswordEncoder())
//                    .withUser("user")
//                    .password(new BCryptPasswordEncoder().encode("password"))
//                    .roles("ADMIN");
//        }
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
//        UserDetails userDetails = new User("craig3", "password", Arrays.asList(authority));
//        return new InMemoryUserDetailsManager(Arrays.asList(userDetails));
//    }
//}
//
