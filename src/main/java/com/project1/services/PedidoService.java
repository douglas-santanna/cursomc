package com.project1.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project1.domain.ItemPedido;
import com.project1.domain.PagamentoComBoleto;
import com.project1.domain.Pedido;
import com.project1.domain.enums.StatusPagamento;
import com.project1.repositories.ItemPedidoRepository;
import com.project1.repositories.PagamentoRepository;
import com.project1.repositories.PedidoRepository;
import com.project1.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repositorioPedido;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository repositorioPagamento;
	
	@Autowired
	private ProdutoService servicoProduto;
	
	@Autowired
	private ItemPedidoRepository repositorioItemPedido;
	
	@Autowired
	private ClienteService servicoCliente;
	
	@Autowired
	private EmailService servicoEmail;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> objetoPedido = repositorioPedido.findById(id);
		return objetoPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhum objeto encontrado! id: "+ id+", Tipo do objeto "+ Pedido.class.getName()));
				
	}
	
	@Transactional
	public Pedido insert(Pedido objetoPedido) {
		objetoPedido.setId(null);
		objetoPedido.setInstante(new Date());
		objetoPedido.setCliente(servicoCliente.buscar(objetoPedido.getCliente().getId()));
		objetoPedido.getPagamento().setStatusPagamento(StatusPagamento.PENDENTE);
		objetoPedido.getPagamento().setPedido(objetoPedido);
		if(objetoPedido.getPagamento() instanceof PagamentoComBoleto){
			PagamentoComBoleto pagto = (PagamentoComBoleto) objetoPedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, objetoPedido.getInstante()); 
		}
		objetoPedido = repositorioPedido.save(objetoPedido);
		repositorioPagamento.save(objetoPedido.getPagamento());
	
		for(ItemPedido itemEncontrato : objetoPedido.getItens() ){
			itemEncontrato.setDesconto(0.0);
			itemEncontrato.setProduto(servicoProduto.buscar(itemEncontrato.getProduto().getId()));
			itemEncontrato.setPreco(itemEncontrato.getProduto().getPreco());
			itemEncontrato.setPedido(objetoPedido);
		}
		repositorioItemPedido.saveAll(objetoPedido.getItens());
		servicoEmail.envioDeConfirmacaoDePedidoHTML(objetoPedido);
		return objetoPedido;

	}
	
}
