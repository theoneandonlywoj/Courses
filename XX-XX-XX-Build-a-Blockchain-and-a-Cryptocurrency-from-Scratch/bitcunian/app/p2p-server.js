const Websocket = require('ws');

const P2P_PORT = process.env.P2P_PORT || 5001;
const peers = process.env.PEERS ? process.env.PEERS.split(',') : [];
console.log("Peers: ", peers);

class P2PServer {
    constructor(blockchain) {
        this.blockchain = blockchain;
        this.sockets = [];
    }

    listen() {
        const server = new Websocket.Server({ port: P2P_PORT });
        server.on('connection', socket => this.connectSocket(socket));
        console.log(`Listening for P2P connections on port ${P2P_PORT}...`);
    }

    connectSocket(socket) {
        this.sockets.push(socket);
        console.log('Socket connected', socket);
    }
}