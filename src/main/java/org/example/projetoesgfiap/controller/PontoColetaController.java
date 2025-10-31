package org.example.projetoesgfiap.controller;

import org.example.projetoesgfiap.dto.PontoColetaRequestDTO;
import org.example.projetoesgfiap.dto.PontoColetaResponseDTO;
import org.example.projetoesgfiap.service.PontoColetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos-coleta")
@Tag(name = "Pontos de Coleta", description = "API para gerenciamento de Pontos de Coleta de Resíduos")
@SecurityRequirement(name = "basicAuth")
public class PontoColetaController {

    @Autowired
    private PontoColetaService pontoColetaService;

    @Operation(summary = "Cria um novo ponto de coleta")
    @ApiResponse(responseCode = "201", description = "Ponto de coleta criado com sucesso")
    @PostMapping("/post")
    public ResponseEntity<PontoColetaResponseDTO> criarPontoColeta(@Valid @RequestBody PontoColetaRequestDTO requestDTO) {
        PontoColetaResponseDTO responseDTO = pontoColetaService.criarPontoColeta(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pontos de coleta")
    @GetMapping("/getAll")
    public ResponseEntity<List<PontoColetaResponseDTO>> listarPontosColeta() {
        return ResponseEntity.ok(pontoColetaService.listarTodosPontosColeta());
    }

    @Operation(summary = "Busca um ponto de coleta pelo ID")
    @ApiResponse(responseCode = "200", description = "Ponto de coleta encontrado")
    @ApiResponse(responseCode = "404", description = "Ponto de coleta não encontrado")
    @GetMapping("/get/{id}")
    public ResponseEntity<PontoColetaResponseDTO> buscarPontoColetaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pontoColetaService.buscarPontoColetaPorId(id));
    }

    @Operation(summary = "Atualiza um ponto de coleta existente")
    @ApiResponse(responseCode = "200", description = "Ponto de coleta atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Ponto de coleta não encontrado")
    @PutMapping("/put/{id}")
    public ResponseEntity<PontoColetaResponseDTO> atualizarPontoColeta(@PathVariable Long id, @Valid @RequestBody PontoColetaRequestDTO requestDTO) {
        return ResponseEntity.ok(pontoColetaService.atualizarPontoColeta(id, requestDTO));
    }

    @Operation(summary = "Deleta um ponto de coleta pelo ID")
    @ApiResponse(responseCode = "204", description = "Ponto de coleta deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Ponto de coleta não encontrado")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarPontoColeta(@PathVariable Long id) {
        pontoColetaService.deletarPontoColeta(id);
        return ResponseEntity.noContent().build();
    }
}