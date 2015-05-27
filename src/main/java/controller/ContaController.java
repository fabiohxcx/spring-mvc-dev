package controller;

import modelo.Conta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ContaDAO;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adiciona(Conta conta) {

		System.out.println("Conta adicionada é:" + conta.getDescricao());
		ContaDAO dao = new ContaDAO();
		dao.adiciona(conta);

		return "conta-adicionada";
	}
}
