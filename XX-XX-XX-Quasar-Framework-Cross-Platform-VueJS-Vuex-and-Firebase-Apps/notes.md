# Notes
## Install Quasar CLI
```bash
npm install -g @quasar/cli
```

## Create a new project
```bash
quasar create <project_name>
```

## Stopping a click propagating to the parent section
- When we have a parent div or section that has a click attached to, clicking on the child section will trigger that click event. 
- If we want to stop that behaviour (click event propagation), we need to add .stop to the click event on the child section.
- Example in the src/compontents/Tasks/Task.vue