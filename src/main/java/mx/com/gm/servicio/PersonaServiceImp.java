package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

//    Como solo leemos debemos especificar que es transaccional y readOnly
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) this.personaDao.findAll();
    }

//    Se crea una nueva transaccion si no existe una
    @Override
    @Transactional
    public void guardar(Persona persona) {
        this.personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        this.personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return this.personaDao.findById(persona.getIdPersona()).orElse(null);
    }

}
