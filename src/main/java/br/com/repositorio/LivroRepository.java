package br.com.repositorio;

import br.com.entidade.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    long countByIdimaIgnoreCase(String idima);

    @Override
    List<LivroEntity> findAll();
}

