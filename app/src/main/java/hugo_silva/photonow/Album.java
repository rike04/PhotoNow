package hugo_silva.photonow;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Album implements Serializable{

    private int id;
    private Bitmap capa;
    private Map<Integer, Fotografia> fotografias;
    private String titulo;
    private Boolean privado;
    private String descricao;
    private Date data;
    private List<String> comentarios;

    public Album(String titulo,Bitmap capa ) {
        this.id += 1;
        this.titulo = titulo;
        fotografias = new HashMap<>();
        privado = true;
        descricao = null;
        data = new Date();
        comentarios = new ArrayList<>();
        this.capa = capa;
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

    public Bitmap getImagemCapa() {
        return capa;
    }

    public void addFotografia(Bitmap imagem) {
        Fotografia foto;
        if(fotografias.isEmpty()) {

        } else {

        }
    }

}
