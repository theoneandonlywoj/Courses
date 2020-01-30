const Transaction = require('./transaction');

class TransactionPool {
    constructor() {
        this.transactions = [];
    }

    updateOrAddTransaction(transaction) {
        const transactionWithId = 
            this.transactions.find(t => t.id === transaction.id);

        if(transactionWithId) {
            const transactionWithIdIndex = this.transactions.indexOf(transactionWithId);
            this.transactions[transactionWithIdIndex] = transaction;
        } else {
            this.transactions.push(transaction);
        }
    }

    existingTransaction(senderAddress) {
        return this.transactions.find(t => t.input.address = senderAddress);
    }
}

module.exports = TransactionPool;