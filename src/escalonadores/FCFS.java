package escalonadores;

import modelo.EstadoProcesso;
import modelo.Processo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FCFS implements AlgoritmoEscalonamento {

    @Override
    public String getNome() {
        return "FCFS (First-Come, First-Served)";
    }

    @Override
    public void executar(List<Processo> processos) {
        int tempo = 0;
        int concluidos = 0;
        int totalProcessos = processos.size();

        List<Processo> processosNaoChegados = new ArrayList<>(processos);
        processosNaoChegados.sort(Comparator.comparingInt(Processo::getTempoChegada));

        Queue<Processo> filaProntos = new LinkedList<>();
        Processo processoNaCPU = null;

        System.out.println("\n--- Iniciando Simulação: " + getNome() + " ---");

        while (concluidos < totalProcessos) {
            List<Processo> chegadasAgora = new ArrayList<>();
            for (Processo p : processosNaoChegados) {
                if (p.getTempoChegada() == tempo) {
                    p.setEstado(EstadoProcesso.PRONTO);
                    filaProntos.add(p);
                    chegadasAgora.add(p);
                    System.out.println("tempo " + tempo + ": processo " + p.getId() + " chegou à fila de prontos");
                }
            }
            processosNaoChegados.removeAll(chegadasAgora);

            if (processoNaCPU == null && !filaProntos.isEmpty()) {
                processoNaCPU = filaProntos.poll();
                processoNaCPU.setEstado(EstadoProcesso.EM_EXECUCAO);
                System.out.println("tempo " + tempo + ": processo " + processoNaCPU.getId() + " entrou em execução");
            }

            if (processoNaCPU != null) {
                if (processoNaCPU.getTempoExecucaoTotal() != processoNaCPU.getTempoRestante()) {
                    System.out.println("tempo " + tempo + ": processo " + processoNaCPU.getId() + " continua em execução");
                }

                processoNaCPU.executarUmCiclo(tempo);

                if (processoNaCPU.getEstado() == EstadoProcesso.CONCLUIDO) {
                    System.out.println("tempo " + (tempo + 1) + ": processo " + processoNaCPU.getId() + " concluído");
                    processoNaCPU = null;
                    concluidos++;
                }
            } else {
                System.out.println("tempo " + tempo + ": CPU ociosa");
            }
            tempo++;
        }
        System.out.println("--- Fim da Simulação ---\n");
    }
}