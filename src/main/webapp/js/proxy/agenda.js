var AgendaProxy = {

	url : "api/agenda",

	load : function() {
		return $.ajax({
			type : "GET",
			url : this.url,
			contentType : "application/json",
			error : function() {},
			beforeSend : function(request) {
				App.setHeader(request)
			}
		});
	}
};
