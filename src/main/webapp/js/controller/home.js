$(function() {

	$('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		lang : 'pt-br',
		height : 'auto',
		defaultDate : moment().format('YYYY-MM-DD'),
		fixedWeekCount : false,
		minTime : '07:00:00',
		maxTime : '19:00:00',
		events : function(start, end, timezone, callback) {

			AgendaProxy.load().done(function(data) {
				console.log(data);
				var arrEvents = [];
				$.each(data, function(i, event) {
					if (event.start.split(" ")[1] === "00:00:00" && event.finish.split(" ")[1] === "00:00:00") {
						event.start = event.start.split(" ")[0];
						event.finish = event.finish.split(" ")[0];
					}
					arrEvents.push({
						id : event.id,
						title : event.title,
						start : event.start,
						end : event.finish
					});
				});
				callback(arrEvents);
			});

		}
	});

});
