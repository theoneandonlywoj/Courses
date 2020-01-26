const Block = require('./block')

class Blockchain {
    constructor() {
        this.chain = [Block.genesis()];
    }

    addBlock(data) {
        const lastBlock = this.chain[this.chain.length - 1];
        const block = Block.mineBlock(lastBlock, data);
        this.chain.push(block);

        return block;
    }


    isChainValid(chain) {
        // Checking if the genesis blocks are the same
        if(JSON.stringify(chain[0]) !== JSON.stringify(Block.genesis())) {
            return false;
        } 

        for(let i=1; i < chain.length; i++) {
            const block = chain[i];
            const previousBlock = chain[i - 1]

            if(block.lastHash !== previousBlock.hash) {
                return false;
            }
        }

        return true;
    }
}

module.exports = Blockchain;