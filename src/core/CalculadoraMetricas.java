package core;

import modelo.EstadoProcesso;
import modelo.Processo;
import java.util.List;

public class CalculadoraMetricas {

    public static void exibirMetricas(List<Processo> processos) {
        if (processos == null || processos.isEmpty()) {
            System.out.println("Nenhum processo para avaliar.");
            return;
        }

        double somaTurnaround = 0;
        double somaTempoResposta = 0;
        int concluidos = 0;

        for (Processo p : processos) {
            if (p.getEstado() == EstadoProcesso.CONCLUIDO) {
                somaTurnaround += p.getTurnaround();
                somaTempoResposta += p.getTempoResposta();
                concluidos++;
            }
        }

        System.out.println("\n=== RESULTADOS E MÉTRICAS ===");
        System.out.println("Processos concluídos com sucesso: " + concluidos);

        if (concluidos > 0) {
            double turnaroundMedio = somaTurnaround / concluidos;
            double respostaMedia = somaTempoResposta / concluidos;

            System.out.printf("Tempo de Retorno Médio (Turnaround): %.2f u.t.\n", turnaroundMedio);
            System.out.printf("Tempo de Resposta Médio: %.2f u.t.\n", respostaMedia);
        }
        System.out.println("=============================\n");
    }
}