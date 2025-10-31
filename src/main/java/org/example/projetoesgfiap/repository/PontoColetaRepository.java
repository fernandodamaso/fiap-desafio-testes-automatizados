package org.example.projetoesgfiap.repository;

import org.example.projetoesgfiap.entity.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {
}