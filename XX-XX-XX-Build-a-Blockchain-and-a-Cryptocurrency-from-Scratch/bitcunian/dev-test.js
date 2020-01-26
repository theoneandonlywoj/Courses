const Block = require('./block');

const firstNongenesisBlock = Block.mineBlock(Block.genesis(), []);
console.log(firstNongenesisBlock.toString());