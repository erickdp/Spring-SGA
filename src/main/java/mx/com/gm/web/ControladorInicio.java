package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.saludo}")
    private String saludo;

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model) {

        List<Persona> personas = (List<Persona>) this.personaService.listarPersonas();

        String mensaje = "Hola mundo con Thymeleaf";
        log.info("ejecutando el controlador Spring MVC");
        log.debug("mas detalle del controlador");

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
//        model.addAttribute("persona", persona);
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors error) {
//        Reiniciar la asignacion de llaves primarias en la entidad de la bbdd
//        alter table nombreTabla AUTO_INCREMENT = 1;
        if (error.hasErrors()) {
            return "modificar";
        }
        this.personaService.guardar(persona);
//        Redirecciona al index
        return "redirect:/";
    }

//    Asocia el parametro que llega
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model modelo) {
        persona = this.personaService.encontrarPersona(persona);
        modelo.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona, Model modelo) {
        this.personaService.eliminar(persona);
        return "redirect:/";
    }

}
