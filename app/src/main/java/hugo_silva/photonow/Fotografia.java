package hugo_silva.photonow;

import android.graphics.Bitmap;
import android.os.Bundle;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by rike4 on 27/05/2017.
 */

public class Fotografia implements Serializable{

    private static int sequenciaID = 0;
    private final int id;
    private String descricao;
    private Date dataUpload;
    private String localizacao;
    private Bitmap imagem;

    public Fotografia(Bitmap imagem) {
        this.imagem = imagem;
        this.id = sequenciaID + 1;
        dataUpload = new Date();
        descricao = "";
        localizacao = "";
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public int getId() {
        return this.id;
    }

}
