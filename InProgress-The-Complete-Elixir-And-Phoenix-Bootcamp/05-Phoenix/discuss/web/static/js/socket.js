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

  document.querySelector("button").addEventListener('click', () => {
    const content = document.querySelector("textarea").value
    channel.push("comment:add", { messageContent: content })
  });
}

function renderComments(comments){
  // Creating HTML for each comment to be shown
  const renderedComments = comments.map(comment => {
    return `
    <li class="collection-item">
      ${comment.content}
    </li>
    `
  });
  document.querySelector('.collection').innerHTML = renderedComments.join('');
};
window.createSocket = createSocket
