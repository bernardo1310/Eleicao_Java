package eleicao;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = "D:\\eleiccao\\eleicao\\src\\eleicao\\votos.txt";
        Eleicao eleicao = new Eleicao(10);

        try {
            Leitura.carregarVotos(caminhoArquivo, eleicao);
            eleicao.exibirResumo();
            eleicao.calcularDistribuicaoCadeiras();
            eleicao.listarEleitos();
            eleicao.exibirResultadosPorLegenda();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}