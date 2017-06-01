package hugo_silva.photonow;

/**
 * Created by rike4 on 01/06/2017.
 */

public class Encomenda {

    private static int sequenciaID = 0;
    private int id;
    private String tipo;
    private Double preco;
    private final Album album;

    public Encomenda(Double valor, Album album) {
        this.id = sequenciaID + 1;
        sequenciaID++;
        this.album = album;
        atribuiTipo(valor);
        preco = calculaPreco(valor);

    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getPreco() {
        return preco;
    }

    private void atribuiTipo(Double valor) {
        if(valor < 3) {
            tipo = "Pequeno";
        } else if(valor < 5) {
            tipo = "Médio";
        } else {
            tipo = "Grande";
        }
    }

    private Double calculaPreco(Double valor) {
        return album.getNumeroFotos() + valor;
    }
}
