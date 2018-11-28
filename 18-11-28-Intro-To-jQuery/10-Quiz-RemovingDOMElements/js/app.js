function removeAnElement(){
	var articleItems, toBeRemovedElement;
	articleItems = $('.article-item');
	toBeRemovedElement = articleItems.find('ul');
	toBeRemovedElement.remove();
}