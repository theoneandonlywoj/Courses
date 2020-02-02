const Blockchain = require('./blockchain');

const bc = new Blockchain();

for(let i=0; i < 100; i++) {
    console.log(bc.addBlock(`Block data: ${i}`).toString());
}