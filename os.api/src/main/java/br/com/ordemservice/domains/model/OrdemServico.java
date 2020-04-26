package br.com.ordemservice.domains.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Getter;
import lombok.Setter;
@EntityScan
@Entity
@Getter
@Setter
public class OrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	private String descricao;
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;
	
	private LocalDateTime dataAbertura;
	
	private LocalDateTime dataFinalizacao;
	
	
}
