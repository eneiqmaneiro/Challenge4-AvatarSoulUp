package br.com.fiap.challenge4.model;

public class Conta {

    //Atributos
    private int codigo;
    private String nome;
    private String apelido;
    private String email;
    private String senha;

    //Construtor vazio


    public Conta() {}

    //Construtor principal
    public Conta(int codigo, String nome, String apelido, String email, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.apelido = apelido;
        this.senha = senha;
    }

    //Construtor sem código
    public Conta(String nome, String apelido, String email, String senha) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
    }

    //Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //toString
    @Override
    public String toString() {
        return codigo + ", Nome: " + nome + ", Apelido: " + apelido + ", Email: " + email+ ", Senha: " + senha;
    }

}
