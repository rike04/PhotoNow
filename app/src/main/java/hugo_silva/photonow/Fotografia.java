package hugo_silva.photonow;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by rike4 on 27/05/2017.
 */

public class Fotografia implements Serializable{

    private int id;
    private String descricao;
    private Date dataUpload;
    private String localizacao;
    private Image imagem;


}
