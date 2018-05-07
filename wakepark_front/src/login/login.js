import {HttpClient, json} from 'aurelia-fetch-client';
import {inject, Aurelia} from 'aurelia-framework';

@inject(Aurelia)
export class login {
  constructor(aurelia) {
    this.message = "";
    this.aurelia = aurelia;
  }

  userData = {};

  login() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/login', {
      'method': "POST",
      'body': json(this.userData)
      })
      .then(response => response.json())
      .then(data => {
        this.selectView(data)
      });
    document.getElementById("form1").reset();
  }

  selectView(data) {
    if (data.response === "successful") {
      if (data.status === 0) {
        this.aurelia.setRoot('workerLoggedInApp');
      } else {
        this.aurelia.setRoot('userLoggedInApp');
      }
    } else {
      this.message = "Proovi uuesti!";
    }
  }
}
