package hugo_silva.photonow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Album implements Serializable{

    private static int sequenciaIDAlbum = 0;
    private int id;
    private String pathToCapa;
    private ArrayList<Fotografia> fotografias;
    private String titulo;
    private Boolean privado;
    private String descricao;
    private Date data;
    private List<String> comentarios;

    public Album(String titulo,String capa) {
        this.id = sequenciaIDAlbum + 1;
        sequenciaIDAlbum++;
        this.titulo = titulo;
        fotografias = new ArrayList<>();
        privado = true;
        descricao = null;
        data = new Date();
        comentarios = new ArrayList<>();
        pathToCapa = capa;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPrivado() {
        return privado;
    }

    public int getNumeroFotos() {
        return fotografias.size();
    }

    public void addFotografia(String imagem) {
        if(imagem != null) {
            Fotografia foto = new Fotografia(imagem);
            fotografias.add(foto);
        }
    }

    public ArrayList<Fotografia> getAllPhotos() {
        return fotografias;
    }

    public String getPathToCapa() {
        return pathToCapa;
    }
}
