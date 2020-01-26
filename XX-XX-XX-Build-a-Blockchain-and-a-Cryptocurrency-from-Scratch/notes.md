# Notes
## Initialise project
```bash
npm init -y
```

## Install Nodemon as a dev dependency
```bash
npm i nodemon --save-dev
```

## SHA 256
SHA 256 is a one-way hash that produces an unique 32 byte (256 bit) hash value for unique inputs.

## Install CryptoJS
```bash
npm i crypto-js --save
```
## Install JEST
```bash
npm i jest --save-dev
```

## Install Express
```bash
npm i express --save
```

## Install Body Parser
```bash
npm i body-parser --save
```

## Install Websocket
```bash
npm i ws --save
```

## Run three peers example
```bash
HTTP_PORT=3001 P2P_PORT=5001 npm run dev
```
```bash
HTTP_PORT=3002 P2P_PORT=5002 PEERS=ws://localhost:5001 npm run dev
```

```bash
HTTP_PORT=3002 P2P_PORT=5002 PEERS=ws://localhost:5001, ws://localhost:5002 npm run dev
```