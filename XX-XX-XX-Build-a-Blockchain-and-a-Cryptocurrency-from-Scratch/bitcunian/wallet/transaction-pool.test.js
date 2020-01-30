const TransactionPool = require('./transaction-pool');
const Transaction = require('./transaction');
const Wallet = require('./index');

describe('TransactionPool', () => {
    let tp, waller, transaction;

    beforeEach(() => {
        tp = new TransactionPool();
        wallet = new Wallet();
        transaction = Transaction.newTransaction(wallet, 'r3c1p13nt', 30);
        tp.updateOrAddTransaction(transaction);
    });

    it('adds transaction to the pool', () => {
        const transactionForTransactionId = 
            tp.transactions.find(t => t.id === transaction.id)
        expect(transactionForTransactionId).toEqual(transaction);
    });

    it('updates transaction in the pool ', () => {
        const oldTransaction = JSON.stringify(transaction);
        const updatedTransaction = transaction.update(wallet, '4dd3d', 40);
        tp.updateOrAddTransaction(updatedTransaction);
        const transactionForTransactionId = 
            tp.transactions.find(t => t.id === transaction.id)

        expect(JSON.stringify(transactionForTransactionId)).not.toEqual(oldTransaction);
    });
});