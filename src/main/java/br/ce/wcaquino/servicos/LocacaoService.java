package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
	
	
	@Test
	public void TesteAlugarFilme() {
		
		//Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário1");
		Filme filme = new Filme("Filme1", 2, 5.0);
		
		//Ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		//Verificação
		Assert.assertTrue(locacao.getValor() == 5.0);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		
	}

	
//	Teste sem utilizar o JUnit
//  --------------------------	
//	public static void main(String[] args) {
//		
//		//Cenário
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Usuário1");
//		Filme filme = new Filme("Filme1", 2, 5.0);
//		
//		//Ação
//		Locacao locacao = service.alugarFilme(usuario, filme);
//		
//		//Verificação
//		System.out.println(locacao.getValor() == 5.0);
//		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
//		
//	}
}