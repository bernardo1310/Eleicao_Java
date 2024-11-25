package eleicao;

import java.util.*;
import java.util.stream.Collectors;

public class Eleicao {
    protected List<Voto> votos;
    protected Map<Integer, Partido> partidos;
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
        System.out.println("Votos válidos: " + totalVotosValidos+ " (" + (double) totalVotosValidos / votos.size() * 100 + "%)");
        System.out.println("Votos brancos: " + totalBrancos + " (" + (double) totalBrancos / votos.size() * 100 + "%)");
        System.out.println("Votos nulos: " + totalNulos + " (" + (double) totalNulos / votos.size() * 100 + "%)");
    }

    public boolean candidatoAtendeClausula(int votosCandidato) {
        double quocienteEleitoral = calcularQuocienteEleitoral();
        return votosCandidato >= 0.1 * quocienteEleitoral;
    }
    
    public void listarEleitos() {
        List<Voto> votosValidos = votos.stream()
                .filter(v -> v.getNumeroCandidato() > 1) // Apenas votos válidos
                .collect(Collectors.toList());

        // Mapeamento de candidatos para contagem de votos
        Map<Integer, Integer> votosPorCandidato = new HashMap<>();
        for (Voto voto : votosValidos) {
            votosPorCandidato.put(voto.getNumeroCandidato(),
                    votosPorCandidato.getOrDefault(voto.getNumeroCandidato(), 0) + 1);
        }

        // Lista de candidatos ordenada por votos
        List<Map.Entry<Integer, Integer>> candidatosOrdenados = votosPorCandidato.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue() - c1.getValue()) // Ordenar por votos
                .collect(Collectors.toList());

        // Selecionar os 10 eleitos
        System.out.println("Candidatos eleitos:");
        int eleitos = 0;
        int sair = 0;
        for (Map.Entry<Integer, Integer> candidato : candidatosOrdenados) {
            if (eleitos < 10) {
                System.out.println("Candidato: " + candidato.getKey() + " - Votos: " + candidato.getValue());
                eleitos++;
            }
            else {
            	if (sair == 0) {
            		System.out.println("\nDemais candidatos:");
            		sair++;
            	}
            	System.out.println("Candidato: " + candidato.getKey() + " - Votos: " + candidato.getValue());
            }
        }
        
    }
    public void exibirResultadosPorLegenda() {
        int totalVotos = votos.size();

        System.out.println("\nResultados por legenda (partido):");
        for (Partido partido : partidos.values()) {
            int votosPartido = partido.getVotosTotais();
            double percentual = (double) votosPartido / totalVotos * 100;
            System.out.println("Partido " + partido.getNumero() + " - Votos: " + votosPartido + " (" + percentual + "%)");
        }
    }

}