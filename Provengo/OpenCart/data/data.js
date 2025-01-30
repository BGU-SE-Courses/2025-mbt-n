
const xpaths= {
  reviewButton: "//div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]",
      nameField: "//div[2]/input[1]",
      reviewField: "//div[3]/textarea[1]",
      ratingField: (rating) => `//input[${rating}]`,
      submitButton: "//*[@id='button-review']",
      successMessage: "//div[contains(@class, 'alert-success')]",

      // Admin panel elements
      adminUserName:  "//div[1]/div[1]/input[1]",
      adminPassword: "//div[2]/div[1]/input[1]",
      adminConfirmLoginButton: "//button[1]",
      systemButton: "//div[2]/nav[1]/ul[1]/li[9]/a[1]",
      settingsButton: "//div[2]/nav[1]/ul[1]/li[9]/ul[1]/li[1]/a[1]",
      editPen: "//div[1]/table[1]/tbody[1]/tr[1]/td[4]/a[1]/i[1]",
      optionButton: "//div[2]/form[1]/ul[1]/li[4]/a[1]",
      reviewSettingsButton: "//div[1]/div[2]/h2[1]/button[1]",
      slideButton: "//div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/input[2]",
      saveButton: "//div[1]/div[1]/div[1]/div[1]/button[1]/i[1]"
}

const strings = {
  comment: "wow.wow.wow.WOW! what a great product :^))))))))",
  rating : "5",
  name : "matan"
}

const URLs ={
  URL_guest : "http://localhost/opencart/index.php?route=product/product&language=en-gb&product_id=43" ,
  URL_admin : "http://localhost/opencart/matan"
}

const AdminDetails={
  username: "matan",
  password: "123matan"
}

const scrolling={
    down: function(){window.scrollTo(0,2000); pvg.success('yes');},
}