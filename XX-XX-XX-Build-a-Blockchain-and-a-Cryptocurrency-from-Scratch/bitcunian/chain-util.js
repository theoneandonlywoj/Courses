const EC = require('elliptic').ec;
// Timestamp based UUID
const uuidV1 = require('uuid/v1');

// https://en.bitcoin.it/wiki/Secp256k1
const ec = new EC('secp256k1');

class ChainUtil {
    static genKeyPair() {
        return ec.genKeyPair();
    }

    static id() {
        return uuidV1();
    }
}

module.exports = ChainUtil;