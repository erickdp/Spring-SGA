package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Persona;

public interface PersonaService {
    
    List<Persona> listarPersonas();
    
    void guardar(Persona persona);
    
    void eliminar(Persona persona);
    
    Persona encontrarPersona(Persona persona);
    
}
