package pt.unl.fct.tourtuga.gas.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
class WebSecurityConfig(@Qualifier("UserDetailService") @Autowired val userDetailsService: UserDetailsService): WebSecurityConfigurerAdapter() {

    /*@Qualifier("UserDetailService")
    @Autowired
    lateinit var userDetailsService:UserDetailsService ;*/

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers("/students/", "/login")
                    .permitAll()
                    .antMatchers("/applications/taste").hasRole("STUDENT")
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(JwtAuthenticationFilter(authenticationManager()))
                    .addFilter(JwtAuthorizationFilter(authenticationManager()))
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

    @Override
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(passwordEncoder());
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder();
    }
    @Bean
    fun corsConfigurationSource():CorsConfigurationSource{
        val source:UrlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}