package org.example.projetoesgfiap.service;

import org.example.projetoesgfiap.dto.PontoColetaRequestDTO;
import org.example.projetoesgfiap.dto.PontoColetaResponseDTO;
import org.example.projetoesgfiap.dto.TipoResiduoDTO;
import org.example.projetoesgfiap.entity.PontoColeta;
import org.example.projetoesgfiap.entity.TipoResiduo;
import org.example.projetoesgfiap.exception.ResourceNotFoundException;
import org.example.projetoesgfiap.repository.PontoColetaRepository;
import org.example.projetoesgfiap.repository.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    @Transactional
    public PontoColetaResponseDTO criarPontoColeta(PontoColetaRequestDTO requestDTO) {
        PontoColeta pontoColeta = new PontoColeta();
        pontoColeta.setNome(requestDTO.getNome());
        pontoColeta.setEndereco(requestDTO.getEndereco());
        pontoColeta.setHorarioFuncionamento(requestDTO.getHorarioFuncionamento());

        Set<TipoResiduo> tipos = buscarTiposResiduoPorIds(requestDTO.getTiposResiduosIds());
        pontoColeta.setTiposResiduos(tipos);

        PontoColeta salvo = pontoColetaRepository.save(pontoColeta);
        return mapToResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<PontoColetaResponseDTO> listarTodosPontosColeta() {
        return pontoColetaRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PontoColetaResponseDTO buscarPontoColetaPorId(Long id) {
        PontoColeta pontoColeta = pontoColetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PontoColeta não encontrado com id: " + id));
        return mapToResponseDTO(pontoColeta);
    }

    @Transactional
    public PontoColetaResponseDTO atualizarPontoColeta(Long id, PontoColetaRequestDTO requestDTO) {
        PontoColeta pontoColeta = pontoColetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PontoColeta não encontrado com id: " + id));

        pontoColeta.setNome(requestDTO.getNome());
        pontoColeta.setEndereco(requestDTO.getEndereco());
        pontoColeta.setHorarioFuncionamento(requestDTO.getHorarioFuncionamento());

        Set<TipoResiduo> tipos = buscarTiposResiduoPorIds(requestDTO.getTiposResiduosIds());
        pontoColeta.setTiposResiduos(tipos);

        PontoColeta atualizado = pontoColetaRepository.save(pontoColeta);
        return mapToResponseDTO(atualizado);
    }

    @Transactional
    public void deletarPontoColeta(Long id) {
        if (!pontoColetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("PontoColeta não encontrado com id: " + id);
        }
        pontoColetaRepository.deleteById(id);
    }

    private Set<TipoResiduo> buscarTiposResiduoPorIds(Set<Long> ids) {
        Set<TipoResiduo> tipos = new HashSet<>();
        if (ids != null && !ids.isEmpty()) {
            for (Long tipoId : ids) {
                TipoResiduo tipo = tipoResiduoRepository.findById(tipoId)
                        .orElseThrow(() -> new ResourceNotFoundException("TipoResiduo não encontrado com id: " + tipoId));
                tipos.add(tipo);
            }
        }
        return tipos;
    }

    private PontoColetaResponseDTO mapToResponseDTO(PontoColeta pontoColeta) {
        PontoColetaResponseDTO dto = new PontoColetaResponseDTO();
        dto.setId(pontoColeta.getId());
        dto.setNome(pontoColeta.getNome());
        dto.setEndereco(pontoColeta.getEndereco());
        dto.setHorarioFuncionamento(pontoColeta.getHorarioFuncionamento());
        if (pontoColeta.getTiposResiduos() != null) {
            dto.setTiposResiduos(pontoColeta.getTiposResiduos().stream()
                    .map(tr -> new TipoResiduoDTO())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }
}