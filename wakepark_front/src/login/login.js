import {HttpClient, json} from 'aurelia-fetch-client'

export class login {
  constructor() {
  }

  userData = {"action" : "login"};

  login() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.userData)
      })
      .then(response => response.json())
      .then(data => {
        console.log(data.response)
      });
    document.getElementById("form1").reset();
  }
}
