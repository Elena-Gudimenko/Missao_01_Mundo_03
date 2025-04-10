import java.io.IOException;
import java.util.Scanner;
import model.*;

public class CadastroPOO2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo pfRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pjRepo = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Tipo (F para Fi­sica / J para Juri­dica): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    if (tipo.equals("F")) {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();
                        pfRepo.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo.equals("J")) {
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        pjRepo.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                }
                case 2 -> {
                    System.out.print("Tipo (F para Fi­sica / J para Juri­dica): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo.equals("F")) {
                        PessoaFisica pf = pfRepo.obter(id);
                        if (pf != null) {
                            System.out.println("Dados atuais:");
                            pf.exibir();

                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Nova idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine();

                            pfRepo.alterar(new PessoaFisica(id, nome, cpf, idade));
                        }
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = pjRepo.obter(id);
                        if (pj != null) {
                            System.out.println("Dados atuais:");
                            pj.exibir();

                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CNPJ: ");
                            String cnpj = scanner.nextLine();

                            pjRepo.alterar(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Tipo (F para Fi­sica / J para Juri­dica): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo.equals("F")) {
                        pfRepo.excluir(id);
                    } else if (tipo.equals("J")) {
                        pjRepo.excluir(id);
                    }
                }
                case 4 -> {
                    System.out.print("Tipo (F para Fi­sica / J para Juri­dica): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo.equals("F")) {
                        PessoaFisica pf = pfRepo.obter(id);
                        if (pf != null) pf.exibir();
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = pjRepo.obter(id);
                        if (pj != null) pj.exibir();
                    }
                }
                case 5 -> {
                    System.out.print("Tipo (F para Fi­sica / J para Juri­dica): ");
                    String tipo = scanner.nextLine().toUpperCase();

                    if (tipo.equals("F")) {
                        for (PessoaFisica pf : pfRepo.obterTodos()) {
                            pf.exibir();
                            System.out.println();
                        }
                    } else if (tipo.equals("J")) {
                        for (PessoaJuridica pj : pjRepo.obterTodos()) {
                            pj.exibir();
                            System.out.println();
                        }
                    }
                }
                case 6 -> {
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    try {
                        pfRepo.persistir(prefixo + ".fisica.bin");
                        pjRepo.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                }
                case 7 -> {
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    try {
                        pfRepo.recuperar(prefixo + ".fisica.bin");
                        pjRepo.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso!");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar: " + e.getMessage());
                    }
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}