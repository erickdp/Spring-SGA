package mx.com.gm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    Meotod para agregar nuevos usuarios AUTENTICACION
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123") // {noop} es para no codificar la contrasena
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("{noop}123")
                .roles("USER");
    }

//    AUTORIZACION Con este metodo quito el login por default y defino nuevos parametros de acceso, se configura la mayoria al sobrescribirlo
//    Al definir este metodo ya no puedo usar el login por default y debo establecer uno nuevo
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar") // Cualquier path de editar tambien esta restringido
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin() // Definimos nuestra pagina de login personalizada
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403"); // Definimos pagina de errores
    }

}
