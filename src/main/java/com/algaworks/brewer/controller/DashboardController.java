package com.algaworks.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.brewer.repository.CervejaRepository;
import com.algaworks.brewer.repository.ClienteRepository;
import com.algaworks.brewer.repository.VendaRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private CervejaRepository cervejaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("vendasNoAno", vendaRepository.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendaRepository.valorTotalNoMes());
		mv.addObject("ticketMedio", vendaRepository.valorTicketMedioNoAno());
		mv.addObject("estoque", cervejaRepository.valorQuantidadeEstoque());
		mv.addObject("totalClientes", clienteRepository.count());
		
		return mv;
	}
	
}
