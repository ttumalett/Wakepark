import {HttpClient, json} from 'aurelia-fetch-client';
import {inject, Aurelia} from 'aurelia-framework';

@inject(Aurelia)
export class login {
  constructor(aurelia) {
    this.message = "";
    this.aurelia = aurelia;
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
        this.loggingin(data)
      });
    document.getElementById("form1").reset();
  }

  loggingin(data) {
    if (data.response == "successful") {
      console.log(data.status);
      if (data.status == "0") {
        console.log(data.status);
        console.log("töötaja");
        this.aurelia.setRoot('workerLoggedInApp');
      } else {
        console.log("tava");
        this.aurelia.setRoot('userLoggedInApp');
      }
    } else {
      this.message = "Proovi uuesti!";
    }
  }
}
