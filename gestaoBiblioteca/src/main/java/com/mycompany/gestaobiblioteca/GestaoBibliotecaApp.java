package com.mycompany.gestaobiblioteca; 

import java.util.ArrayList;
import java.util.List;
import java.util.Date; // Para exemplo de empréstimo
import java.text.SimpleDateFormat; // Para formatar datas (exemplo)
import java.text.ParseException;   // Para tratar erros de parse de data

public class GestaoBibliotecaApp {

    // --- Declaração dos ArrayLists como variáveis de instância ---
    private List<Livro> catalogoLivros;
    private List<Membro> listaDeMembros;
    private List<Emprestimo> registoEmprestimos;

    // --- Contadores para IDs (simples) ---
    private int proximoIdLivro = 1;
    private int proximoIdMembro = 1;
    private int proximoIdEmprestimo = 1;

    // Construtor da classe principal
    public GestaoBibliotecaApp() {
        // Inicializa os ArrayLists
        this.catalogoLivros = new ArrayList<>();
        this.listaDeMembros = new ArrayList<>();
        this.registoEmprestimos = new ArrayList<>();
    }

    // --- Métodos para adicionar itens às listas ---
    public void adicionarNovoLivro(String isbn, String titulo, String autor) {
        // Validação simples para evitar ISBN duplicado (pode ser mais robusta)
        for (Livro l : catalogoLivros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                System.out.println("Erro: Livro com ISBN " + isbn + " já existe.");
                return;
            }
        }
        Livro novoLivro = new Livro(proximoIdLivro++, isbn, titulo, autor);
        this.catalogoLivros.add(novoLivro);
        System.out.println("Livro adicionado: " + novoLivro.getTitulo());
    }

    public void adicionarNovoMembro(String numeroSocio, String primeiroNome, String apelido, String email) {
        // Validação simples para evitar NumeroSocio duplicado
        for (Membro m : listaDeMembros) {
            if (m.getNumeroSocio().equalsIgnoreCase(numeroSocio)) {
                System.out.println("Erro: Membro com Número de Sócio " + numeroSocio + " já existe.");
                return;
            }
        }
        Membro novoMembro = new Membro(proximoIdMembro++, numeroSocio, primeiroNome, apelido, email);
        this.listaDeMembros.add(novoMembro);
        System.out.println("Membro adicionado: " + novoMembro.getNomeCompleto());
    }

    public void registarNovoEmprestimo(int idLivro, int idMembro, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        // Validações:
        Livro livroParaEmprestar = null;
        for (Livro l : catalogoLivros) {
            if (l.getId() == idLivro) {
                livroParaEmprestar = l;
                break;
            }
        }
        if (livroParaEmprestar == null) {
            System.out.println("Erro: Livro com ID " + idLivro + " não encontrado.");
            return;
        }
        if (!livroParaEmprestar.isDisponivel()) {
            System.out.println("Erro: Livro '" + livroParaEmprestar.getTitulo() + "' não está disponível.");
            return;
        }

        Membro membroRequisitante = null;
        for (Membro m : listaDeMembros) {
            if (m.getId() == idMembro) {
                membroRequisitante = m;
                break;
            }
        }
        if (membroRequisitante == null) {
            System.out.println("Erro: Membro com ID " + idMembro + " não encontrado.");
            return;
        }

        // Se tudo ok, cria o empréstimo e atualiza o estado do livro
        Emprestimo novoEmprestimo = new Emprestimo(proximoIdEmprestimo++, idLivro, idMembro, dataEmprestimo, dataDevolucaoPrevista);
        this.registoEmprestimos.add(novoEmprestimo);
        livroParaEmprestar.setDisponivel(false); // Marca o livro como emprestado
        System.out.println("Empréstimo registado: Livro '" + livroParaEmprestar.getTitulo() + "' para Membro '" + membroRequisitante.getNomeCompleto() + "'.");
    }

    // --- Métodos para mostrar os dados (apenas para exemplo no console) ---
    public void mostrarCatalogoLivros() {
        System.out.println("\n--- Catálogo de Livros ---");
        if (catalogoLivros.isEmpty()) {
            System.out.println("Nenhum livro no catálogo.");
            return;
        }
        for (Livro livro : catalogoLivros) {
            System.out.println(livro + " (Disponível: " + livro.isDisponivel() + ")");
        }
    }

    public void mostrarListaMembros() {
        System.out.println("\n--- Lista de Membros ---");
        if (listaDeMembros.isEmpty()) {
            System.out.println("Nenhum membro registado.");
            return;
        }
        for (Membro membro : listaDeMembros) {
            System.out.println(membro);
        }
    }

    public void mostrarRegistoEmprestimos() {
        System.out.println("\n--- Registo de Empréstimos ---");
        if (registoEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registado.");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Emprestimo emprestimo : registoEmprestimos) {
            Livro l = catalogoLivros.stream().filter(liv -> liv.getId() == emprestimo.getIdLivro()).findFirst().orElse(null);
            Membro m = listaDeMembros.stream().filter(mem -> mem.getId() == emprestimo.getIdMembro()).findFirst().orElse(null);

            String nomeLivro = (l != null) ? l.getTitulo() : "ID Livro: " + emprestimo.getIdLivro();
            String nomeMembro = (m != null) ? m.getNomeCompleto() : "ID Membro: " + emprestimo.getIdMembro();
            String dataEmp = (emprestimo.getDataEmprestimo() != null) ? sdf.format(emprestimo.getDataEmprestimo()) : "N/A";
            String dataPrev = (emprestimo.getDataDevolucaoPrevista() != null) ? sdf.format(emprestimo.getDataDevolucaoPrevista()) : "N/A";
            String dataEfet = (emprestimo.getDataDevolucaoEfetiva() != null) ? sdf.format(emprestimo.getDataDevolucaoEfetiva()) : "-";

            System.out.println("ID Emp: " + emprestimo.getId() +
                               " | Livro: " + nomeLivro +
                               " | Membro: " + nomeMembro +
                               " | Emprestado em: " + dataEmp +
                               " | Prev. Devol.: " + dataPrev +
                               " | Devolvido em: " + dataEfet +
                               " | Estado: " + emprestimo.getEstado());
        }
    }

    // Método main para testar a classe
    public static void main(String[] args) {
        GestaoBibliotecaApp biblioteca = new GestaoBibliotecaApp();

        // Adicionar Livros
        biblioteca.adicionarNovoLivro("978-0547928227", "O Hobbit", "J.R.R. Tolkien");
        biblioteca.adicionarNovoLivro("978-0321765723", "Java Efetivo", "Joshua Bloch");
        biblioteca.adicionarNovoLivro("978-0132350884", "Código Limpo", "Robert C. Martin");

        // Adicionar Membros
        biblioteca.adicionarNovoMembro("S001", "Maria", "Santos", "maria.s@email.com");
        biblioteca.adicionarNovoMembro("S002", "Pedro", "Almeida", "pedro.a@email.com");

        biblioteca.mostrarCatalogoLivros();
        biblioteca.mostrarListaMembros();

        // Exemplo de registo de empréstimo
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataEmprestimo = sdf.parse("2024-01-10");
            Date dataDevolucao = sdf.parse("2024-01-24");
            // Emprestar "O Hobbit" (ID 1) para "Maria Santos" (ID 1)
            biblioteca.registarNovoEmprestimo(1, 1, dataEmprestimo, dataDevolucao);

            Date dataEmp2 = sdf.parse("2023-12-01"); // Empréstimo mais antigo
            Date dataDev2 = sdf.parse("2023-12-15"); // Já deveria ter sido devolvido (para testar "Atrasado")
            biblioteca.registarNovoEmprestimo(2,2, dataEmp2, dataDev2); // Java Efetivo para Pedro
        } catch (ParseException e) {
            System.out.println("Erro ao converter data para o empréstimo de exemplo: " + e.getMessage());
        }


        biblioteca.mostrarCatalogoLivros(); // Para ver que "O Hobbit" está indisponível
        biblioteca.mostrarRegistoEmprestimos();
    }
}