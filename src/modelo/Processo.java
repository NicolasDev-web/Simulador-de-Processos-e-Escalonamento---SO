public class Processo {
    // Atributos obrigatórios do trabalho
    private int id;
    private int tempoChegada;
    private int tempoExecucaoTotal;
    private int tempoRestante;
    private EstadoProcesso estado;

    // Atributos auxiliares para cálculo de métricas
    private int instantePrimeiraExecucao;
    private int tempoConclusao;

    // Construtor principal usado em cenários e entrada manual
    public Processo(int id, int tempoChegada, int tempoExecucaoTotal) {
        this.id = id;
        this.tempoChegada = tempoChegada;
        this.tempoExecucaoTotal = tempoExecucaoTotal;
        this.tempoRestante = tempoExecucaoTotal;
        this.estado = EstadoProcesso.PRONTO;
        this.instantePrimeiraExecucao = -1;
        this.tempoConclusao = 0;
    }

    // Construtor de cópia para não poluir os cenários de teste
    public Processo(Processo p) {
        this.id = p.id;
        this.tempoChegada = p.tempoChegada;
        this.tempoExecucaoTotal = p.tempoExecucaoTotal;
        this.tempoRestante = p.tempoExecucaoTotal; // Reseta o tempo
        this.estado = EstadoProcesso.PRONTO;       // Reseta o estado
        this.instantePrimeiraExecucao = -1;        // Reseta as métricas //Fiz essa mudança por conta que senão ia acabar sujando o processo com dados de outros processos, sacou misera. tenho que apagar isso dps plmds
        this.tempoConclusao = 0;
    }

    // Método que simula 1 unidade de tempo na CPU
    public void executarUmCiclo(int tempoAtual) {
        // Registra o tempo de resposta na primeira vez que ganha a CPU.
        // Explicação braba: Em vez do simulador subtrair o tempo manualmente, ele manda o processo se executar.
        if (this.instantePrimeiraExecucao == -1) {
            this.instantePrimeiraExecucao = tempoAtual;
        }

        this.tempoRestante--;

        if (this.tempoRestante <= 0) {
            this.estado = EstadoProcesso.CONCLUIDO;
            this.tempoConclusao = tempoAtual + 1; // +1 porque concluiu ao final deste ciclo
        }
    }

    // Fórmulas de métricas encapsuladas no próprio objeto
    public int getTurnaround() {
        return tempoConclusao - tempoChegada;
    }

    public int getTempoResposta() {
        return instantePrimeiraExecucao - tempoChegada;
    }


    public int getId() { return id; }
    public int getTempoChegada() { return tempoChegada; }
    public int getTempoExecucaoTotal() { return tempoExecucaoTotal; }
    public int getTempoRestante() { return tempoRestante; }
    public EstadoProcesso getEstado() { return estado; }
    public void setEstado(EstadoProcesso estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "P" + id + " [Chegada: " + tempoChegada + ", Total: " + tempoExecucaoTotal + ", Restante: " + tempoRestante + ", Estado: " + estado + "]";
    }
}