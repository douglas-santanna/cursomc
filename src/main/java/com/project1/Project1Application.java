package com.project1;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project1.domain.Categoria;
import com.project1.domain.Cidade;
import com.project1.domain.Cliente;
import com.project1.domain.Endereco;
import com.project1.domain.Estado;
import com.project1.domain.ItemPedido;
import com.project1.domain.Pagamento;
import com.project1.domain.PagamentoComBoleto;
import com.project1.domain.PagamentoComCartao;
import com.project1.domain.Pedido;
import com.project1.domain.Produto;
import com.project1.domain.enums.StatusPagamento;
import com.project1.domain.enums.TipoCliente;
import com.project1.repositories.CategoriaRepository;
import com.project1.repositories.CidadeRepository;
import com.project1.repositories.ClienteRepository;
import com.project1.repositories.EnderecoRepository;
import com.project1.repositories.EstadoRepository;
import com.project1.repositories.ItemPedidoRepository;
import com.project1.repositories.PagamentoRepository;
import com.project1.repositories.PedidoRepository;
import com.project1.repositories.ProdutoRepository;

@SpringBootApplication
public class Project1Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	@Autowired
	private ProdutoRepository repositorioProduto;
	
	@Autowired
	private EstadoRepository repositorioEstado;
	
	@Autowired
	private CidadeRepository repositorioCidade;
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	@Autowired
	private EnderecoRepository repositorioEndereco;
	
	@Autowired
	private PedidoRepository repositorioPedido;
	
	@Autowired
	private PagamentoRepository repositorioPagamento;
	
	@Autowired
	private ItemPedidoRepository repositorioItemPedido;
	
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Papelaria");
		Categoria cat4 = new Categoria(null, "Roupa");
		Categoria cat5 = new Categoria(null, "Eletronico");
		Categoria cat6 = new Categoria(null, "Lazer");
		Categoria cat7 = new Categoria(null, "Diversão");
		Categoria cat8 = new Categoria(null, "Gratuito");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		repositorioCategoria.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		repositorioProduto.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		repositorioEstado.saveAll(Arrays.asList(estado1,estado2));
		repositorioCidade.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		
		Cliente cliente1 = new Cliente(null, "Carlos Pereira", "carlos@email.com", "410.035.338.33", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefone().addAll(Arrays.asList("39393939","941665012"));
		
		Endereco endereco1 = new Endereco(null, "Rua Francisco", "260", "Apto 1", "Dom Pedro", "12123-700", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Av Amaral Gama", "105", "Sala 3", "Santana", "13456-789", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
		
		repositorioCliente.saveAll(Arrays.asList(cliente1));
		repositorioEndereco.saveAll(Arrays.asList(endereco1,endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("02/04/2020 21:50"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("02/04/2020 22:00"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, StatusPagamento.PAGO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, StatusPagamento.PENDENTE, pedido2, sdf.parse("10/04/2020 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		
		repositorioPedido.saveAll(Arrays.asList(pedido1,pedido2));
		repositorioPagamento.saveAll(Arrays.asList(pagamento1,pagamento2));
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, p2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido2));
		p3.getItens().addAll(Arrays.asList(itemPedido3));
		
		repositorioItemPedido.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
		
		
	}

}
