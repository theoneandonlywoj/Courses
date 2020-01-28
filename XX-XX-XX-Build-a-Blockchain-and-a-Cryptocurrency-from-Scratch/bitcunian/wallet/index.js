const ChainUtil = require('../chain-util');
const { INITIAL_BALANCE } = require('../config');

class Wallet {
    constructor() {
        // Initial balance to set some transactions
        this.balance = BALANCE;
        this.keyPair = ChainUtil.genKeyPair();
        this.publicKey = this.keyPair.getPublic().encode('hex');
    }

    toString() {
        return `Wallet:
        publicKey: ${this.publicKey}
        balance  : ${this.balance}`
    }
}

module.exports = Wallet;