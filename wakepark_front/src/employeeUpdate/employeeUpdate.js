import {HttpClient, json} from "aurelia-fetch-client";
import config from 'config';
import {inject} from 'aurelia-framework';

@inject(config)
export class employeeUpdate {
  userData = {};
  changeRide = {};
  constructor() {
    this.baseUrl = config.baseUrl;
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
    client.fetch(this.baseUrl + '/changeUserRides', {
      'method': "POST",
      'body': json(this.changeRide)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          console.log(data);
          this.ridesLeft = this.changeRide.rides;
        }
      });
  }
  searchUser() {
    let client = new HttpClient();
    client.fetch(this.baseUrl + '/getUserData', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
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
