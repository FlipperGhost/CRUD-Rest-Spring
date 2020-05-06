package br.com.ordemservice.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.ordemservice.domains.model.StatusOrdemServico;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter


public class OrdemServicoModel {

	
	private Long id;
	private ClienteMinimalModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServicoModel other = (OrdemServicoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
