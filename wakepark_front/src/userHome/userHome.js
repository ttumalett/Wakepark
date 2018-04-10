import {HttpClient, json} from 'aurelia-fetch-client';

export class userHome {

  reservationList = [];
  estrellaReservations = [];
  redBullReservations = [];
  liveFearlessReservations = [];

  constructor() {
    this.message = "";
  }

  tracks = [
    {name : 'Estrella'},
    {name : 'Red Bull'},
    {name : 'Live Fearless'}
  ];
  times = [
    {start : '12:00'}, {start : '12:15'}, {start : '12:30'}, {start : '12:45'}, {start : '13:00'}
  ];
  reservationData = {"action" : "addReservation", "client" : "1"};

  reserveRide() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.reservationData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response == "successful") {
          this.message = "Oled ajale registreeritud!";
        }
      });
  }

  activate() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/reservations')
      .then(response => response.json())
      .then(reservations => {
        this.reservationList = reservations;
        var e = 0;
        var r = 0;
        var l = 0;
        for (let i = 0; i < this.reservationList.length; i++) {
          var reservation = this.reservationList[i];
          if (reservation.track.name == 'Estrella') {
            this.estrellaReservations[e] = reservation;
            e++;
          } else if (reservation.track.name == 'Red Bull') {
            this.redBullReservations[r] = reservation;
            r++;
          } else if (reservation.track.name == 'Live Fearless') {
            this.liveFearlessReservations[l] = reservation;
            l++;
          }
        }
        console.log(this.reservationList);
      });
  }
}
