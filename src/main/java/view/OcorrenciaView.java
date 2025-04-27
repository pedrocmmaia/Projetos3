package view;

import config.DatabaseConfig;
import controller.MoradorController;
import controller.OcorrenciaController;
import model.Ocorrencia;
import model.Morador;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OcorrenciaView {

    public static void menuOcorrencias() {
        try {
            System.out.println("🔗 Iniciando conexão com o banco de dados...");
            DatabaseConfig.criarTabelas();
            Connection connection = DatabaseConfig.getConnection();

            OcorrenciaController ocorrenciaController = new OcorrenciaController(connection); // ✅ agora com conexão
            MoradorController moradorController = new MoradorController(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n📋 Menu de Ocorrências:");
                System.out.println("1 - Cadastrar Ocorrência");
                System.out.println("2 - Listar Ocorrências");
                System.out.println("3 - Atualizar Ocorrência");
                System.out.println("4 - Excluir Ocorrência");
                System.out.println("5 - Buscar por ID");
                System.out.println("6 - Voltar");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Tipo de ocorrência:");
                        System.out.println("1 - Manutenção");
                        System.out.println("2 - Reclamação");
                        System.out.println("3 - Outro");
                        int tipoOpcao = scanner.nextInt();
                        scanner.nextLine();

                        Ocorrencia.TipoOcorrencia tipoOcorrencia = null;
                        switch (tipoOpcao) {
                            case 1: tipoOcorrencia = Ocorrencia.TipoOcorrencia.MANUTENÇÃO; break;
                            case 2: tipoOcorrencia = Ocorrencia.TipoOcorrencia.RECLAMAÇÃO; break;
                            case 3: tipoOcorrencia = Ocorrencia.TipoOcorrencia.OUTRO; break;
                        }
                        System.out.print("Descrição da ocorrência: ");
                        String descricao = scanner.nextLine();

                        System.out.print("ID do morador: ");
                        int moradorId = scanner.nextInt();
                        scanner.nextLine();

                        Morador morador = moradorController.obterMoradorPorId(moradorId);
                        if (morador == null) {
                            System.out.println("❌ Morador com esse ID não existe. Cadastro cancelado.");
                            return;
                        }

                        Ocorrencia ocorrencia = new Ocorrencia(descricao, LocalDateTime.now(),  tipoOcorrencia, Ocorrencia.EstadoOcorrencia.ABERTO, moradorId);
                        ocorrenciaController.adicionarOcorrencia(ocorrencia);

                        System.out.println("✅ Ocorrência cadastrada com sucesso!");
                        break;
                    case 2:
                        ocorrenciaController.listarOcorrencias();
                        break;
                    case 3:
                        System.out.print("ID da ocorrência a atualizarOcorrenciaDao: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        Ocorrencia ocorrenciaExistente = ocorrenciaController.buscarOcorrenciaPorId(idAtualizar);

                        if (ocorrenciaExistente == null){
                            System.out.println("Ocorrência não existe");
                            break;
                        }


                        System.out.print("Nova descrição: ");
                        String novaDescricao = scanner.nextLine();

                        System.out.println("Tipo de ocorreência:");
                        System.out.println("1 - Manutenção");
                        System.out.println("2 - Reclamação");
                        System.out.println("3 - Outro");
                        int tipoOpcaoModificar = scanner.nextInt();
                        scanner.nextLine();

                        Ocorrencia.TipoOcorrencia tipoOcorrenciaModificar = null;
                        switch (tipoOpcaoModificar) {
                            case 1: tipoOcorrenciaModificar = Ocorrencia.TipoOcorrencia.MANUTENÇÃO; break;
                            case 2: tipoOcorrenciaModificar = Ocorrencia.TipoOcorrencia.RECLAMAÇÃO; break;
                            case 3: tipoOcorrenciaModificar = Ocorrencia.TipoOcorrencia.OUTRO; break;
                        }


                        Ocorrencia ocorrenciaModificada = new Ocorrencia(
                                ocorrenciaExistente.getId(),
                                novaDescricao,
                                ocorrenciaExistente.getDataCriacao(),
                                tipoOcorrenciaModificar,
                                ocorrenciaExistente.getEstadoOcorrencia(),
                                ocorrenciaExistente.getMorador()
                        );

                        ocorrenciaController.adicionarOcorrencia(ocorrenciaModificada);
                        break;
                    case 4:
                        System.out.println("Insira o ID da ocorrência que deseja excluir: ");
                        int idExcluir = scanner.nextInt();
                        scanner.nextLine();
                        ocorrenciaController.deletarOcorrencia(idExcluir);
                        break;
                    case 5:
                        System.out.println("Insira o ID da ocorrência que busca");
                        int idBuscar = scanner.nextInt();
                        scanner.nextLine();
                        ocorrenciaController.buscarOcorrenciaPorId(idBuscar);
                        break;
                    case 6:
                        System.out.println("🔙 Retornando ao menu principal...");
                        return;
                    default:
                        System.out.println("⚠️ Opção inválida. Tente novamente.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

}
