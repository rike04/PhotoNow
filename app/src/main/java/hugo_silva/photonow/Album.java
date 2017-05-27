package hugo_silva.photonow;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Album {

    private int id;
    private Map<Integer, Fotografia> fotografias;
    private String descricao;
    private Date data;
    private List<String> comentarios;

    public Album() {

    }


}
