import {HttpClient, json} from "aurelia-fetch-client";

export class userSettings {
  constructor() {
    this.username = sessionStorage.getItem('currentUser');
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNr = "";
    this.ridesLeft = "";
  }

  userData = {'username' : sessionStorage.getItem('currentUser')};

  activate() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/getUserData', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.ridesLeft = data.ridesLeft;
          this.email = data.email;
          this.phoneNr = data.phoneNr;
        }
      });
  }
}
