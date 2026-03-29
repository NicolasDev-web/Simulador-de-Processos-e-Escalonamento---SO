package modelo;

public class Processo {
    private int id;
    private int tempoChegada;
    private int tempoExecucaoTotal;
    private int tempoRestante;
    private EstadoProcesso estado;

    private int instantePrimeiraExecucao;
    private int tempoConclusao;

    // Construtor Padrão
    public Processo(int id, int tempoChegada, int tempoExecucaoTotal) {
        this.id = id;
        this.tempoChegada = tempoChegada;
        this.tempoExecucaoTotal = tempoExecucaoTotal;
        this.tempoRestante = tempoExecucaoTotal;
        this.estado = EstadoProcesso.PRONTO;
        this.instantePrimeiraExecucao = -1;
    }

    // Construtor de Cópia (Clonagem para os testes)
    public Processo(Processo p) {
        this.id = p.id;
        this.tempoChegada = p.tempoChegada;
        this.tempoExecucaoTotal = p.tempoExecucaoTotal;
        this.tempoRestante = p.tempoExecucaoTotal;
        this.estado = EstadoProcesso.PRONTO;
        this.instantePrimeiraExecucao = -1;
        this.tempoConclusao = 0;
    }

    public void executarUmCiclo(int tempoAtual) {
        if (this.instantePrimeiraExecucao == -1) {
            this.instantePrimeiraExecucao = tempoAtual;
        }

        this.tempoRestante--;

        if (this.tempoRestante <= 0) {
            this.estado = EstadoProcesso.CONCLUIDO;
            this.tempoConclusao = tempoAtual + 1;
        }
    }

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