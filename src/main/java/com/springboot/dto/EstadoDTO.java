package com.springboot.dto;

import java.io.Serializable;

import com.springboot.model.Estado;

public class EstadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public EstadoDTO() {

	}

	public EstadoDTO(Estado e) {
		this.id = e.getId();
		this.nome = e.getNome();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
