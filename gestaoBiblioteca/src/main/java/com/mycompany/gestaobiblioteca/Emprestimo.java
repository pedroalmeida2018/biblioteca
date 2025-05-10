package com.mycompany.gestaobiblioteca;

import java.util.Date;
import java.util.Objects;
import java.time.LocalDate;
import java.time.ZoneId;

public class Emprestimo {
    private int id;
    private int idLivro;
    private int idMembro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoEfetiva; // null se ainda não foi devolvido

    // Construtor
    public Emprestimo(int id, int idLivro, int idMembro, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        this.id = id;
        this.idLivro = idLivro;
        this.idMembro = idMembro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoEfetiva = null; // Começa como não devolvido
    }

    // Getters
    public int getId() { return id; }
    public int getIdLivro() { return idLivro; }
    public int getIdMembro() { return idMembro; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public Date getDataDevolucaoEfetiva() { return dataDevolucaoEfetiva; }

    // Setters
    public void setDataEmprestimo(Date dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) { this.dataDevolucaoPrevista = dataDevolucaoPrevista; }
    public void setDataDevolucaoEfetiva(Date dataDevolucaoEfetiva) { this.dataDevolucaoEfetiva = dataDevolucaoEfetiva; }

    // Método para obter o estado do empréstimo (Ativo, Devolvido, Atrasado)
    public String getEstado() {
        if (dataDevolucaoEfetiva != null) {
            return "Devolvido";
        } else {
            // Comparar com a data atual
            if (dataDevolucaoPrevista != null && new Date().after(dataDevolucaoPrevista)) {
                 // Convert java.util.Date to java.time.LocalDate for easier comparison
                LocalDate hoje = LocalDate.now();
                LocalDate prevista = dataDevolucaoPrevista.toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();
                if (hoje.isAfter(prevista)) {
                    return "Atrasado";
                } else {
                    return "Ativo";
                }
            }
            return "Ativo"; // Se dataDevolucaoPrevista for null ou não estiver atrasado
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Empréstimo ID: " + id + ", Livro ID: " + idLivro + ", Membro ID: " + idMembro;
    }
}