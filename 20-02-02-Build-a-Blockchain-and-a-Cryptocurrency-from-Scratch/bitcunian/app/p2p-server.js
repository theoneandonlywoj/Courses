const Websocket = require('ws');

const MESSAGE_TYPES = {
    chain: 'CHAIN',
    transaction: 'TRANSACTION',
    clear_transaction: 'CLEAR_TRANSACTIONS'
};
const P2P_PORT = process.env.P2P_PORT || 5001;
const peers = process.env.PEERS ? process.env.PEERS.split(',') : [];
console.log("Peers: ", peers);

class P2PServer {
    constructor(blockchain, transactionPool) {
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
            switch(data.type) {
                case MESSAGE_TYPES.chain:
                    this.blockchain.replaceChain(data.chain);
                    break;
                case MESSAGE_TYPES.transaction:
                    this.transactionPool.updateOrAddTransaction(data.transaction);
                    break;
                case MESSAGE_TYPES.clear_transaction:
                    this.transactionPool.clear();
                    break;
            }
            
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

    broadcastClearTransactions() {
        this.sockets.forEach(socket => {
            socket.send(JSON.stringify({
                type: MESSAGE_TYPES.clear_transaction
            }));
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