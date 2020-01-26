const Block = require('./block');
const Blockchain = require('./index');

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

    it('invalidates a chain with a corrupted genesis block', () => {
        // Corrupting the data of the genesis block
        bc2.chain[0].data = 'Bad data';

        expect(bc.isChainValid(bc2.chain)).toBe(false);
    });

    it('invalidates a corrupt chain', () => {
        bc2.addBlock('foo');
        bc2.chain[1] = 'something else';

        expect(bc.isChainValid(bc2.chain)).toBe(false);
    });

    it('replaces the chain with a valid chain', () => {
        bc.addBlock('adding block');

        bc2.addBlock('first');
        bc2.addBlock('second');

        bc.replaceChain(bc2.chain);
        expect(bc.chain).toEqual(bc2.chain);
    });

    it('does not replace the chain with a shorter or equal length chain', () => {
        bc.addBlock('adding block');

        bc2.addBlock('first');
        bc2.addBlock('second');

        bc2.replaceChain(bc.chain);
        expect(bc2.chain).not.toEqual(bc.chain);
    });

    it('does not replace the chain with a longer, yet corrupted, chain', () => {
        bc2.addBlock('first');
        bc2.addBlock('second');

        bc2.chain[1].data = 'some random incorrect data';

        bc.replaceChain(bc2.chain);
        expect(bc.chain).not.toEqual(bc2.chain);
    });
});