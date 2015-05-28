package controller;

import java.util.List;

import modelo.Conta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping("/removeConta")
	public String remove(Conta conta) {

		ContaDAO dao = new ContaDAO();
		dao.remove(conta);

		return "redirect:listaContas"; // fica com a url listaContas
		// return "forward:listaContas"; mantém a url do remove
	}

	@RequestMapping("/listaContas")
	public ModelAndView lista() {

		ContaDAO dao = new ContaDAO();
		List<Conta> contas = dao.lista();

		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("todasAsContas", contas);

		return mv;
	}
}
