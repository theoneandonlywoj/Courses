function buildFamilyTree(){
	var family1, family2, bruce, madison, hunter;
	family1 = $('#family1');
	family2= $('<div id="family2"><h1>Family 2</h1></div>');

	bruce= $('<div id="bruce"><h2>Bruce</h2></div>');
	madison= $('<div id="madison"><h3>Madison</h3></div>');
	hunter= $('<div id="hunter"><h3>Hunter</h3></div>');

	family2.insertAfter(family1);
	family2.append(bruce);
	bruce.append(madison);
	bruce.append(hunter);

}