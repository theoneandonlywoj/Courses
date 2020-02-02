const Transaction = require('./transaction');
const Wallet = require('./index');
const { MINING_REWARD } = require('../config');

describe('Transaction', () => {
  let transaction, wallet, recipient, amount;
  beforeEach(() => {
    wallet = new Wallet();
    amount = 50;
    recipient = 'r3c1p13nt';
    transaction = Transaction.newTransaction(wallet, recipient, amount);
  });

  it('ouputs the `amount` subtracted from the wallet balance', () => {
    expect(transaction.outputs.find(output => output.address === wallet.publicKey).amount)
      .toEqual(wallet.balance - amount);
  });

  it('outputs the `amount` added to the recipient', () => {
    expect(transaction.outputs.find(output => output.address === recipient).amount)
      .toEqual(amount);
  });

  it('outputs the balance of the wallet', () => {
    expect(transaction.input.amount).toEqual(wallet.balance);
  });

  it('validates a valid transaction', () => {
    // The new transaction defined in the beforeEach function is not corrupted
    expect(Transaction.verifyTransaction(transaction)).toBe(true);
  });

  it('invalidates an corrupt transaction', () => {
    // Corrupting the transaction
    transaction.outputs[0].amount = 50000000;
    expect(Transaction.verifyTransaction(transaction)).toBe(false);
  });

  describe('transacting with an amount that exceeds the balance', () => {
    beforeEach(() => {
      amount = 50000;
      transaction = Transaction.newTransaction(wallet, recipient, amount);
    });

    it('does not create the transaction', () => {
      expect(transaction).toEqual(undefined);
    });
  });

  describe('and updating a transaction', () => {
    let nextAmount, nextRecipient;

    beforeEach(() => {
      nextAmount = 20;
      nextRecipient = "n3xtAddr3ss";
      transaction = transaction.update(wallet, nextRecipient, nextAmount);
    });

    it("subtracts the next amount from the senders's output", () => {
      const sendersBalance = 
        transaction.outputs.find(output => output.address === wallet.publicKey).amount;
      const balanceAfterAllTransactions = wallet.balance - amount - nextAmount;

      expect(sendersBalance).toEqual(balanceAfterAllTransactions);
    });

    it('outputs the amount for the new recipient', () => {
      const transactionForTheNextRecipient = 
        transaction.outputs.find(output => output.address === nextRecipient)
      expect(transactionForTheNextRecipient.amount).toEqual(nextAmount);
    });
  });

  describe('creating a reward transaction', () => {
    beforeEach(() => {
      transaction = Transaction.rewardTransaction(wallet, Wallet.blockchainWallet());
    });

    it(`rewards the miner's wallet`, () => {
      const transactionForMinersWallet = 
        transaction.outputs.find(output => output.address === wallet.publicKey);
      expect(transactionForMinersWallet.amount).toEqual(MINING_REWARD);
    });
  });
});