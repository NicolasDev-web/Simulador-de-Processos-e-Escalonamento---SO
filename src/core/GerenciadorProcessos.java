package core;

import modelo.Processo;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProcessos {
    private List<Processo> listaProcessos;

    public GerenciadorProcessos() {
        this.listaProcessos = new ArrayList<>();
    }

    public void adicionarProcesso(Processo p) {
        listaProcessos.add(p);
        System.out.println("Processo " + p.getId() + " criado com sucesso!");
    }

    public void removerProcesso(int id) {
        boolean removido = listaProcessos.removeIf(p -> p.getId() == id);
        if (removido) {
            System.out.println("Processo " + id + " removido.");
        } else {
            System.out.println("Processo não encontrado.");
        }
    }

    public void listarProcessos() {
        if (listaProcessos.isEmpty()) {
            System.out.println("Nenhum processo cadastrado.");
            return;
        }
        for (Processo p : listaProcessos) {
            System.out.println(p.toString());
        }
    }

    public List<Processo> getProcessos() {
        return listaProcessos;
    }
}