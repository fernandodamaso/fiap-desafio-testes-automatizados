package org.example.projetoesgfiap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PontoColetaRequestDTO {
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "Endereço não pode ser vazio")
    @Size(max = 500, message = "Endereço deve ter no máximo 500 caracteres")
    private String endereco;

    @Size(max = 255, message = "Horário de funcionamento deve ter no máximo 255 caracteres")
    private String horarioFuncionamento;

    @NotEmpty(message = "Deve haver pelo menos um ID de tipo de resíduo")
    private Set<Long> tiposResiduosIds;
}