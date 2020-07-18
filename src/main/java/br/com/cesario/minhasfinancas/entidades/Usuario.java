package br.com.cesario.minhasfinancas.entidades;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "usuario", schema = "financas")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;


    @Column(name = "senha")
    private String senha;

}
