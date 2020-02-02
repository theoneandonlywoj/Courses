const Wallet = require('../wallet');
const Transaction = require('../wallet/transaction');

class Miner {
    constructor(blockchain, transactionPool, wallet, p2pServer) {
        this.blockchain = blockchain;
        this.transactionPool = transactionPool;
        this.wallet = wallet;
        this.p2pServer = p2pServer;
    }

    mine() {
        const validTransactions = this.transactionPool.validTransactions();
        // Include a reward for the miner
        const blockchainRewardTransaction =
            Transaction.rewardTransaction(this.wallet, Wallet.blockchainWallet());
        validTransactions.push(blockchainRewardTransaction);
        // Create a block of valid transactions
        const block = this.blockchain.addBlock(validTransactions);
        // Synchronize the chains in the P2P server
        this.p2pServer.syncChains();
        // Clear transactionPool and broadcast the same to other miners
        this.transactionPool.clear();
        this.p2pServer.broadcastClearTransactions();

        return block;
    }
}

module.exports = Miner;