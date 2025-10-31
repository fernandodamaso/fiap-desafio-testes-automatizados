package org.example.projetoesgfiap.controller;

import org.example.projetoesgfiap.dto.TipoResiduoDTO;
import org.example.projetoesgfiap.entity.TipoResiduo;
import org.example.projetoesgfiap.service.TipoResiduoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-residuo")
public class TipoResiduoController {

    private TipoResiduoService tipoResiduoService;

    @Autowired
    public TipoResiduoController(TipoResiduoService tipoResiduoService) {
        this.tipoResiduoService = tipoResiduoService;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoResiduoDTO criarTipoResiduo(@Valid @RequestBody TipoResiduoDTO tipoResiduoDTO) {
        return tipoResiduoService.criarTipoResiduo(tipoResiduoDTO);
    }

    @GetMapping("/getAll")
    public List<TipoResiduo> listarTiposResiduo() {
        return tipoResiduoService.listarTodos();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TipoResiduo> buscarTipoResiduoPorId(@PathVariable Long id) {
        TipoResiduo tipoResiduo = tipoResiduoService.buscarPorId(id);
        if (tipoResiduo != null) {
            return ResponseEntity.ok(tipoResiduo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<TipoResiduo> atualizarTipoResiduo(@PathVariable Long id, @Valid @RequestBody TipoResiduo tipoResiduoDetalhes) {
        TipoResiduo tipoResiduoAtualizado = tipoResiduoService.atualizarTipoResiduo(id, tipoResiduoDetalhes);
        if (tipoResiduoAtualizado != null) {
            return ResponseEntity.ok(tipoResiduoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarTipoResiduo(@PathVariable Long id) {
        tipoResiduoService.deletarTipoResiduo(id);
        return ResponseEntity.noContent().build();
    }
}