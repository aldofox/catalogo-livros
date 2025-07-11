package br.com.repositorio;

import br.com.entidade.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<AutorEntity, Long> {
}
