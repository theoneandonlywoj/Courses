const Block = require('./block');
const currentTimestamp = Date.now();
const block = new Block(currentTimestamp, "l4sth4sh", "h4sh", { first: "block" });
const genesisBlock = Block.genesis();
console.log("block: ", block.toString());
console.log("genesisBlock: ", genesisBlock.toString());