package org.example.projetoesgfiap.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ponto_coleta")
@Getter
@Setter
@NoArgsConstructor
public class PontoColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 500)
    private String endereco;

    @Column(length = 255)
    private String horarioFuncionamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ponto_coleta_tipos_residuos",
            joinColumns = @JoinColumn(name = "ponto_coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_residuo_id")
    )
    private Set<TipoResiduo> tiposResiduos = new HashSet<>();
}