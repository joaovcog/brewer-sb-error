Brewer = Brewer || {};

Brewer.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuNomeInput = $('.js-sku-nome-cerveja-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(skuNome) {
				return this.skuNomeInput.data('url') + '?skuNome=' + skuNome;
			}.bind(this), 
			getValue: 'nome', 
			minCharNumber: 3, 
			requestDelay: 300, 
			adjustWidth: false, 
			ajaxSettings: {
				contentType: 'application/json'
			}, 
			template: {
				type: 'custom', 
				method: template.bind(this)
			}, 
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		
		this.skuNomeInput.easyAutocomplete(options);
		//$('div.easy-autocomplete').removeAttr('style');
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.skuNomeInput.getSelectedItemData());
		this.skuNomeInput.val('');
		this.skuNomeInput.focus();
	}
	
	function template(nome, cerveja) {
		cerveja.valorFormatado = Brewer.formatarMoeda(cerveja.valor);
		return this.template(cerveja);
	}
	
	return Autocomplete;
	
}());