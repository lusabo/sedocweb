/* Login Controller */

$(function() {

	App.clearAuthentication();
	
	$("form").submit(function(event) {
		event.preventDefault();
		$("[id$='-message']").hide();

		var data = {
			'username' : $("#username").val().trim(),
			'password' : $("#password").val()
		};

		LogonProxy.login(data).done(loginOk).fail(loginFailed);
	});

});

/* Callbacks */

function loginOk(data, textStatus, jqXHR) {
	App.setToken(jqXHR.getResponseHeader('Set-Token'));
	App.setLoggedInUser(data);
	location.href = "home";
}

function loginFailed(request) {
	switch (request.status) {
		case 422:
			App.handle422(request);
			break;

		case 401:
			$("#global-message").html('Usuário ou senha inválidos.').show();
			break;
	}
}
