
package com.mycompany.gestaobiblioteca;
import java.util.Objects;


public class Livro {
     int id;
        private String isbn;
        private String titulo;
        private String autor; 
        private boolean disponivel;
        
    //Construtor
    
        public Livro ( int id, String isbn, String titulo, String autor, boolean disponivel){
            this.id = id;
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
            this.disponivel = true;       
        }
        
    // Getters e Setters
      public int getId () {
          return id;
      }
      public String getIsbn () {
          return isbn;
      }
      public void setIsbn (String isbn) {
          this.isbn = isbn;
      }
      public String getTitulo () {
          return titulo;
      }
      public void setTitulo (String titulo) {
          this.titulo = titulo;
      }
      public String getAutor () {
          return autor;
      }
      public void setAutor (String autor) {
          this.autor = autor;
      }
      public boolean isDisponivel () {
          return disponivel;
      }
      public void setDisponivel (boolean disponivel) {
          this.disponivel = disponivel;
      }
      
     @Override
      public String toString () {
          return titulo + " - " + autor;
      }
     @Override
      public boolean equals (Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false; // o getClass tem de existir para garantir que se está a usar a classe Livro
          Livro livro = (Livro)o;
          return id == livro.id;
      }
     @Override
      public int hashCode () {
        return Objects.hash(id);
    }
      }
   
    