package controller;

import dao.BlocoDAO;
import model.Bloco;

import java.util.List;

public class BlocoController {
    private BlocoDAO blocoDAO;

    public BlocoController() {
        this.blocoDAO = new BlocoDAO();
    }

    // Cadastrar um novo bloco
    public void cadastrarBloco(String nome) {
        Bloco bloco = new Bloco(nome);
        blocoDAO.cadastrarBloco(bloco);
        System.out.println("✅ Bloco cadastrado com sucesso!");
    }

    // Listar todos os blocos
    public List<Bloco> listarBlocos() {
        return blocoDAO.listarBlocos();
    }

    // Atualizar bloco
    public void atualizarBloco(int id, String nome) {
        Bloco bloco = new Bloco(id, nome);
        blocoDAO.atualizarBloco(bloco);
        System.out.println("✅ Bloco atualizado com sucesso!");
    }

    // Excluir bloco pelo ID
    public void excluirBloco(int id) {
        blocoDAO.excluirBloco(id);
        System.out.println("✅ Bloco excluído com sucesso!");
    }
}
