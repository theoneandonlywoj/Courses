const { INITIAL_BALANCE } = require('../config');

class Wallet {
    constructor() {
        // Initial balance to set some transactions
        this.balance = BALANCE;
        this.keyPair = null;
        this.publicKey = null;
    }

    toString() {
        return `Wallet:
        publicKey: ${this.publicKey}
        balance  : ${this.balance}`
    }
}

module.exports = Wallet;