package hugo_silva.photonow;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Utilizador implements Serializable {

    private final int id;
    private String username;
    private String password;
    private String morada;
    private char[] nif;
    private char[] telemovel;
    private List<Album> albuns;
    private List<Encomenda> encomendas;

    public Utilizador(String username, String password, int id) {
        this.id = id;
        this.username = username;
        this.password = password;
        albuns = new ArrayList<>();
        encomendas = new ArrayList<>();
        nif = new char[7];
        telemovel = new char[9];
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

    public Album getAlbumbyId(int id) {
        for(Album a: albuns) {
            if(id == a.getId()) {
                return a;
            }
        }
        return null;
    }

    public void addAlbum(Album album) {
        if(album != null) {
            getAlbuns().add(album);
        }
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                '}';
    }

    public int countAlbunsPrivados() {
        int contador = 0;
        for (Album a: albuns) {
             if (a.getPrivado()) contador++;
        }
        return contador;
    }

    public int countAlbunsPublicos() {
        int contador = 0;
        for (Album a: albuns) {
            if (!a.getPrivado()) contador++;
        }
        return contador;
    }

    public List<Album> getAlbunsPublicos() {
        List<Album> albunsPublicos = new ArrayList<>();

        for(Album a: albuns){
            if(!a.getPrivado()) {
                albunsPublicos.add(a);
            }
        }
        return albunsPublicos;
    }

    public List<Album> getAlbunsPrivados() {
        List<Album> albunsPrivados = new ArrayList<>();

        for(Album a: albuns){
            if(a.getPrivado()) {
                albunsPrivados.add(a);
            }
        }
        return albunsPrivados;
    }

    public Boolean tituloJaExiste(String titulo) {
        for (Album a: albuns) {
             if(a.getTitulo().equals(titulo)) {
                 return false;
             }
        }
        return true;
    }

    public ArrayList<Fotografia> getAllPhotos() {
        ArrayList<Fotografia> lista = new ArrayList<>();
        for(Album a: albuns) {
            lista = a.getAllPhotos();
        }
        return lista;
    }

    public void setListaAlbuns(ArrayList<Album> novaLista) {
        this.albuns = novaLista;
    }

    public void addEncomenda(Encomenda e) {
        if(!encomendas.contains(e)) {
            encomendas.add(e);
        }
    }

    public List<Encomenda> getEncomendas() {
        return encomendas;
    }

    public Fotografia getFotografia(int id) {
        for(Album a: albuns) {
            for(Fotografia f: a.getAllPhotos()) {
                if(id == f.getId()) {
                    return f;
                }
            }
        }
        return null;
    }

}
