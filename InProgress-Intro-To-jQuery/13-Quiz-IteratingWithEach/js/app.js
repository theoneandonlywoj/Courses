function numberAdder(){
	var text, number;

	text =$(this).text();

	number = text.length;

	$(this).text(text + " : " + number);
}

function forEach(){
	// Iterating through all p tags
	$('p').each(numberAdder);
}
