# VueJS
## Installing Vue CLI
``` bash
npm install -g @vue/cli
```
## Installing Vue CLI globally
``` bash
npm install -g @vue/cli-init
```
## Creating projects with Vue CLI and Webpack Simple Template (version 2)
``` bash
vue init webpack-simple projectName
```

## Creating projects with Vue CLI (version 3)
``` bash
vue create project-name
```

## Linting
``` bash
npm run lint
```

## Go to the folder, install the dependencies and run it in the dev mode
``` bash
cd projectName
npm install
npm run dev
```
## Build the project
```bash
npm run build
```
## Serve in production with Docker
https://cli.vuejs.org/guide/deployment.html#bitbucket-cloud

## Installing Vue-resource as a production dependency
```bash
npm install --save vue-resource
```

## Installing Vue-Router
```bash
npm install --save vue-router
```

## Installing Vuex
```bash
npm install --save vuex
```

## Adding support for ES6
```bash
npm install --save-dev babel-preset-stage-2
```

## Installing Axios
```bash
npm install --save axios
```

## Installing Vuelidate
```bash
npm install --save vuelidate
```

## Asset Management
- Assets should be stored in `src\assets`, because Webpack will optimize the usage of them, unlike if they were stored in `public\img`.

## Adding plugins (must have name vue-cli-plugin-pluginName)
```bash
vue add pluginName
```

## Installing SASS loader
```bash
npm install sass-loader node-sass
```