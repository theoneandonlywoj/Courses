# Notes
## Adding jQuery to a HTML file
### Local
```javascript 
<script src='js/jquery.min.js'></script>
```
### Local
 jQuery official
```javascript 
<script src='//code.jquery.com/jquery-1.11.1.min.js'></script>
```
### Local
 Content Delivery Network
```javascript 
<script src='//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
```

## Family Tree Selection
- .parent() - parent of a given object (1 level above)
- .parents() - all parents, grandparents etc... of a given object (all the way up to the top)
- .children() - all immediate children of a given object (1 level below)
- .find() - all children og a given object (all level below)
- .siblings - all siblings (objects having the same parent as the given object)

## Appending elements with jQuery
- .preprend() - prepending a child element
- .append() - appending a child element 
- .insertBefore() - inserting a sibling element before the given element
- .insertAfter() - inserting a sibling element after the given element