package com.mycompany.gestaobiblioteca;

import java.util.Objects;

public class Membro {
    private int id;
    private String numeroSocio;
    private String primeiroNome;
    private String apelido;
    private String email;

    // Construtor
    public Membro(int id, String numeroSocio, String primeiroNome, String apelido, String email) {
        this.id = id;
        this.numeroSocio = numeroSocio;
        this.primeiroNome = primeiroNome;
        this.apelido = apelido;
        this.email = email;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return primeiroNome + " " + apelido;
    }

    @Override
    public String toString() {
        // Alterado para corresponder ao formato do enunciado para JComboBox
        return getNomeCompleto() + " (" + numeroSocio + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membro membro = (Membro) o;
        return id == membro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}