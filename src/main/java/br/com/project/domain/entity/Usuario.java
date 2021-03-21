package br.com.project.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Username é obrigatório")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Password é obrigatório")
    @Column(name = "password")
    private String password;

    @Column(name = "admin")
    private boolean admin;
}
