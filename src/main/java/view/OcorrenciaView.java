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

    public static void menuOcorrencias(Usuario usuarioLogado) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            OcorrenciaController ocorrenciaController = new OcorrenciaController(connection);
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

                        Ocorrencia.TipoOcorrencia tipoOcorrencia = switch (tipoOpcao) {
                            case 1 -> Ocorrencia.TipoOcorrencia.MANUTENÇÃO;
                            case 2 -> Ocorrencia.TipoOcorrencia.RECLAMAÇÃO;
                            case 3 -> Ocorrencia.TipoOcorrencia.OUTRO;
                            default -> null;
                        };

                        System.out.print("Descrição da ocorrência: ");
                        String descricao = scanner.nextLine();

                        Morador morador = moradorController.obterMoradorPorUsuarioId(usuarioLogado.getId());
                        if (morador == null) {
                            System.out.println("❌ Morador não encontrado. Cadastro cancelado.");
                            break;
                        }

                        Ocorrencia novaOcorrencia = new Ocorrencia(
                                descricao,
                                LocalDateTime.now(),
                                tipoOcorrencia,
                                Ocorrencia.EstadoOcorrencia.ABERTO,
                                morador.getId()
                        );
                        ocorrenciaController.adicionarOcorrencia(novaOcorrencia);
                        System.out.println("✅ Ocorrência cadastrada com sucesso!");
                        break;

                    case 2:
                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.MORADOR) {
                            List<Ocorrencia> minhasOcorrencias = ocorrenciaController.buscarOcorrenciasPorUsuario(usuarioLogado.getId());
                            if (minhasOcorrencias.isEmpty()) {
                                System.out.println("Você ainda não cadastrou nenhuma ocorrência.");
                            } else {
                                minhasOcorrencias.forEach(System.out::println);
                            }
                        } else {
                            ocorrenciaController.listarOcorrencias();
                        }
                        break;

                    case 3:
                        System.out.print("ID da ocorrência a atualizarOcorrenciaDao: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        Ocorrencia ocorrenciaExistente = ocorrenciaController.buscarOcorrenciaPorId(idAtualizar);
                        if (ocorrenciaExistente == null) {
                            System.out.println("❌ Ocorrência não encontrada.");
                            break;
                        }

                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.MORADOR &&
                                ocorrenciaController.pertenceAoUsuario(idAtualizar, usuarioLogado.getId())) {
                            System.out.println("❌ Você não tem permissão para atualizar esta ocorrência.");
                            break;
                        }

                        System.out.print("Nova descrição: ");
                        String novaDescricao = scanner.nextLine();
                        novaDescricao = novaDescricao.isEmpty() ? ocorrenciaExistente.getDescricao() : novaDescricao;

                        System.out.println("Tipo de ocorreência:");
                        System.out.println("Novo tipo de ocorrência:");
                        System.out.println("1 - Manutenção");
                        System.out.println("2 - Reclamação");
                        System.out.println("3 - Outro");
                        int tipoOpcaoModificar = scanner.nextInt();
                        scanner.nextLine();

                        Ocorrencia.TipoOcorrencia tipoOcorrenciaModificar = switch (tipoOpcaoModificar) {
                            case 1 -> Ocorrencia.TipoOcorrencia.MANUTENÇÃO;
                            case 2 -> Ocorrencia.TipoOcorrencia.RECLAMAÇÃO;
                            case 3 -> Ocorrencia.TipoOcorrencia.OUTRO;
                            default -> ocorrenciaExistente.getTipoOcorrencia();
                        };

                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.SINDICO ||
                        usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.ADMINISTRADOR){
                            System.out.println("Novo status de ocorrência:");
                            System.out.println("1 - ABERTO");
                            System.out.println("2 - EM ANDAMENTO");
                            System.out.println("3 - FECHADO");

                            int tipoStatusOpcaoModificar = scanner.nextInt();
                            scanner.nextLine();

                            Ocorrencia.EstadoOcorrencia tipoStatusModificar = switch (tipoStatusOpcaoModificar){
                                case 1 -> Ocorrencia.EstadoOcorrencia.ABERTO;
                                case 2 -> Ocorrencia.EstadoOcorrencia.EM_ANDAMENTO;
                                case 3 -> Ocorrencia.EstadoOcorrencia.RESOLVIDO;
                                default -> ocorrenciaExistente.getEstadoOcorrencia();
                            };

                            Ocorrencia ocorrenciaModificada = new Ocorrencia(
                                    ocorrenciaExistente.getId(),
                                    novaDescricao,
                                    ocorrenciaExistente.getDataCriacao(),
                                    tipoOcorrenciaModificar,
                                    tipoStatusModificar,
                                    ocorrenciaExistente.getMorador()
                            );

                            ocorrenciaController.atualizarOcorrencia(ocorrenciaModificada);

                        }else {

                            Ocorrencia ocorrenciaModificada = new Ocorrencia(
                                    ocorrenciaExistente.getId(),
                                    novaDescricao,
                                    ocorrenciaExistente.getDataCriacao(),
                                    tipoOcorrenciaModificar,
                                    ocorrenciaExistente.getEstadoOcorrencia(),
                                    ocorrenciaExistente.getMorador()
                            );
                            ocorrenciaController.atualizarOcorrencia(ocorrenciaModificada);

                        }

                        System.out.println("✅ Ocorrência atualizada.");
                        break;

                    case 4:
                        System.out.print("ID da ocorrência: ");
                        int idExcluir = scanner.nextInt();
                        scanner.nextLine();

                        Ocorrencia ocorrenciaParaExcluir = ocorrenciaController.buscarOcorrenciaPorId(idExcluir);
                        if (ocorrenciaParaExcluir == null) {
                            System.out.println("❌ Ocorrência não encontrada.");
                            break;
                        }

                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.MORADOR &&
                                ocorrenciaParaExcluir.getMorador().getId() != usuarioLogado.getId()) {
                            System.out.println("❌ Você não tem permissão para excluir esta ocorrência.");
                            break;
                        }

                        ocorrenciaController.deletarOcorrencia(idExcluir);
                        System.out.println("✅ Ocorrência excluída.");
                        break;

                    case 5:
                        System.out.print("ID da ocorrência: ");
                        int idBuscar = scanner.nextInt();
                        scanner.nextLine();
                        Ocorrencia encontrada = ocorrenciaController.buscarOcorrenciaPorId(idBuscar);
                        if (encontrada == null) {
                            System.out.println("❌ Ocorrência não encontrada.");
                        } else {
                            if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.MORADOR &&
                                    encontrada.getMorador().getId() != usuarioLogado.getId()) {
                                System.out.println("❌ Você não tem permissão para visualizar esta ocorrência.");
                            } else {
                                System.out.println(encontrada);
                            }
                        }
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
