import java.io.IOException;
import model.*;

public class CadastroPOO {

    public static void main(String[] args) {
        try {
            // --- Pessoa Fisica ---
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "Ivan Silva", "123.456.789-00", 30));
            repo1.inserir(new PessoaFisica(2, "Maria Souza", "987.654.321-00", 25));
            repo1.persistir("pessoas_fisicas.dat");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar("pessoas_fisicas.dat");

            System.out.println("PESSOAS FISICAS:");
            for (PessoaFisica pf : repo2.obterTodos()) {
                pf.exibir();
                System.out.println();
            }

            // --- Pessoa Jur?dica ---
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(1, "Empresa Alpha", "11.222.333/0001-44"));
            repo3.inserir(new PessoaJuridica(2, "Tech Solutions", "55.666.777/0001-99"));
            repo3.persistir("pessoas_juridicas.dat");

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar("pessoas_juridicas.dat");

            System.out.println("PESSOAS JURIDICAS:");
            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
