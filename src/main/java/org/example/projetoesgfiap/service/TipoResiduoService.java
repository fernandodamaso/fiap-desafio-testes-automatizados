package org.example.projetoesgfiap.service;

import org.example.projetoesgfiap.dto.TipoResiduoDTO;
import org.example.projetoesgfiap.entity.TipoResiduo;
import org.example.projetoesgfiap.repository.TipoResiduoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoResiduoService {

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    @Transactional
    public TipoResiduoDTO criarTipoResiduo(TipoResiduoDTO tipoResiduoDTO) {
        TipoResiduo tipoResiduo = new TipoResiduo();

        BeanUtils.copyProperties(tipoResiduoDTO, tipoResiduo);

        TipoResiduo tipoResiduoSalvo = tipoResiduoRepository.save(tipoResiduo);
        return new TipoResiduoDTO(tipoResiduoSalvo);
    }

    @Transactional(readOnly = true)
    public List<TipoResiduo> listarTodos() {
        return tipoResiduoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TipoResiduo buscarPorId(Long id) {
        Optional<TipoResiduo> tipoResiduoOptional = tipoResiduoRepository.findById(id);
        return tipoResiduoOptional.orElse(null);
    }

    @Transactional
    public TipoResiduo atualizarTipoResiduo(Long id, TipoResiduo tipoResiduoDetalhes) {
        TipoResiduo tipoResiduoExistente = buscarPorId(id);
        if (tipoResiduoExistente != null) {
            tipoResiduoExistente.setNome(tipoResiduoDetalhes.getNome());
            tipoResiduoExistente.setDescricao(tipoResiduoDetalhes.getDescricao());
            return tipoResiduoRepository.save(tipoResiduoExistente);
        }
        return null;
    }

    @Transactional
    public void deletarTipoResiduo(Long id) {
        tipoResiduoRepository.deleteById(id);
    }
}