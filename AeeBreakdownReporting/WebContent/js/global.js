function refreshFeedback(data) {
	console.log(data.status);
	alert($('#feedback').val());
	if (data.status == 'completed'){
		
		if($('#feedback').val() != "") {
			$('#feedbackPanel').show();
		} else {
			$('#feedbackPanel').hide();
		}
	}
}

function hideFeedback() {
	$('#feedbackPanel').hide();
}