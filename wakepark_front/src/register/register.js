import {HttpClient, json} from 'aurelia-fetch-client'

export class register {
  constructor() {
    this.message = ""
  }

  userData = {"action" : "register"};

  registerUser() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response == "successful") {
          this.message = "Kasutaja loodud!";
        }
      });
    document.getElementById("registerForm").reset();
  }
}
