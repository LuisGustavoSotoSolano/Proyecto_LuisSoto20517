package negocio;

import datos.SociosDAO;
import java.util.List;

import objetosnegocio.Socio;

public class Control {

    private final SociosDAO sociosDAO;

    public Control() {
        this.sociosDAO = new SociosDAO();
    }

    //SOCIOS//
    public List<Socio> obtenerSocios(String textoBusqueda) {
        return this.sociosDAO.obtenerSocios(textoBusqueda);
    }

    public void guardarSocio(Socio socio) {
        this.sociosDAO.guardarSocio(socio);
    }

    public void actualizarSocio(Socio socio) {
        this.sociosDAO.actualizarSocio(socio);
    }

    public Socio obtenerSocioPorId(Integer id) {
        return this.sociosDAO.obtenerSocioPorId(id);
    }

    public boolean eliminarSocio(Integer id) {
        return this.sociosDAO.eliminarSocio(id);
    }
}