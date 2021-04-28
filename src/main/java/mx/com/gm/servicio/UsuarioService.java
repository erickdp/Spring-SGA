package mx.com.gm.servicio;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        
//        Carga los roles en una clase envolvente
        List<GrantedAuthority> roles = new ArrayList<>();
        
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
//        El usuario se crea mediante una propia clase de spring
        return new User(usuario.getNombreUsuario(), usuario.getContrasena(), roles);

    }

}
