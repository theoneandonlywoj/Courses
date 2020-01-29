const ChainUtil = require('../chain-util');

class Transaction {
    constructor() {
        this.id = ChainUtil.id();
        this.input = null;
        this.outputs = [];
    }

    static newTransaction(senderWallet, recipient, amount) {
        const transaction = new this();
        if(amount > senderWallet.balance) {
            console.log(`Amount ${amount} exceeds the balance`);
            return;
        }

        // First output is the sender and his / her new balance
        // Second is the recipient and his / her additional balance
        // ... is ES6 decomposition functionality
        transaction.outputs.push(...[
            { amount: senderWallet.balance - amount,
              address: senderWallet.publicKey },
            { amount: amount, 
              address: recipient}
        ])

        Transaction.signTransaction(transaction, senderWallet);
        return transaction;
    }

    static signTransaction(transaction, senderWallet) {
        transaction.input = {
            timestamp: Date.now(),
            amount: senderWallet.balance,
            address: senderWallet.publicKey,
            signature: senderWallet.sign(ChainUtil.hash(transaction.outputs))
        }
    }

    static verifyTransaction(transaction) {
        return ChainUtil.verifySignature(transaction.input.address,
                                         transaction.input.signature,
                                         ChainUtil.hash(transaction.outputs))
    }
}

module.exports = Transaction;
