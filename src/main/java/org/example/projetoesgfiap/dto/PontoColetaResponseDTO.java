package org.example.projetoesgfiap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PontoColetaResponseDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String horarioFuncionamento;
    private Set<TipoResiduoDTO> tiposResiduos;
}