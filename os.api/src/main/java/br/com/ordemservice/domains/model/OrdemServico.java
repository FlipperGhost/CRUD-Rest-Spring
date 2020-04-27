package br.com.ordemservice.domains.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import	br.com.ordemservice.domains.validations.ValidationGroups;
import java.time.OffsetDateTime;

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
	
	@Valid
	@ConvertGroup(from = Default.class ,to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@JsonProperty(access=Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;
	
	@JsonProperty(access=Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	@JsonProperty(access=Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	
}
