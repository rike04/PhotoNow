package hugo_silva.photonow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Utilizador {

    private final int id;
    private String username;
    private String password;
    List<Album> albuns;

    public Utilizador(String username, String password, int id) {
        this.id = id;
        this.username = username;
        this.password = password;
        albuns = new ArrayList<>();

    }

    public Utilizador() {
        this.id = 1;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }
}
