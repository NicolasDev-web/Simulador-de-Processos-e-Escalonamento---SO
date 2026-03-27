# Documentação do projeto: Simulador de Processos e Escalonamento

Este documento descreve cada classe presente no projeto, o objetivo de cada componente e o fluxo de execução completo.

## 1. Main.java

### Função
Ponto de entrada do programa CLI (menu principal).

### Comportamento
- Exibe menu:
  - Cenário 1, 2, 3
  - Adicionar processo manual
  - Listar processos
  - Executar simulação
  - Sair
- Cria/repovoa `GerenciadorProcessos` e usa `getProcessos()` para execução.

### Fluxo de execução
1. Escolhe algoritmo (1=FCFS, 2=SJF, 3=RoundRobin)
2. Cria instância do algoritmo (`AlgoritmoEscalonamento`)
3. Replica processos originais com `new Processo(p)` (cópia segura)
4. Chama `executar` do algoritmo
5. Chama `CalculadoraMetricas.exibirMetricas`

## 2. core/GerenciadorProcessos.java

### Função
Repositório simples de processos em memória.

### APIs
- `adicionarProcesso(Processo p)`
- `removerProcesso(int id)`
- `listarProcessos()`
- `List<Processo> getProcessos()`

### Detalhe
`getProcessos()` retorna a lista interna diretamente (mutável), por isso em `Main` o algoritmo recebe clones para não "sujar" cenário original.

## 3. core/CalculadoraMetricas.java

### Função
Cálculo e exibição de métricas pós-simulação.

### Métricas
- Turnaround médio (`average = somaTurnaround / concluidos`)
  - `p.getTurnaround()` (tempoConclusao - tempoChegada)
- Tempo de resposta médio (`average = somaTempoResposta / concluidos`)
  - `p.getTempoResposta()` (instantePrimeiraExecucao - tempoChegada)
- Processos concluídos total

### Requisitos
Processo deve estar em estado `CONCLUIDO` para contar.

## 4. escalonadores/AlgoritmoEscalonamento.java

### Papel
Interface que define o contrato de cada algoritmo.

### Método
- `String getNome()`
- `void executar(List<Processo> processos)`

## 5. escalonadores/FCFS.java

### Estratégia
- Fila FIFO (`LinkedList`)
- Ordena entrada por tempo de chegada
- Unidade de tempo por ciclo
- Não preemptivo (um processo roda até concluir)

### Lógica principal
- Verifica chegadas no instante atual
- Adiciona à fila de prontos
- Escala primeiro da fila se CPU livre
- Executa ciclo com `processo.executarUmCiclo(tempo)`
- Marca concluído e conta

## 6. escalonadores/SJF.java

### Estratégia
- "Shortest Job First", não preemptivo
- Fila de prontos: `PriorityQueue` ordenada por `getTempoExecucaoTotal`
- Entrada ordenada por chegada
- Executa cada processo até finalizar
- Escolhe o menor job disponível, só troca no próximo processo quando CPU livre

## 7. escalonadores/RoundRobin.java

### Estratégia
- Preemptivo com quantum
- Fila FIFO (`LinkedList`)
- Controle de quantum (`tempoNoQuantumAtual`)
- A cada ciclo:
  - se termina, remove
  - se atinge quantum, re-enfileira e muda para `PRONTO`
- Garante fair share rotacional

## 8. modelo/Processo.java

### Dados
- `id`, `tempoChegada`, `tempoExecucaoTotal`
- `tempoRestante` (decrementa em cada ciclo)
- `estado` (`EstadoProcesso`)
- `instantePrimeiraExecucao`
- `tempoConclusao`

### Construtores
- `Processo(int id, int tempoChegada, int tempoExecucaoTotal)`
- `Processo(Processo p)` (clonar; reset = pronto, tempoRestante total, métricas resetadas)

### Execução
- `executarUmCiclo(int tempoAtual)`
  - Seta `instantePrimeiraExecucao` na 1ª vez que ganha CPU
  - `tempoRestante--`
  - se `tempoRestante <= 0`: `estado = CONCLUIDO`, `tempoConclusao = tempoAtual + 1`

### Métricas
- `getTurnaround()`
- `getTempoResposta()`

### toString
- mostra id, chegada, total, restante, estado

## 9. modelo/EstadoProcesso.java

Enum de estados:
- `PRONTO`
- `EM_EXECUCAO`
- `BLOQUEADO` (não usado no momento)
- `CONCLUIDO`

## 10. Recomendações para execução

1. Configure JDK e `PATH` com `java` e `javac`
2. Compilar:
   - `javac src\\**\\*.java` (ou `javac src\\*.java` no contexto)
3. Executar:
   - `java -cp src Main`

## 11. Correção da compilação

Erro inicial: "The constructor Processo(int, int, int) is undefined".
- Corrigido em `modelo/Processo.java` adicionando o construtor principal.
- Permite a criação de processos nos cenários 1,2,3 e entrada manual.

---

Esta documentação foi gerada automaticamente com base no código fonte atual e cobre cada classe principal do projeto.