package br.com.spring_example.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;

    private String nome;

    private String email;

    public Usuario() {
        super();
    }

    public Usuario(String nome, String email) {
        super();
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
