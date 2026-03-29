package escalonadores;

import modelo.Processo;
import java.util.List;

public interface AlgoritmoEscalonamento {
    String getNome();
    void executar(List<Processo> processos);
}