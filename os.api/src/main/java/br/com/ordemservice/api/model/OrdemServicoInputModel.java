package br.com.ordemservice.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class OrdemServicoInputModel {

	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	@Getter
	@Setter
	public class ClienteIdInput {
		@NotNull
		private Long id;
		
	}
}
