import core.CalculadoraMetricas;
import core.GerenciadorProcessos;
import escalonadores.AlgoritmoEscalonamento;
import escalonadores.FCFS;
import escalonadores.RoundRobin;
import escalonadores.SJF;
import modelo.Processo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorProcessos gerenciador = new GerenciadorProcessos();
        boolean rodando = true;

        System.out.println("=========================================");
        System.out.println("  SIMULADOR DE ESCALONAMENTO DE PROCESSOS  ");
        System.out.println("=========================================");

        while (rodando) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Carregar Cenário de Teste 1 (Processos Curtos e Semelhantes)");
            System.out.println("2. Carregar Cenário de Teste 2 (Chegadas Distantes)");
            System.out.println("3. Carregar Cenário de Teste 3 (Processos Muito Diferentes)");
            System.out.println("4. Adicionar Processo Manualmente");
            System.out.println("5. Listar Processos Cadastrados");
            System.out.println("6. Executar Simulação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciador = new GerenciadorProcessos();
                    gerenciador.adicionarProcesso(new Processo(1, 0, 3));
                    gerenciador.adicionarProcesso(new Processo(2, 1, 2));
                    gerenciador.adicionarProcesso(new Processo(3, 2, 4));
                    System.out.println("Cenário 1 carregado.");
                    break;
                case 2:
                    gerenciador = new GerenciadorProcessos();
                    gerenciador.adicionarProcesso(new Processo(1, 0, 5));
                    gerenciador.adicionarProcesso(new Processo(2, 4, 3));
                    gerenciador.adicionarProcesso(new Processo(3, 10, 2));
                    System.out.println("Cenário 2 carregado.");
                    break;
                case 3:
                    gerenciador = new GerenciadorProcessos();
                    gerenciador.adicionarProcesso(new Processo(1, 0, 20));
                    gerenciador.adicionarProcesso(new Processo(2, 1, 2));
                    gerenciador.adicionarProcesso(new Processo(3, 2, 1));
                    System.out.println("Cenário 3 carregado.");
                    break;
                case 4:
                    System.out.print("ID do Processo: ");
                    int id = scanner.nextInt();
                    System.out.print("Tempo de Chegada: ");
                    int chegada = scanner.nextInt();
                    System.out.print("Tempo de Execução Total: ");
                    int exec = scanner.nextInt();
                    gerenciador.adicionarProcesso(new Processo(id, chegada, exec));
                    break;
                case 5:
                    gerenciador.listarProcessos();
                    break;
                case 6:
                    if (gerenciador.getProcessos().isEmpty()) {
                        System.out.println("Nenhum processo cadastrado. Adicione ou carregue um cenário.");
                        break;
                    }
                    executarMenuSimulacao(scanner, gerenciador.getProcessos());
                    break;
                case 0:
                    rodando = false;
                    System.out.println("Encerrando simulador...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private static void executarMenuSimulacao(Scanner scanner, List<Processo> originais) {
        System.out.println("\n--- ESCOLHA O ALGORITMO ---");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. Round Robin");
        System.out.print("Opção: ");
        int algOpcao = scanner.nextInt();

        AlgoritmoEscalonamento algoritmo = null;

        if (algOpcao == 1) {
            algoritmo = new FCFS();
        } else if (algOpcao == 2) {
            algoritmo = new SJF();
        } else if (algOpcao == 3) {
            System.out.print("Informe o valor do Quantum: ");
            int quantum = scanner.nextInt();
            algoritmo = new RoundRobin(quantum);
        } else {
            System.out.println("Algoritmo inválido.");
            return;
        }

        List<Processo> processosParaExecucao = new ArrayList<>();
        for (Processo p : originais) {
            processosParaExecucao.add(new Processo(p));
        }

        algoritmo.executar(processosParaExecucao);
        CalculadoraMetricas.exibirMetricas(processosParaExecucao);
    }
}