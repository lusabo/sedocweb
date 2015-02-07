$(function() {

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,basicWeek,basicDay'
		},
		lang: 'pt-br',
		height: 'auto',
		defaultDate: '2015-02-07',
		events: [
					{
						title: 'Aniversário de Paulo',
						start: '2015-02-19'
					},
					{
						title: 'Treinamento',
						start: '2015-02-02',
						end: '2015-02-06'
					}
				]
	});	

});
