import {HttpClient, json} from 'aurelia-fetch-client';

export class userHome {

  reservationList = [];
  estrellaReservations = [];
  redBullReservations = [];
  liveFearlessReservations = [];

  constructor() {
    this.name = sessionStorage.getItem("currentUser");
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
  reservationData = {"action" : "addReservation", "client" : "2"};

  reserveRide() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.reservationData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
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
        for (let i = 0; i < this.reservationList.length; i++) {
          let reservation = this.reservationList[i];
          if (reservation.track.name === 'Estrella') {
            this.estrellaReservations.push(reservation);
          } else if (reservation.track.name === 'Red Bull') {
            this.redBullReservations.push(reservation);
          } else if (reservation.track.name === 'Live Fearless') {
            this.liveFearlessReservations.push(reservation);
          }
        }
        console.log(this.reservationList);
      });
  }
}
