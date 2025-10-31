package org.example.projetoesgfiap.repository;

import org.example.projetoesgfiap.entity.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoResiduoRepository extends JpaRepository<TipoResiduo, Long> {
}