package com.algaworks.brewer.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.EstiloRepository;
import com.algaworks.brewer.repository.filter.EstiloFilter;
import com.algaworks.brewer.service.CadastroEstiloService;
import com.algaworks.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping("/estilos")
public class EstiloController {

	@Autowired
	private CadastroEstiloService cadastroEstiloService;

	@Autowired
	private EstiloRepository estiloRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		return new ModelAndView("estilo/CadastroEstilo");
	}

	@PostMapping({"/novo", "{\\+d}"})
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estilo);
		}

		try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());

			return novo(estilo);
		}

		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso.");

		return new ModelAndView("redirect:/estilos/novo");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}

		cadastroEstiloService.salvar(estilo);

		return ResponseEntity.ok(estilo);
	}

	@GetMapping
	public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estilo/PesquisaEstilos");

		PageWrapper<Estilo> paginaWrapper;
		
		Page<Estilo> page;
		
		if (estiloFilter.isSearch()) {
			page = estiloRepository.filtrar(estiloFilter, pageable);
		} else {
			page = new PageImpl<>(new ArrayList<Estilo>());
		}

		paginaWrapper = new PageWrapper<>(page, httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);

		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Estilo estilo = estiloRepository.findOne(codigo);
		
		ModelAndView mv = novo(estilo);
		mv.addObject(estilo);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Estilo estilo) {
		try {
			cadastroEstiloService.excluir(estilo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok().build();
	}
}
