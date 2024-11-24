package eleicao;

import java.util.*;
import java.util.stream.Collectors;

public class Eleicao {
    private List<Voto> votos;
    private Map<Integer, Partido> partidos;
    private int totalVotosValidos;
    private int totalBrancos;
    private int totalNulos;
    private int numeroCadeiras;

    public Eleicao(int numeroCadeiras) {
        this.votos = new ArrayList<>();
        this.partidos = new HashMap<>();
        this.numeroCadeiras = numeroCadeiras;
        this.totalVotosValidos = 0;
        this.totalBrancos = 0;
        this.totalNulos = 0;
    }

    public void registrarVoto(Voto voto) {
        int candidato = voto.getNumeroCandidato();
        votos.add(voto);

        if (candidato == 0) {
            totalNulos++;
        } else if (candidato == 1) {
            totalBrancos++;
        } else {
            totalVotosValidos++;
            int numeroPartido = candidato / 1000;
            partidos.putIfAbsent(numeroPartido, new Partido(numeroPartido));
            partidos.get(numeroPartido).adicionarVotos(1);
        }
    }

    public double calcularQuocienteEleitoral() { //Criado pois é usado em várias outras métodos
        return (double) totalVotosValidos / numeroCadeiras;
    }

    public void calcularDistribuicaoCadeiras() {
        double quocienteEleitoral = calcularQuocienteEleitoral();
        System.out.println("Quociente Eleitoral: " + quocienteEleitoral);

        for (Partido partido : partidos.values()) {
            int cadeiras = (int) (partido.getVotosTotais() / quocienteEleitoral);
            System.out.println("Partido " + partido.getNumero() + ": " + cadeiras + " cadeiras.");
        }
    }

    public void exibirResumo() {
        System.out.println("Resumo da Eleição:");
        System.out.println("Total de votos: " + votos.size());
        System.out.println("Votos válidos: " + totalVotosValidos);
        System.out.println("Votos brancos: " + totalBrancos + " (" + (double) totalBrancos / votos.size() * 100 + "%)");
        System.out.println("Votos nulos: " + totalNulos + " (" + (double) totalNulos / votos.size() * 100 + "%)");
    }

    public boolean candidatoAtendeClausula(int votosCandidato) {
        double quocienteEleitoral = calcularQuocienteEleitoral();
        return votosCandidato >= 0.1 * quocienteEleitoral;
    }
    

}