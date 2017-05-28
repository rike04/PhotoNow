package hugo_silva.photonow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Repositorio implements Serializable {

    private List<Utilizador> utilizadores;

    public Repositorio() {
        utilizadores = new ArrayList<>();
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(List<Utilizador> u) {
        this.utilizadores = u;
    }

    public Boolean checkUtilizador(String username, String password) {
        if(utilizadores.size() > 0) {
            for(Utilizador u: utilizadores) {
                if(u.getUsername().equals(username)) {
                    if(u.getPassword().equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addUtilizador(Utilizador u) {
        if(u != null) {
            return;
        } else {

            utilizadores.add(u);
        }
    }

}
