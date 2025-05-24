package view;

import config.DatabaseConfig;
import controller.ComunicadoController;
import controller.PagamentoController;
import controller.UsuarioController;


import java.sql.SQLException;
import java.util.Scanner;
import model.Usuario;

public class MoradorView {
    public static void menuMorador(Usuario usuario) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController(DatabaseConfig.getConnection());
        ComunicadoController comunicadoController = new ComunicadoController(DatabaseConfig.getConnection());
        PagamentoController pagamentoController = new PagamentoController(DatabaseConfig.getConnection());

        int opcao;

        do {
            System.out.println("\n===== MENU MORADOR =====");
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("1. Editar dados da conta");
            System.out.println("2. Menu Ocorrências");
            System.out.println("3. Menu Reserva");
            System.out.println("4. Visualizar cobranças");
            System.out.println("5. Comunicados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                   int idUsuario = usuario.getId();
                     Usuario existente = usuarioController.obterUsuarioPorId(idUsuario);
                if (existente == null) {
                    System.out.println("Usuário não encontrado!");
                    break;
                }

                System.out.print("Novo nome (" + existente.getNome() + "): ");
                String nome = scanner.nextLine();
                nome = nome.isEmpty() ? existente.getNome() : nome;

                System.out.print("Novo email (" + existente.getEmail() + "): ");
                String email = scanner.nextLine();
                email = email.isEmpty() ? existente.getEmail() : email;

                System.out.print("Nova senha: ");
                String senha = scanner.nextLine();
                senha = senha.isEmpty() ? existente.getSenha() : senha;

                System.out.print("Novo telefone (" + existente.getTelefone() + "): ");
                String telefone = scanner.nextLine();
                telefone = telefone.isEmpty() ? existente.getTelefone() : telefone;

                usuarioController.atualizarUsuario(
                        existente.getId(), nome, email, senha, telefone, existente.getTipoUsario()
                );
                
                break;
                case 2:
                 OcorrenciaView.menuOcorrencias(usuario);
                break;
                 //case 3:
                 // ReservasView.menuReservas();
                //break;
                case 4:
                    pagamentoController.listarPagamentos();
                break;
                case 5:
                 comunicadoController.listarComunicados();
                break;
                 //case 0: 
                 //System.out.println("Saindo...");
                //break;
                 default:
                 System.out.println("Opção inválida.");
                break;
            }
        } while (opcao != 0);
    }
}
