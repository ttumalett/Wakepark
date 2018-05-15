import {HttpClient, json} from 'aurelia-fetch-client';
import {inject, Aurelia} from 'aurelia-framework';
import config from 'config';

@inject(Aurelia, config)
export class login {
  constructor(aurelia) {
    this.message = "";
    this.aurelia = aurelia;
    this.baseUrl = config.baseUrl;
  }

  userData = {};

  login() {
    let client = new HttpClient();
    client.fetch(this.baseUrl + '/login', {
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
      sessionStorage.setItem("currentUser", data.username);
      sessionStorage.setItem("currentUserStatus", data.status);
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
