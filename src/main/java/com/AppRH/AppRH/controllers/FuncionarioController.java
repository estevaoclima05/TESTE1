package com.AppRH.AppRH.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.repository.FuncionarioRepository;




@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository fr;



	// chamo o form de casdatrar funcionários
	@RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
	public String form() {
		return "funcionario/formFuncionario";
	}

	// cadastra funcionários
	@RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
	public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarFuncionario";
		}

		fr.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		return "redirect:/cadastrarFuncionario";
	}

	// listar funcionário
	@RequestMapping("/funcionarios")
	public ModelAndView listaFuncionarios() {
		ModelAndView mv = new ModelAndView("funcionario/listaFuncionario");
		Iterable<Funcionario> funcionarios = fr.findAll();
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}



	
	//deleta funcionário
	@RequestMapping("/deletarFuncionario")
	public String deletarFuncionario(long id) {
		Funcionario funcionario = fr.findById(id);
		fr.delete(funcionario);
		return "redirect:/funcionarios";
		
	}
	
	// Métodos que atualizam funcionário
	//form
	@RequestMapping(value="/editar-funcionario", method = RequestMethod.GET)
	public ModelAndView editarFuncionario(long id) {
		Funcionario funcionario = fr.findById(id);
		ModelAndView mv = new ModelAndView("funcionario/update-funcionario");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	
	// update funcionário
	@RequestMapping(value = "/editar-funcionario", method = RequestMethod.POST)
	public String updateFuncionario(@Valid Funcionario funcionario,  BindingResult result, RedirectAttributes attributes){
		
		fr.save(funcionario);
		attributes.addFlashAttribute("successs", "Funcionário alterado com sucesso!");
		
		long idLong = funcionario.getId();
		String id = "" + idLong;
		return "redirect:/dependentes/" + id;
		
	}
	


	
	
	
	
	
	
	

}
