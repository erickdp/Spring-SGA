package mx.com.gm.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        Al definir el parametro de lang en las urls se especifica el idioma a usar
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

//    Mapeo de paths que no pasan por el controlador
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        Este parametro es para acceder a la ruta inicial localhost:8080 sin roles pero debe pasar por un login se debe definir una ruta para el login
//        registry.addViewController("/").setViewName("index");
//        Este parametro es para acceder al form de login personalizado
        registry.addViewController("/login");
        registry.addViewController("/errores/403").setViewName("/errores/403");
    }

}
