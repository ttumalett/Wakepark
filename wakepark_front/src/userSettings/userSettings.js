import {HttpClient, json} from "aurelia-fetch-client";

export class userSettings {
  constructor() {
    this.userDataMessage = "";
    this.passwordMessage = "";
    this.username = sessionStorage.getItem('currentUser');
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNr = "";
    this.ridesLeft = "";
  }

  userData = {'username' : sessionStorage.getItem('currentUser')};
  changeableData = {'username' : sessionStorage.getItem('currentUser')};
  changeablePassword = {'username' : sessionStorage.getItem('currentUser')};

  changeUserData() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/changeUserData', {
      'method': "POST",
      'body': json(this.changeableData)
    })
      .then(response => response.json())
      .then(data => {
          if (data.response === "successful") {
            this.userDataMessage = "Andmed uuendatud!";
          } else {
            this.userDataMessage = "Proovi uuesti!"
          }
      });
  }

  changeUserPassword() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/changeUserPassword', {
      'method': "POST",
      'body': json(this.changeablePassword)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.passwordMessage = "Parool uuendatud!";
        } else {
          this.passwordMessage = "Proovi uuesti!";
        }
      });
    document.getElementById("passwordChangeForm").reset();
  }

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
