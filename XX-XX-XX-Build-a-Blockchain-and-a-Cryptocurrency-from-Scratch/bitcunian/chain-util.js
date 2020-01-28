const EC = require('elliptic').ec;

// https://en.bitcoin.it/wiki/Secp256k1
const ec = new EC('secp256k1');

class ChainUtil {
    static genKeyPair() {
        return ec.genKeyPair();
    }
}

module.exports = ChainUtil;