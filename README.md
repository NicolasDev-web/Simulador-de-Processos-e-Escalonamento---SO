# 💻 Simulador de Escalonamento de Processos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Terminal](https://img.shields.io/badge/CLI-Terminal-4D4D4D?style=for-the-badge&logo=gnu-bash&logoColor=white)

Este projeto é um simulador lógico de gerenciamento de processos e algoritmos de escalonamento. Construído inteiramente em Java, o sistema opera via terminal (CLI) para demonstrar na prática o ciclo de vida dos processos na CPU e comparar a eficiência de diferentes políticas de escalonamento.

## 🚀 Funcionalidades

* **Máquina de Estados:** Representação fiel do ciclo de vida lógico transitando entre `PRONTO`, `EM_EXECUÇÃO` e `BLOQUEADO`.
* **Gestão de Processos:** Criação, listagem e controle de processos informando tempo de chegada e tempo de execução total.
* **Algoritmos Implementados:** O motor de simulação permite a escolha dinâmica entre três políticas clássicas:
  * **FCFS (First-Come, First-Served):** O primeiro a chegar é o primeiro a ser executado.
  * **SJF (Shortest Job First):** Prioriza a execução dos processos com o menor tempo de execução total, utilizando Fila de Prioridade.
  * **Round-Robin:** Escalonamento preemptivo baseado em fatias de tempo (*quantum*) configuráveis pelo usuário.
* **Linha do Tempo (Clock Lógico):** Acompanhamento passo a passo no terminal das trocas de contexto e eventos do sistema a cada instante.
* **Motor de Métricas:** Geração automática de relatórios de desempenho ao final da simulação, calculando o Tempo de Retorno Médio (Turnaround) e o Tempo de Resposta Médio.

## 🏗️ Arquitetura e Estrutura de Diretórios

O projeto foi refatorado utilizando o padrão de projeto **Strategy** e está organizado de forma modular em pacotes para separar responsabilidades:

```text
src/
├── modelo/
│   ├── EstadoProcesso.java        # Enumeração da máquina de estados
│   └── Processo.java              # Entidade principal com construtor de cópia (Clonagem)
├── escalonadores/
│   ├── AlgoritmoEscalonamento.java # Interface do padrão Strategy
│   ├── FCFS.java                  # Implementação do First-Come, First-Served
│   ├── SJF.java                   # Implementação do Shortest Job First
│   └── RoundRobin.java            # Implementação do Round Robin preemptivo
├── core/
│   ├── GerenciadorProcessos.java  # Banco de dados em memória para gestão dos cadastros
│   └── CalculadoraMetricas.java   # Motor de consolidação matemática (Turnaround/Resposta)
└── Main.java                      # Ponto de entrada e Menu Textual CLI
