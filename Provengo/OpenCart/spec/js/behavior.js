/* @provengo summon selenium */
// @provengo summon constraints

// This bthread handles the process of a guest submitting a product review.
// It creates a Selenium session for the guest user, navigates to the reviews section,
// fills out the review form with the provided details, and submits the review.
bthread('Guest submits a review for a product', function(){
  let s = new SeleniumSession('Use_case_1')
  s.start(URLs.URL_guest)
  navigate_to_reviews(s);  // Scroll down and click the review section
  fill_review(s, strings.comment, strings.name, strings.rating);  // Fill in review details
  submit_review(s);  // Click the submit button and handle confirmation
})


// This bthread ensures that the admin disables guest comments after a guest submits a review.
// It waits until the guest completes their review before starting the admin workflow.
// The admin logs into the system, navigates to the review settings, disables guest comments, and saves changes.
bthread('Admin disables comments for guests', function(){
  let s = new SeleniumSession('Use_case_2')
  Constraints.block(B).until(A);
  s.start(URLs.URL_admin)
  loginAsAdmin(s, AdminDetails.username, AdminDetails.password)
  navigateToReviewsButton(s)
  disable_comments_guests(s)
  saveChanges(s)
})

