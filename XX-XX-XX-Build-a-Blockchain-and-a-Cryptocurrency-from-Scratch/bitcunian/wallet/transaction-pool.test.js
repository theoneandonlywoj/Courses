const TransactionPool = require('./transaction-pool');
const Transaction = require('./transaction');
const Wallet = require('./index');
const Blockchain = require('../blockchain');

describe('TransactionPool', () => {
    let tp, wallet, bc, transaction;

    beforeEach(() => {
        bc = new Blockchain();
        tp = new TransactionPool();
        wallet = new Wallet();
        transaction = wallet.createTransaction('r3c1p13nt', 30, bc, tp);
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

    it('clears the transaction pool', () => {
      tp.clear();
      expect(tp.transactions).toEqual([]);
    });

    describe('mixing valid and corrupt transactions', () => {
        let validTransactions;

        beforeEach(() => {
          validTransactions = [...tp.transactions];
          for (let i=0; i<6; i++) {
            wallet = new Wallet();
            transaction = wallet.createTransaction('r4nd-4dr355', 30, bc, tp);
            if (i%2==0) {
              transaction.input.amount = 9999;
            } else {
              validTransactions.push(transaction);
            }
          }
        });
      
        it('shows a difference between valid and corrupt transactions', () => {
          expect(JSON.stringify(tp.transactions)).not.toEqual(JSON.stringify(validTransactions));
        });
      
        it('grabs valid transactions', () => {
          expect(tp.validTransactions()).toEqual(validTransactions);
        });
      });
});