package br.com.fiap.challenge4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContaRequestDto {

    @NotBlank
    @Size(max = 100, min= 3)
    private String nome;

    @NotBlank
    @Size(max = 20, min = 2)
    private String apelido;

    @NotBlank
    private String email;

    @NotBlank
    @Size (max = 30, min = 12)
    private String senha;

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
}
