package org.example.projetoesgfiap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "TIPO_RESIDUO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoResiduo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome do tipo de resíduo não pode estar em branco.")
    @Size(max = 100, message = "O nome do tipo de resíduo não pode exceder 100 caracteres.")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
    @Column(name = "descricao", length = 255)
    private String descricao;
}