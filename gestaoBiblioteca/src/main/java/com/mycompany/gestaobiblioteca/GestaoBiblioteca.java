

package com.mycompany.gestaobiblioteca;
import java.util.Date;

public class GestaoBiblioteca {

    class Livro {
        int id;
        int isbn;
        public String titulo;
        public String autor; 
    }
    class Membro {
        public int id;
        public String numeroSocio;
        public String primeiroNome;
        public String apelido;
        public String email;
    }
    class Emprestimo{
        public int id;
        public int idLivro;
        public int idMembro;
        public Date dataEmprestimo;
        public Date dataDevolucaoPrevista;
        public Date dataDevolucaoEfetiva;
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
