package model.entities;

import java.io.Serializable;

public class SrqNfce implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numero;
	private Integer serie;
	private String chave;
	private Double valor;
	
	public SrqNfce() {
		
	}

	public SrqNfce(Integer numero, Integer serie, String chave, Double valor) {
		this.numero = numero;
		this.serie = serie;
		this.chave = chave;
		this.valor = valor;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
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
		SrqNfce other = (SrqNfce) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SrqNfce [numero=" + numero + ", serie=" + serie + ", chave=" + chave + ", valor=" + valor + "]";
	}
}