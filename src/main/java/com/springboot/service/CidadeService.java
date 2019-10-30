package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Cidade;
import com.springboot.repository.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository rep;
	

	public List<Cidade> findByEstado(Integer estadoId) {
		return rep.findCidades(estadoId);
	}
}
