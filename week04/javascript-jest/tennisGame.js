const getScore1 = require('./tennis1');
const getScore2 = require('./tennis2');
const getScore3 = require('./tennis3');

(function tennisGame() {
  console.log(getScore1(0, 0));
  console.log(getScore1(1, 0));
  console.log(getScore1(2, 0));
  console.log(getScore1(2, 1));
  console.log(getScore1(3, 1));
  console.log(getScore1(4, 1));
})();
