Brewer.Venda = (function() {
	function Venda(tabelaItens) {
		this.tabelaItens = tabelaItens
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorFreteInput = $('#valorFrete');
		this.valorDescontoInput = $('#valorDesconto');
		this.valorTotalBoxContainer = $('.js-valor-total-box-container');
		this.horaEntregaInput = $('#horaEntrega');
		
		this.valorTotalItens = this.tabelaItens.valorTotal();
		this.valorFrete = this.valorFreteInput.data('valor');
		this.valorDesconto = this.valorDescontoInput.data('valor');
	}
	
	Venda.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		this.valorFreteInput.on('keyup', onValorFreteAlterado.bind(this));
		this.valorDescontoInput.on('keyup', onValorDescontoAlterado.bind(this));
		
		this.tabelaItens.on('tabela-itens-atualizada', onValoresAlterados.bind(this));
		this.valorFreteInput.on('keyup', onValoresAlterados.bind(this));
		this.valorDescontoInput.on('keyup', onValoresAlterados.bind(this));
		
		this.horaEntregaInput.on('keyup', onHoraEntregaAlterada.bind(this));
		this.horaEntregaInput.on('change', onCampoHoraIncompleto.bind(this));
		
		onValoresAlterados.call(this);
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
	}
	
	function onValorFreteAlterado(evento) {
		this.valorFrete = Brewer.recuperarValor($(evento.target).val());
	}
	
	function onValorDescontoAlterado(evento) {
		this.valorDesconto = Brewer.recuperarValor($(evento.target).val());
	}
	
	function onValoresAlterados() {
		var valorTotal = numeral(this.valorTotalItens) + numeral(this.valorFrete) - numeral(this.valorDesconto);
		this.valorTotalBox.html(Brewer.formatarMoeda(valorTotal));
		
		this.valorTotalBoxContainer.toggleClass('negativo', valorTotal < 0);
	}
	
	function onHoraEntregaAlterada(evento) {
		var input = $(evento.target);
		var horaEntrega = input.val();
		
		if (horaEntrega.length == 5) {
			var isValid = horaEntrega.match(/^([0-1]?[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])?$/);
			
			if (!isValid) {
				var horaArray = horaEntrega.split(':');
				var hora, minuto;
				
				hora = horaArray[0];
				minuto = horaArray[1];
				
				if (hora >= 24) {
					hora = '23';
				}
				
				if (minuto >= 60) {
					minuto = '59';
				}
				
				input.val(hora.concat(':').concat(minuto));
			}
		}
	}
	
	function onCampoHoraIncompleto(evento) {
		var input = $(evento.target);
		var horaEntrega = input.val();
		
		if (horaEntrega.length < 5) {
			input.val('');
		}
	}
	
	return Venda;
}());

$(function() {
	var autocomplete = new Brewer.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Brewer.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var venda = new Brewer.Venda(tabelaItens);
	venda.iniciar();
});