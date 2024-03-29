package com.springboot.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.dto.CidadeDTO;
import com.springboot.dto.EstadoDTO;
import com.springboot.model.Cidade;
import com.springboot.model.Estado;
import com.springboot.service.CidadeService;
import com.springboot.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;

		//BUSCAR POR ID
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<Estado> find(@PathVariable Integer id){
			Estado obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}

		//INSERIR
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void>insert(@RequestBody Estado obj){
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}

		//ATUALIZAR
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@RequestBody Estado obj, @PathVariable Integer id){
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}


		//EXCLUIR
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}

		//LISTAR TODAS
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<EstadoDTO>> findAll() {
			List<Estado> lista = service.findAll();
			List<EstadoDTO> listaDTO = lista.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList()); 
			return ResponseEntity.ok().body(listaDTO);
		}
		
		//LISTAR CIDADES DE UM ESTADO
		@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
		public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
			List<Cidade> list = cidadeService.findByEstado(estadoId);
			List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
			return ResponseEntity.ok().body(listDto);
		}

}
