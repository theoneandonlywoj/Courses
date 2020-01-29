const Transaction = require('./wallet/transaction');
const Wallet = require('./wallet');

const senderWallet = new Wallet();
const transaction = Transaction.newTransaction(senderWallet, 'r3c1p13nt', 50);

Transaction.signTransaction(transaction, senderWallet);
console.log('Transaction verified:', transaction);