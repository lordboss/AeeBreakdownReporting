function editArea(index, name) {
	$('#title' + index).hide();
	$('#updateForm' + index).show();
	$('#updateOriginalName' + index).val(name);
	$('#updateName' + index).val(name);
}

function executeUpdate(index) {
	$('.areaToUpdate').val($('#updateOriginalName' + index).val());
	$('.newName').val($('#updateName' + index).val());
	$('.update').click();
}

function cancelUpdate(index) {
	$('#updateForm' + index).hide();
	$('#title' + index).show();
}

function removeArea(name) {
	$('.areaToDelete').val(name);
	$('.delete').click();
}