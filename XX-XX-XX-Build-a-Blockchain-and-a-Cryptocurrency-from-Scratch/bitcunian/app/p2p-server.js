const Websocket = require('ws');

const MESSAGE_TYPES = {
    chain: 'CHAIN',
    transaction: 'TRANSACTION'
};
const P2P_PORT = process.env.P2P_PORT || 5001;
const peers = process.env.PEERS ? process.env.PEERS.split(',') : [];
console.log("Peers: ", peers);

class P2PServer {
    constructor(blockchainm, transactionPool) {
        this.blockchain = blockchain;
        this.transactionPool = transactionPool;
        this.sockets = [];
    }

    listen() {
        const server = new Websocket.Server({ port: P2P_PORT });
        server.on('connection', socket => this.connectSocket(socket));
        
        this.connectToPeers();

        console.log(`Listening for P2P connections on port ${P2P_PORT}...`);
    }

    connectToPeers() {
        peers.forEach(peer => {
            const socket = new Websocket(peer);

            socket.on('open', () => this.connectSocket(socket));
        });
    }

    connectSocket(socket) {
        this.sockets.push(socket);
        console.log('Socket connected');

        this.messageHandler(socket);
        this.sendChain(socket);
    }

    messageHandler(socket){
        socket.on('message', message => {
            const data = JSON.parse(message);
            // console.log('Data on message:', data);

            this.blockchain.replaceChain(data);
            console.log('Updated blockchain:', this.blockchain.chain);
        });
    }

    sendChain(socket) {
        const chainObject = {
            type: MESSAGE_TYPES.chain,
            chain: this.blockchain.chain
        }
        socket.send(JSON.stringify(chainObject));
    }

    syncChains() {
        this.sockets.forEach(socket => {
            this.sendChain(socket);
        });
    }

    broadcastTransaction(transaction) {
        this.sockets.forEach(socket => {
            this.sendTransaction(socket, transaction)
        });
    }

    sendTransaction(socket, transaction) {
        const transactionObject = {
            type: MESSAGE_TYPES.transaction,
            transaction: transaction
        }
        socket.send(JSON.stringify(transactionObject));
    }
}

module.exports = P2PServer;