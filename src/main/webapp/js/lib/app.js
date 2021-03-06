var App = {

	tokenKey : "Token",

	userKey : "User",

//	savedLocationKey : "Saved Location",
//
//	restoreSavedLocation : function() {
//		var url = sessionStorage.getItem(this.savedLocationKey);
//		location.href = (url ? url : App.getContextPath() + "/home");
//	},
//
//	saveLocation : function($url) {
//		sessionStorage.setItem(this.savedLocationKey, $url);
//	},
//
//	clearSavedLocation : function() {
//		sessionStorage.removeItem(this.savedLocationKey);
//	},
//
	setLoggedInUser : function($user) {
		sessionStorage.setItem(this.userKey, JSON.stringify($user));
	},
//
//	getLoggedInUser : function() {
//		return JSON.parse(sessionStorage.getItem(this.userKey));
//	},
//
	clearAuthentication : function() {
		sessionStorage.removeItem(this.userKey);
		sessionStorage.removeItem(this.tokenKey);
	},

	getToken : function() {
		return sessionStorage.getItem(this.tokenKey);
	},

	setToken : function(token) {
		sessionStorage.setItem(this.tokenKey, token);
	},
//
//	isLoggedIn : function() {
//		return App.getToken() != null;
//	},
//
	setHeader : function($request) {
		$request.setRequestHeader("Authorization", "Token " + App.getToken());
	},
//
//	getBaseUrl : function() {
//		return location.protocol + "//" + location.host + this.getContextPath();
//	},
//
	getContextPath : function() {
		return "sedocweb";
	},
//
//	getUrlParameterByName : function($name) {
//		$name = $name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
//		var regex = new RegExp("[\\?&]" + $name + "=([^&#]*)"), results = regex.exec(location.search);
//		return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
//	},
//
	handle401 : function($request) {
		this.clearAuthentication();
		this.saveLocation(location.href);
		location.href = App.getContextPath() + "/login";
	},

	handle422 : function(request) {
		
		$($("form input, form select, form textarea").get().reverse()).each(function() {
			var id = $(this).attr('id');
			
			var messages = [];

			$.each(request.responseJSON, function(index, value) {
				var aux = value.property ? value.property : "global";
				if (id === aux) {
					messages.push(value.message);
					return;
				}
			});
			
			var message = $("#" + id + "-message");
			
			if (messages.length > 1) {
				var ul = message.children("ul");
				ul.empty();

				while (messages.length > 0) {
					ul.append("<li>" + messages.pop() + "</li>");
				}

				message.show();
				$(this).focus();

			} else if (messages.length === 1) {
				message.html(messages.pop()).show();
				$(this).focus();
			} else {
				message.hide();
			}
		});
	},
//
//	loadDateCombos : function($day, $month, $year) {
//		for (i = 1; i <= 31; i++) {
//			$($day).append(new Option(i, i));
//		}
//
//		var monthNames = [ "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
//				"Dezembro" ];
//		for (i = 1; i <= 12; i++) {
//			$($month).append(new Option(monthNames[i - 1], i));
//		}
//
//		var year = (new Date()).getFullYear();
//
//		for (i = year; i >= year - 100; i--) {
//			$($year).append(new Option(i, i));
//		}
//	},
//
//	translateStatus : function($status) {
//		switch ($status) {
//			case "pendent":
//				return '<span class="registration label label-warning">Aguardando pagamento</span>';
//
//			case "confirmed":
//				return '<span class="registration label label-success">Confirmada</span>';
//
//			case "cancelled":
//				return '<span class="registration label label-danger">Cancelada</span>';
//
//			default:
//				return '<span class="registration label label-danger">Status desconhecido</span>';
//		}
//	}
};

$.ajaxSetup({
	error : function($request) {
		switch ($request.status) {
			case 401:
				App.handle401($request);
				break;

			case 422:
				App.handle422($request);
				break;
		}
	}
});
