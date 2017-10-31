var Product = {
	
	_initTabs: function() {
		$('#product-tab-container ul li a').click(function (e) {
			  e.preventDefault()
			  $(this).tab('show')
		});
		
		$('#rest-api-table').dataTable({
			"order": [[2,'asc']],
			"iDisplayLength": 50
		});
		$('#soap-api-table').dataTable({
			"iDisplayLength": 50
		});
		$('#clinical-event-table').dataTable({
			"iDisplayLength": 50
		});
		
//		$('#alpore-versioned-product-search-input').keyup(function(eventObject) {
//			eventObject.preventDefault();
//			var elem = $(this),
//			value = elem.val(),
//			id = elem.data('id'),
//			url = 'restws/search/'+ id + '/product';
//			$.ajax({
//				type: 'GET',
//				contentType: 'application/json',
//				data: {'name': value},
//				url: url,
//				success: function(data, textStatus, jqXHR){
//					var resultNode = $('#versioned-product-search-results');
//						resultNode.empty();
//						if(data && data.productId) {
//							resultNode.append('<div>');
//							resultNode.append('<h3>' + data.productDetails.productName  +'</h3>');
//							resultNode.append('</div');
//						}
//				},
//				dataType: 'json'
//			});
//		});
	},
		 
	init: function() {
		this._initTabs();
	}
};