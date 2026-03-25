# 💻 Simulador de Escalonamento de Processos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Terminal](https://img.shields.io/badge/CLI-Terminal-4D4D4D?style=for-the-badge&logo=gnu-bash&logoColor=white)

[cite_start]Este projeto é um simulador lógico de gerenciamento de processos e algoritmos de escalonamento[cite: 5, 9]. [cite_start]Construído inteiramente em Java, o sistema opera via terminal (CLI) para demonstrar na prática o ciclo de vida dos processos na CPU e comparar a eficiência de diferentes políticas de escalonamento[cite: 5, 9].

## 🚀 Funcionalidades

* **Máquina de Estados:** Representação fiel do ciclo de vida lógico transitando entre `PRONTO`, `EM_EXECUÇÃO` e `BLOQUEADO`.
* **Gestão de Processos:** Criação, listagem e controle de processos informando tempo de chegada e tempo de execução total.
* **Algoritmos Implementados:** O motor de simulação permite a escolha dinâmica entre três políticas clássicas:
  * **FCFS (First-Come, First-Served):** O primeiro a chegar é o primeiro a ser executado.
  * **Round-Robin:** Escalonamento preemptivo baseado em fatias de tempo (*quantum*) configuráveis pelo usuário.
  * **SJF (Shortest Job First):** Prioriza a execução dos processos com o menor tempo de execução total.
* **Linha do Tempo (Clock Lógico):** Acompanhamento passo a passo no terminal das trocas de contexto e eventos do sistema a cada instante.
* **Motor de Métricas:** Geração automática de relatórios de desempenho ao final da simulação, calculando o Tempo de Retorno Médio (Turnaround) e o Tempo de Resposta Médio.

## 🛠️ Modelagem e Arquitetura

O sistema foi desenhado aplicando conceitos de Orientação a Objetos para garantir modularidade e facilitar a adição de novos algoritmos. O padrão de projeto **Strategy** foi utilizado para isolar as lógicas de escalonamento da classe principal de simulação.

## ⚙️ Como Executar na sua Máquina

**Pré-requisitos:**
* Java Development Kit (JDK) 11 ou superior instalado.

