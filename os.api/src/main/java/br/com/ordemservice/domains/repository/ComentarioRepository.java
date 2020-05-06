package br.com.ordemservice.domains.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordemservice.api.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
