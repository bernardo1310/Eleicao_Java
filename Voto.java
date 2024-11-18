public class Voto {
    private int numeroUrna;
    private int numeroCandidato;

    public Voto(int numeroUrna, int numeroCandidato) {
        this.numeroUrna = numeroUrna;
        this.numeroCandidato = numeroCandidato;
    }

    public int getNumeroUrna() {
        return numeroUrna;
    }

    public int getNumeroCandidato() {
        return numeroCandidato;
    }
}