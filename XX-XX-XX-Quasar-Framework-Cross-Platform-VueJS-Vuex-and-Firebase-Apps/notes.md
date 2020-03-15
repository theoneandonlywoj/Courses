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

## Adding a boot file
```bash
quasar new boot <name>
```

## Install Firebase Package
```bash
npm install --save firebase
```

## Change the main icon
### Go to src/index.template.html
### Change the line below to the location of the web icon (128 x 128)
- from:
```
<link rel="icon" type="image/png" href="statics/app-logo-128x128.png">
```
- to:
```
<link rel="icon" type="image/png" href="statics/icons/icon-128x128.png">
```
### Comment out the following line:
```
<link rel="icon" type="image/ico" href="statics/icons/favicon.ico">
```

## Build for production (Web)
```bash
quasar build
```

## Adding Mac Platform
```bash
quasar mode add electron
```

## Run dev in Electron mode
```bash
quasar dev -m electron
```

## Build for production (Electron)
```bash
quasar build -m electron
```

## Install Cordova
```bash
sudo npm install -g cordova
```

## Instal Xcode (for iOS)
- Go to developer.apple.com/download/more
- Sign in with you Apple ID
- Download Xcode
- Extract and install it

## Add the Cordova mode to the project
```bash
quasar mode add cordova
```

## Add iOS and Android modes
```bash
cd src-cordova
cordova platform add android
cordova platform add ios
```

## Check if Cordova requirements are fulfilled
```bash
cordova requirements
```

## Splash Screen Configuration
https://cordova.apache.org/docs/en/latest/reference/cordova-plugin-splashscreen/