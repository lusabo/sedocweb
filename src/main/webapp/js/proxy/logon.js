var LogonProxy = {

	url : "api/logon",

	login : function(data) {
		return $.ajax({
			type : "POST",
			url : this.url,
			data : JSON.stringify(data),
			contentType : "application/json",
			error : function() {}
		});
	}
};
