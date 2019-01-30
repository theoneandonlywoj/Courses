import {Socket} from "phoenix"
let socket = new Socket("/socket", {params: {token: window.userToken}})

socket.connect()

const createSocket = (topicId) => {
  let channel = socket.channel(`comments: ${topicId}`, {})
  channel.join()
    .receive("ok", resp => { 
      console.log("Channel joined!");
      renderComments(resp.comments); 
    })
    .receive("error", resp => { console.log("Unable to join", resp) })

  // Listening to new events
  channel.on(`comments:#{topicId}:new`, renderComment);

  document.querySelector("button").addEventListener('click', () => {
    const content = document.querySelector("textarea").value
    channel.push("comment:add", { messageContent: content })
  });
}

function renderComments(comments){
  // Creating HTML for each comment to be shown
  const renderedComments = comments.map(comment => {
    return commentTemplate(comment);
  });
  document.querySelector('.collection').innerHTML = renderedComments.join('');
};

function renderComment(event){
  const renderedComment = commentTemplate(event.comment);
  document.querySelector('.collection').innerHTML += renderedComment;
};

function commentTemplate(comment){
  return `
    <li class="collection-item">
      ${comment.content}
    </li>
    `
};
window.createSocket = createSocket
