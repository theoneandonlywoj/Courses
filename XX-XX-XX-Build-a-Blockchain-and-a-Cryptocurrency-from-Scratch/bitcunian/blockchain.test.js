const Block = require('./block');
const Blockchain = require('./blockchain');

describe('Blockchain', () => {
    let bc, bc2;

    beforeEach(() => {
        bc = new Blockchain();
        bc2 = new Blockchain();
    });

    it('starts with a genesis block', () => {
        expect(bc.chain[0]).toEqual(Block.genesis());
    })

    it('adds a new block', () => {
        const data = 'foo';
        bc.addBlock(data);

        expect(bc.chain[bc.chain.length - 1].data).toEqual(data);
    });

    it('validates a valid chain', () => {
        // Adding a block
        bc2.addBlock('data here');
        bc2.addBlock('and one more block');

        expect(bc.isChainValid(bc2.chain)).toBe(true);
    });

    it('invalidates a chain with corrupted data', () => {
        // Corrupting the data of the genesis block
        bc2.chain[0].data = 'Bad data';

        expect(bc.isChainValid(bc2.chain)).toBe(false);
    });
});