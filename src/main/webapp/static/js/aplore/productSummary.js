var ProductSummary = {
	
	_populateProductsSummary: function() {
		$('#alpore-product-search-input').keyup(function(eventObject) {
			eventObject.preventDefault();
			var elem = $(this),
			value = elem.val(),
			url = 'restws/search/product';
			$.ajax({
				type: 'GET',
				contentType: 'application/json',
				data: {'name': value},
				url: url,
				success: function(data, textStatus, jqXHR){
					var resultNode = $('#product-search-results ul');
						resultNode.empty();
						$.each(data, function(index, value) {
							var href = 'ProductDetails.action?name=' + value.name;
							resultNode.append('<li><a href="'+ href +'">' + value.name  +'</a></li>');
						});
				},
				dataType: 'json'
			});
		});
		
	},
		 
	init: function() {
		this._populateProductsSummary();
	}
};