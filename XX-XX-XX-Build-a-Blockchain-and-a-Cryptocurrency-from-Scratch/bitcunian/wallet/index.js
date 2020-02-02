const ChainUtil = require('../chain-util');
const Transaction = require('./transaction');
const { INITIAL_BALANCE } = require('../config');

class Wallet {
    constructor() {
        // Initial balance to set some transactions
        this.balance = INITIAL_BALANCE;
        this.keyPair = ChainUtil.genKeyPair();
        this.publicKey = this.keyPair.getPublic().encode('hex');
    }

    toString() {
        return `Wallet:
        publicKey: ${this.publicKey}
        balance  : ${this.balance}`
    }

    sign(dataHash) {
        return this.keyPair.sign(dataHash);
    }

    createTransaction(recipient, amount, transactionPool) {
        if(amount > this.balance) {
            console.log(`Amount ${amount} exceeds the current balance of ${this.balance}`);
            return;
        }
        
        let transaction =
            transactionPool.existingTransaction(this.publicKey);

        if(transaction) {
            transaction.update(this, recipient, amount);
        } else {
            transaction = Transaction.newTransaction(this, recipient, amount);
            transactionPool.updateOrAddTransaction(transaction);
        }

        return transaction;
    }

    calculateBalance(blockchain) {
        let balance = this.balance;
        let transactions = [];
        blockchain.chain.forEach(block => {
            block.data.forEach(transaction => {
                transactions.push(transaction);
            })
        });
        const walletInputTransactions =
            transactions.filter(t => t.input.address === this.publicKey);

        let startTime = 0;
        // If there are any transactions for that wallet at all.
        if(walletInputTransactions.length > 0) {
            const recentInputTransaction =
                walletInputTransactions.reduce((prev, curr) => {
                    return prev.input.timestamp > curr.input.timestamp ? prev : curr
                });
            balance = 
                recentInputTransaction.outputs.find(output =>
                        output.address === this.publicKey).amount;
            startTime = recentInputTransaction.input.timestamp;
            // Adding all currency values that come after that record.
        }
        transactions.forEach(transactions => {
            if(transaction.input.timestamp > startTime) {
                transaction.outputs.forEach(output => {
                    if(output.address === this.publicKey) {
                        balance += output.amount;
                    }
                })
            }
        });

        return balance;
    }

    static blockchainWallet() {
        const blockchainWallet = new this();
        blockchainWallet.address = 'blockchain-wallet';
        return blockchainWallet;
    }
}

module.exports = Wallet;