import {HttpClient, json} from "aurelia-fetch-client";

export class employeeUpdate {
  userData = {"action" : "getUserData"};
  changeRide = {"action" : "changeUserRides"};
  constructor() {
    this.userName = "";
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNr = "";
    this.ridesLeft = "";
  }
  changeUserRides() {
    this.changeRide.user = this.userName;
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.changeRide)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response == "successful") {
          console.log(data);
          this.ridesLeft = this.changeRide.rides;
        }
      });
  }
  searchUser() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response == "successful") {
          this.userName = this.userData.username;
          this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.ridesLeft = data.ridesLeft;
          this.email = data.email;
          this.phoneNr = data.phoneNr;
        }
      });
  }
}
