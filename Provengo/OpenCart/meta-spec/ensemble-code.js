// @provengo summon ctrl

/**
 * List of events "of interest" that we want test suites to cover.
 */
// const GOALS = [
//     any(/Howdy/),
//     any(/Mars/),
//     Ctrl.markEvent("Classic!")
// ];
//
// const makeGoals = function(){
//     return [ [ any(/Howdy/), any(/Venus/) ],
//              [ any(/Mars/) ],
//              [ Ctrl.markEvent("Classic!") ] ];
// }

const GOALS = [
    any("End(submit_review)"),         // Guest review submission event
    any("End(saveChanges)")     // Admin saves changes after disabling guest comments

];

/**
 * Creates structured goals based on meaningful event sequences.
 */

const makeGoals = function() { return[

        //Pairs covering interactions between guest review submission and admin actions
        [ any("Start(navigate_to_reviews)"), any ("Start(LoginAsAdmin)"),any("End(navigate_to_reviews)"), any("End(LoginAsAdmin)")],
        [ any("Start(navigate_to_reviews)"), any("Start(navigateToReviewsButton)"),any("End(navigate_to_reviews)"), any("End(navigateToReviewsButton)") ],
        [ any("Start(navigate_to_reviews)"), any("Start(disable_comments_guests)"),any("End(navigate_to_reviews)"), any("End(disable_comments_guests)") ], // without the start(savingchanges)

        // Pairs covering interactions after filling review
        [ any("Start(fill_review)"), any("Start(LoginAsAdmin)"),any("End(fill_review)"), any("End(LoginAsAdmin)") ],
        [ any("Start(fill_review)"), any("Start(navigateToReviewsButton)"),any("End(fill_review)"), any("End(navigateToReviewsButton)") ],
        [ any("Start(fill_review)"), any("Start(disable_comments_guests)"),any("End(fill_review)"), any("End(disable_comments_guests)") ],

        // Pairs covering interactions after submitting review
        [ any("Start(submit_review)"), any("Start(LoginAsAdmin)"),any("End(submit_review)"), any("End(LoginAsAdmin)")  ],
        [ any("Start(submit_review)"), any("Start(navigateToReviewsButton)"),any("End(submit_review)"), any("End(navigateToReviewsButton)")  ],
        [ any("Start(submit_review)"), any("Start(disable_comments_guests)"),any("End(submit_review)"), any("End(disable_comments_guests)") ],
        [ any("End(submit_review)"), any("End(saveChanges)") ]
    ];
}
/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 * 
 * It make no difference if a goal was met more then once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals( ensemble ) {
    const unreachedGoals = [];
    for ( let idx=0; idx<GOALS.length; idx++ ) {
        unreachedGoals.push(GOALS[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
            let event = test[eventIdx];
            for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if ( unreachedGoal.contains(event) ) {
                    unreachedGoals.splice(ugIdx,1);
                }
            }
        }
    }

    return GOALS.length-unreachedGoals.length;
}

/**
 * Ranks potential test suites based on the percentage of goals they cover.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events.
 *
 * Multiple ranking functions are supported - to change ranking function,
 * use the `ensemble.ranking-function` configuration key, or the 
 * --ranking-function <functionName> command-line parameter.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
 function rankingFunction(ensemble) {

    // How many goals did `ensemble` hit?
    const metGoalsCount = rankByMetGoals(ensemble);
    // What percentage of the goals did `ensemble` cover?
    const metGoalsPercent = metGoalsCount/GOALS.length;

    return metGoalsPercent * 100; // convert to human-readable percentage
}

///////


function rankByMetTwoWayGoals( ensemble ) {
    let list_of_goals = makeGoals()
    let count = 0
    for (let i =0;i<list_of_goals.length;i++) {
        goal = list_of_goals[i]
        GOALS = goal
        count += rankByMetGoals(ensemble)
    }
    return count;
}

function rankingFunctionTwoWay(ensemble) {
    // How many two-way goals did `ensemble` hit?
    const metGoalsCount = rankByMetTwoWayGoals(ensemble);

    // What percentage of the two-way goals did `ensemble` cover?
    // System.out.print(makeGoals().length)
    // console.log(makeGoals().length);
    // console.log(metGoalsCount);

    const metGoalsPercent = (metGoalsCount / makeGoals().length);

    return metGoalsPercent  * 100; // Return as a percentage
}



