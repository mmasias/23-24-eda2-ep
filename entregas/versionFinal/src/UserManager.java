import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<Usuario> usuarios;

    public UserManager() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null; 
    }
}
