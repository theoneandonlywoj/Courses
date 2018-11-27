function appendChildElements(){
	var firstArticleItem;
	firstArticleItem = $('.article-item').first();
	console.log(firstArticleItem);
	// New element preprended as a child of element firstArticleItem
	firstArticleItem.prepend('<img src="https://images.pexels.com/photos/45846/toy-car-mini-cooper-beach-45846.jpeg?auto=compress&cs=tinysrgb&h=350">');
	// New element appended as a child of element firstArticleItem
	firstArticleItem.append('<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjgVO7jUP52qaBGTTTVFz7HkgtsyatDEPjPfpo7cGkLU-vvMs6TQ">');
}