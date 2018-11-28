/*
For this quiz, use articleList and DOM navigation methods to collect articleList's
sibling <h1> (var h1), children (var kids), and parent <div>s (var parents).

You must use articleList to navigate to the element(s)!
*/
function treeFiltering(){
            var articleList, h1Element, kids, parents;
            articleList = $('.article-list');
            h1Element = articleList.siblings('h1');
            kids = articleList.find('*');
            parents = articleList.parents('div');
            console.log(h1Element, kids, parents);
}