// Login Controller
$(function() {
	
	$("#username").focus();

	$("form").submit(function(event) {
		event.preventDefault();
		$("[id$='-message']").hide();

		var data = {
			'username' : $("#username").val().trim(),
			'password' : $("#password").val()
		};

		LogonProxy.login(data).done(loginOk).fail(loginFailed);
	});

	LogonProxy.getOAuthAppIds().done(getOAuthAppIdsOk);
});

/* Callbacks */

function loginOk($data, $status, $request) {
	App.setToken($request.getResponseHeader('Set-Token'));
	App.setLoggedInUser($data);

	var url;
	var pendencies = false;

	if ($data.profile.pendencies > 0) {
		url = App.getContextPath() + "/user/profile";
		pendencies = true;

	} else if ($data.health.pendencies > 0) {
		url = App.getContextPath() + "/user/health";
		pendencies = true;
	}

	if (pendencies) {
		bootbox.dialog({
			title : "Atenção!",
			message : "Seus dados cadastrais estão incompletos. Para se inscrever nas provas você precisa resolver isso. É fácil e rápido!",
			buttons : {
				main : {
					label : "<span class='glyphicon glyphicon-edit' aria-hidden='true' style='font-size: 0.8em;'></span> Resolver agora",
					className : "btn-success",
					callback : function() {
						location.href = url;
					}
				}
			},
			onEscape : function() {
				App.restoreSavedLocation();
			}
		});

	} else {
		App.restoreSavedLocation();
	}
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
