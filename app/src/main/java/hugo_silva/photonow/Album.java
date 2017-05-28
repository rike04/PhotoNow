package hugo_silva.photonow;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Album implements Serializable{

    private int id;
    private Map<Integer, Fotografia> fotografias;
    private Boolean privacidade;
    private String descricao;
    private Date data;
    private List<String> comentarios;

    public Album() {

    }


}
