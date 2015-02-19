$(function() {

	App.loadMenu();

	$("form").submit(function(e) {
		e.preventDefault();

		var filter = $("#filter").val();
		var showInactive = $("#showInactive").is(':checked');

		JobTitleProxy.find(filter, showInactive).done(loadTable);
	});

});

function loadTable(data) {
	var dynatable = $('.table').dynatable({
		features : {
			paginate : true,
			sort : true,
			pushState : true,
			search : false,
			recordCount : true,
			perPageSelect : false
		},
		params : {
			records : "registros"
		},
		inputs : {
			recordCountText : 'Mostrando ',
			paginationPrev: 'Anterior',
		    paginationNext: 'Proximo'
		},
		dataset : {
		    page: null,
			records : data,
		    perPageDefault: 10
		}
	}).data('dynatable');

	dynatable.settings.dataset.originalRecords = data;
	dynatable.process();
}
