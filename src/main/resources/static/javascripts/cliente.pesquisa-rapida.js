Brewer = Brewer || {};

Brewer.PesquisaRapidaCliente = (function() {
	
	function PesquisaRapidaCliente() {
		this.pesquisaRapidaClientesModal = $('#pesquisaRapidaClientes');
		this.nomeInput = $('#nomeFiltro');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-clientes-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaClientes');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		
		this.pesquisaRapidaClientesModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaRapidaClientesModal.on('hide.bs.modal', onModalClose.bind(this));
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaClientesModal.find('form').attr('action'), 
			method: 'GET', 
			contentType: 'application/json', 
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this), 
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onModalClose() {
		this.nomeInput.val('');
		this.containerTabelaPesquisa.html('');
		this.mensagemErro.addClass('hidden');
	}
	
	function onPesquisaConcluida(resultado) {
		/*$('#pagination-container').pagination({
			dataSource: resultado, 
			callback: function(data, pagination) {
				var html = this.template(data);
				this.containerTabelaPesquisa.html(html);
			}
		});*/
		
		this.mensagemErro.addClass('hidden');

		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaCliente = new Brewer.TabelaCliente(this.pesquisaRapidaClientesModal);
		tabelaCliente.iniciar();
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaCliente;
	
}());

Brewer.TabelaCliente = (function() {
	function TabelaCliente(modal) {
		this.modalCliente = modal;
		this.cliente = $('.js-cliente-pesquisa-rapida');
	}
	
	TabelaCliente.prototype.iniciar = function() {
		this.cliente.on('click', onClienteSelecionado.bind(this));
	}
	
	function onClienteSelecionado(evento) {
		this.modalCliente.modal('hide');

		var clienteSelecionado = $(evento.currentTarget);
		
		$('#nomeCliente').val(clienteSelecionado.data('nome'));
		$('#codigoCliente').val(clienteSelecionado.data('codigo'));
	}
	
	return TabelaCliente;
}());

Brewer.Paginacao = (function() {
	function Paginacao(registros) {
		this.todos = registros;
		this.paginaAtual = 1;
		this.registrosPorPagina = 2;
		this.totalRegistros = registros.length;
	}
	
	Paginacao.prototype.criar = function(paginaAtual) {
		criarPaginacao.call(this, paginaAtual);
	}
	
	function criarPaginacao(paginaAtualAux) {
		this.paginaAtual = paginaAtualAux || 1;
		
		var refInicial = (this.paginaAtual - 1) * this.registrosPorPagina;
		
		this.pagina = this.todos.slice(refInicial).slice(0, this.registrosPorPagina);
		this.totalPaginas = Math.ceil(this.todos.length / this.registrosPorPagina);
		this.paginaAnterior = this.paginaAtual - 1 ? this.paginaAtual - 1 : null;
		this.proximaPagina = (this.totalPaginas > this.paginaAtual) ? this.paginaAtual + 1 : null;
	}
	
	return Paginacao;
}());

$(function() {
	var pesquisaRapidaCliente = new Brewer.PesquisaRapidaCliente();
	
	pesquisaRapidaCliente.iniciar();
});