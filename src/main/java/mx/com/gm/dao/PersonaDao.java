package mx.com.gm.dao;

import mx.com.gm.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 @Repository se usaba en la implementacion no en la interfaz por eso ya no se usa
*/
public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
