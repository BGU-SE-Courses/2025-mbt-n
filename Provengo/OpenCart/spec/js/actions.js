/* @provengo summon selenium */
/* @provengo summon ctrl */


const A = Event("End(submit_review)")
const B = Event("Start(saveChanges)")

function navigate_to_reviews(session) {
    Ctrl.doSleep(5000)     // best command ever
    sync({request: Event("Start(navigate_to_reviews)")});
    session.runCode(scrolling.down)
    Ctrl.doSleep(1000)     // best command ever
    session.click(xpaths.reviewButton);
    Ctrl.doSleep(1000)
    sync({request: Event("End(navigate_to_reviews)")});
}


function fill_review(session, comment, name, rating) {
    sync({request: Event("Start(fill_review)")});
    session.writeText(xpaths.nameField, name);
    Ctrl.doSleep(1000)
    session.writeText(xpaths.reviewField, comment);
    Ctrl.doSleep(1000)
    session.click(xpaths.ratingField(rating))
    Ctrl.doSleep(1000)
    sync({request: Event("End(fill_review)")});
}

function submit_review(session) {
    sync({request: Event("Start(submit_review)")});
    session.click(xpaths.submitButton);
    Ctrl.doSleep(1000)
    sync({request: A});
}

function loginAsAdmin(session, username, password) {
    Ctrl.doSleep(5000)
    sync({request: Event("Start(LoginAsAdmin)")});
    Ctrl.doSleep(2000)
    session.writeText(xpaths.adminUserName, username);
    Ctrl.doSleep(1000)
    session.writeText(xpaths.adminPassword, password);
    Ctrl.doSleep(1000)
    session.click(xpaths.adminConfirmLoginButton);
    sync({request: Event("End(LoginAsAdmin)")});
}

function navigateToReviewsButton(session) {
    sync({request: Event("Start(navigateToReviewsButton)")});
    session.click(xpaths.systemButton);
    Ctrl.doSleep(1000)
    session.click(xpaths.settingsButton);
    Ctrl.doSleep(1000)
    session.click(xpaths.editPen);
    Ctrl.doSleep(1000)
    session.click(xpaths.optionButton);
    Ctrl.doSleep(1000)
    session.click(xpaths.reviewSettingsButton);
    Ctrl.doSleep(1000)
    sync({request: Event("End(navigateToReviewsButton)")});
}

function disable_comments_guests(session) {
    sync({request: Event("Start(disable_comments_guests)")});
    session.click(xpaths.slideButton);
    Ctrl.doSleep(1000)
    sync({request: Event("End(disable_comments_guests)")});
}

function saveChanges(session) {
    sync({request: B});
    //session.click(xpaths.saveButton);
    //Ctrl.doSleep(10000)
    sync({request: Event("End(saveChanges)")});
}

