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

        return transaction;
    }
}

module.exports = Transaction;
