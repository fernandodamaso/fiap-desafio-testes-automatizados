package org.example.projetoesgfiap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projetoesgfiap.entity.TipoResiduo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoResiduoDTO {
    @NotBlank(message = "Nome do tipo de resíduo não pode ser vazio")
    private String nome;
    @NotBlank(message = "Descrição do tipo de resíduo não pode ser vazia")
    private String descricao;

    public TipoResiduoDTO(TipoResiduo tipoResiduoSalvo) {
        this(
                tipoResiduoSalvo.getNome(),
                tipoResiduoSalvo.getDescricao()
        );
    }
}