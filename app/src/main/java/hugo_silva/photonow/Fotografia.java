package hugo_silva.photonow;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by rike4 on 27/05/2017.
 */

public class Fotografia implements Serializable{

    private static int sequenciaID = 0;
    private final int id;
    private String descricao;
    private final Date dataUpload;
    private String localizacao;
    private final String pathToImage;

    public Fotografia(String imagem) {
        this.id = sequenciaID + 1;
        dataUpload = new Date();
        descricao = "";
        localizacao = "";
        pathToImage = imagem;
    }

    public int getId() {
        return this.id;
    }

    public String getImagePath() {
        return pathToImage;
    }

}
