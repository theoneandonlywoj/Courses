const Block = require('./block');
const { MINE_RATE } = require('../config');

describe('Block', () => {
    let data, lastBlock, block;

    beforeEach(() => {
        data = 'bar';
        lastBlock = Block.genesis();
        block = Block.mineBlock(lastBlock, data);
    });

    it('sets the `data` to match the given input', () => {
        expect(block.data).toEqual(data);
    });

    it('sets the `lastHash` to match hash of the last block', () => {
        expect(block.lastHash).toEqual(lastBlock.hash);
    });

    it('generates a hash with number of the leading zero equal to the difficulty', () => {
        expect(block.hash.substring(0, block.difficulty)).toEqual('0'.repeat(block.difficulty));
    });

    it('lowers difficulty for slowly mined blocks', () => {
        expect(Block.adjustDifficulty(block, block.timestamp + (MINE_RATE * 2)))
            .toEqual(block.difficulty - 1)
    });

    it('increases difficulty for rapidly mined blocks', () => {
        expect(Block.adjustDifficulty(block, (block.timestamp + (MINE_RATE / 4))))
            .toEqual(block.difficulty + 1)
    });
});