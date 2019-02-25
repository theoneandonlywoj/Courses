new Vue({
  el: '#app',
  data: {
    playerHealth: 100,
    monsterHealth: 100,
    gameIsRunning: false,
  },
  methods: {
    startGame: function(){
      this.gameIsRunning = true;
      this.playerHealth = 100;
      this.monsterHealth = 100;
    },
    attack: function() {
      this.monsterAttacks();
      this.playerAttacks(false);
    },
    specialAttack: function() {
      this.monsterAttacks();
      this.playerAttacks(true);
    },
    heal: function() {
      // Not healing more than 100%
      this.playerHealth = Math.min(this.playerHealth + 10, 100);
      this.monsterAttacks();
    },
    giveUp: function(){
      this.gameIsRunning = false;
    },
    calculateDamage: function(minDmg, maxDmg) {
      return Math.max(Math.floor(Math.random() * maxDmg), minDmg);
    },
    checkWin: function(){
      if (this.monsterHealth <= 0) {
        // The 'confirm()' method is generated by JS
        if (confirm("You won! New game?")) {
          this.startGame();
        } else {
          this.gameIsRunning = false;
        }
        return true;
      } else {
        return false;
      }
    },
    checkLost: function(){
      if (this.playerHealth <= 0) {
        // The 'confirm()' method is generated by JS
        if (confirm("You lost! New game?")) {
          this.startGame();
        } else {
          this.gameIsRunning = false;
        }
        return true;
      } else {
        return false;
      }
    },
    playerAttacks: function(isSpecial){
      var dmgMultiplier = 1;
      if (isSpecial){
        dmgMultiplier = 2;
      }
      this.monsterHealth -= dmgMultiplier * this.calculateDamage(3, 10);
      // If we win, we do not want to execute the rest of the code
      // Using 'return;' to stop the execution of the rest of the code.
      if (this.checkWin()){
        return;
      }
    },
    monsterAttacks: function(){
      this.playerHealth -= this.calculateDamage(5, 15);
      if (this.checkLost()){
        return;
      }
    }


  }
});
