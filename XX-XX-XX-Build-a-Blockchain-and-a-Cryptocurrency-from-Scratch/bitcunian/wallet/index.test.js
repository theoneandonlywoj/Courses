const Wallet = require('./index');
const TransactionPool = require('./transaction-pool');
const Blockchain = require('../blockchain');
const { INITIAL_BALANCE } = require('../config');

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

    describe("calculating a balance", () => {
        let addBalance, repeatAdd, senderWallet;

        beforeEach(() => {
            senderWallet = new Wallet();
            addBalance = 100;
            repeatAdd = 3;

            for(let i=0; i < repeatAdd; i++) {
                senderWallet.createTransaction(wallet.publicKey, addBalance, bc, tp);
            }

            bc.addBlock(tp.transactions);
        });

        it('calculates the balance for the blockchain transactions matching the recipient', () => {
            expect(wallet.calculateBalance(bc)).toEqual(INITIAL_BALANCE + addBalance * repeatAdd);
        });

        it('calculates the balance for the blockchain transactions matching the sender', () => {
            expect(senderWallet.calculateBalance(bc)).toEqual(INITIAL_BALANCE - addBalance * repeatAdd);
        });
    });
});