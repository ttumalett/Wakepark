import {HttpClient, json} from 'aurelia-fetch-client'

export class login {
  constructor() {
  }

  data = {};

  login() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.data)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.username + " " + data.password)
      });

  }
}
