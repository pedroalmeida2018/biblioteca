
package com.mycompany.gestaobiblioteca;
import java.util.Objects;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Emprestimo {
        public int id;
        public int idLivro;
        public int idMembro;
        public Date dataEmprestimo;
        public Date dataDevolucaoPrevista;
        public Date dataDevolucaoEfetiva;
        
        //Construtor
    
        public Emprestimo ( int id, int idLivro, int idMembro, Date dataEmprestimo, Date dataDevolucaoPrevista, Date dataDevolucaoEfetiva){
            this.id = id;
            this.idLivro = idLivro;
            this.idMembro = idMembro;
            this.dataEmprestimo = dataEmprestimo;
            this.dataDevolucaoPrevista = dataDevolucaoPrevista;
            this.dataDevolucaoEfetiva = null;
        }
        
    // Getters e Setters
      public int getId () {
          return id;
      }
      public int getIdLivro () {
          return idLivro;
      }
      public int getIdMembro () {
          return idMembro;
      }
      public Date getDataEmprestimo () {
          return dataEmprestimo;
      }
      public void setDataEmprestimo (Date dataEmprestimo) {
          this.dataEmprestimo = dataEmprestimo;
      }
      public Date getDataDevolucaoPrevista () {
          return dataDevolucaoPrevista;
      }
      public void setDataDevolucaoPrevista (Date dataDevolucaoPrevista) {
          this.dataDevolucaoPrevista = dataDevolucaoPrevista;
      }
      public Date getDataDevolucaoEfetiva () {
          return dataDevolucaoEfetiva;
      }
      public void setDataDevolucaoEfetiva (Date dataDevolucaoEfetiva) {
          this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
      }
      
      public String getEstado () {
          if (dataDevolucaoEfetiva != null){
          return "Devolvido";
      } else {
          LocalDate hoje = LocalDate.now ();
          LocalDate prevista = dataDevolucaoPrevista.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          
          if (hoje.isAfter(prevista)) {
              return "Atrasado";
          }
          else {
              return "Ativo";
          }
          }  
      }
     @Override
      public boolean equals (Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false; // o getClass tem de existir para garantir que se está a usar a classe Livro
          Emprestimo emprestimo = (Emprestimo)o;
          return id == emprestimo.id;
      }
     @Override
      public int hashCode () {
        return Objects.hash(id);
    }
    }

   
