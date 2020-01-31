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

    validTransactions() {
        return this.transactions.filter(transaction => {
            const outputTotal = transaction.outputs.reduce((total, output) => {
                return total + output.amount;
            }, 0);

            if(transaction.input.amount !== outputTotal) {
                console.log(`Invalid transaction from ${transaction.input.address}!.
                             Reason: corrupted transaction amount!`)
                return;
            }

            if(!Transaction.verifyTransaction(transaction)) {
                console.log(`Invalid transaction from ${transaction.input.address}!.
                             Reason: verification failed!`);
                return;
            }

            return transaction;
        })
    }
}

module.exports = TransactionPool;