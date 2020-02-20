package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objetosnegocio.Socio;

public class SociosDAO extends BaseDAO {
    
    public boolean eliminarSocio(Integer idSocio){
        try{
            Connection conn = this.getConnection();
            Statement comando = conn.createStatement();
            String delete = String.format("delete from socios where idsocios = %s", idSocio);
            comando.executeUpdate(delete);
            conn.close();
            return true;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());     
            return false;
        }
    }
    
    public void guardarSocio(Socio socio){
        try{
            Connection conn = this.getConnection();
            Statement comando = conn.createStatement();
            String insert = String.format("insert into socios(dni, nombre) "
                    + "values('%s','%s');" , socio.getDNI(), socio.getNombre());
            comando.executeUpdate(insert);
            conn.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());            
        }
    }
    
    public void actualizarSocio(Socio socio){
        try{
            Connection conn = this.getConnection();
            Statement comando = conn.createStatement();
            String update = String.format("UPDATE socios SET "
                    + "socios.dni = '%s', socios.nombre = '%s' WHERE socios.idsocios = %s;", 
                    socio.getDNI(), socio.getNombre(), socio.getId());
            comando.executeUpdate(update);
            conn.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());            
        }
    }
    
    public Socio obtenerSocioPorId(Integer id){
        try{
            Connection conn = this.getConnection();
            Statement comando = conn.createStatement();           
            String query = String.format("SELECT idsocios,dni,nombre "
                    + "FROM socios WHERE idsocios=%s;", id);
            ResultSet resultado = comando.executeQuery(query);
            if(resultado.next()){
                Socio socio = new Socio();
                socio.setId(resultado.getInt("idsocios"));
                socio.setDNI(resultado.getString("dni"));
                socio.setNombre(resultado.getString("nombre"));
                return socio;
            }
            conn.close();
            return null;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());            
            return null;
        }
    }
    
    public List<Socio> obtenerSocios(String textoBusqueda){
        ArrayList<Socio> socios = new ArrayList<>();
        Connection conn = null;
        try{
            conn = this.getConnection();
            Statement comando = conn.createStatement();
            ResultSet resultado;
            if(textoBusqueda == null || textoBusqueda.isEmpty()){
                resultado = comando.executeQuery("SELECT * FROM socios;");
            }else{
                resultado = comando.executeQuery("SELECT *"
                        + "FROM socios WHERE dni LIKE '%" + textoBusqueda + "%';");
            }            
            while(resultado.next()){
                Socio socio = new Socio();
                socio.setId(resultado.getInt("idsocios"));
                socio.setDNI(resultado.getString("dni"));
                socio.setNombre(resultado.getString("nombre"));
                socios.add(socio);
            }            
            return socios;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }finally{
            try{
                conn.close();
            }catch(SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    
}
