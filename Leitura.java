package eleicao;

import java.io.*;

public class Leitura {
    public static void carregarVotos(String caminhoArquivo, Eleicao eleicao) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int numeroUrna = Integer.parseInt(partes[0]);
                int numeroCandidato = Integer.parseInt(partes[1]);
                eleicao.registrarVoto(new Voto(numeroUrna, numeroCandidato));
            }
        }
    }
}