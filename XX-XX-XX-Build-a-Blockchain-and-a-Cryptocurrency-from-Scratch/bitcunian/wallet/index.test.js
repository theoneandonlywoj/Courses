const Wallet = require('./index');
const TransactionPool = require('./transaction-pool');
const Blockchain = require('../blockchain');

describe('Wallet', () => {
    let wallet, bc, tp;

    beforeEach(() => {
        bc = new Blockchain();
        wallet = new Wallet();
        tp = new TransactionPool();

    });

    describe('creating a transaction', () => {
        let transaction, sendAmount, recipient;

        beforeEach(() => {
            sendAmount = 50;
            recipient = 'r4nd0m';
            transaction = wallet.createTransaction(recipient, sendAmount, bc, tp);
        });

        describe('and doing the same transaction', () => {
            beforeEach(() => {
                // Repeating the same transaction as above.
                wallet.createTransaction(recipient, sendAmount, bc, tp);
            });

            it('doubles the `sendAmount` subtracked from the wallet balance', () => {
                const transactionForSenderId =
                    transaction.outputs.find(output => output.address === wallet.publicKey);
                expect(transactionForSenderId.amount).toEqual(wallet.balance - sendAmount * 2);
            });

            it('clones the `sendAmount` for the recipient', () => {
                const transactionsForRecipient = 
                    transaction.outputs.filter(output => output.address === recipient);
                const transactionAmounts = transactionsForRecipient.map(t => t.amount);
                expect(transactionAmounts).toEqual([sendAmount, sendAmount]);
            });
        });
    });
});