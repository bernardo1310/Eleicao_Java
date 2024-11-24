package eleicao;

public class Partido {
    private int numero;
    private int votosTotais;

    public Partido(int numero) {
        this.numero = numero;
        this.votosTotais = 0;
    }

    public void adicionarVotos(int votos) {
        votosTotais += votos;
    }

    public int getNumero() {
        return numero;
    }

    public int getVotosTotais() {
        return votosTotais;
    }
}