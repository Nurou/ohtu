'use strict';

const POINT_THRESHOLD_FOR_ADVANTAGE = 4;
const POINT_THRESHOLD_FOR_WIN = 2;

function getScore(playerOneScore, playerTwoScore) {
  var playerScoresAreEqual = playerOneScore === playerTwoScore;
  var eachScoredAtLeastThree =
    playerOneScore >= POINT_THRESHOLD_FOR_ADVANTAGE || playerTwoScore >= POINT_THRESHOLD_FOR_ADVANTAGE;

  if (playerScoresAreEqual) {
    switch (playerOneScore) {
      case 0:
        return 'Love-All';
      case 1:
        return 'Fifteen-All';
      case 2:
        return 'Thirty-All';
      default:
        return 'Deuce';
    }
  }

  if (eachScoredAtLeastThree) {
    var playerOnesPointLead = playerOneScore - playerTwoScore;

    var playerOneLeadsByOne = playerOnesPointLead === 1;
    var playerTwoLeadsByOne = playerOnesPointLead === -1;

    if (playerOneLeadsByOne) return 'Advantage player1';
    if (playerTwoLeadsByOne) return 'Advantage player2';

    if (playerOnesPointLead >= POINT_THRESHOLD_FOR_WIN) return 'Win for player1';

    return 'Win for player2';
  }

  // otherwise...
  var runningScore = '';
  var tempScore = 0;

  for (var i = 1; i < 3; i++) {
    if (i === 1) {
      tempScore = playerOneScore;
    } else {
      runningScore += '-';
      tempScore = playerTwoScore;
    }
    switch (tempScore) {
      case 0:
        runningScore += 'Love';
        break;
      case 1:
        runningScore += 'Fifteen';
        break;
      case 2:
        runningScore += 'Thirty';
        break;
      case 3:
        runningScore += 'Forty';
        break;
    }
  }
  return runningScore;
}

module.exports = getScore;
