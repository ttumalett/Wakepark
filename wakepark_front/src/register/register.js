import {HttpClient, json} from 'aurelia-fetch-client'
import config from 'config';
import {inject} from 'aurelia-framework';

@inject(config)
export class register {
  constructor() {
    this.message = "";
    this.baseUrl = config.baseUrl;
  }

  userData = {};

  registerUser() {
    let client = new HttpClient();
    client.fetch(this.baseUrl + '/register', {
      'method': "POST",
      'body': json(this.userData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.message = "Kasutaja loodud!";
        }
      });
    document.getElementById("registerForm").reset();
  }
}
