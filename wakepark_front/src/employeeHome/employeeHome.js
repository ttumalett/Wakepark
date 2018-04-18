import {HttpClient, json} from "aurelia-fetch-client";

export class employeeHome {

  reservationList = [];
  estrellaReservations = [];
  redBullReservations = [];
  liveFearlessReservations = [];

  times = [
    {start : '12:00'}, {start : '12:15'}, {start : '12:30'}, {start : '12:45'}, {start : '13:00'}
  ];
  reservationEstrella = {"action" : "addReservationWorker"};
  reservationLiveFearless= {"action" : "addReservationWorker"};
  reservationRedBull = {"action" : "addReservationWorker"};

  constructor() {
  }
  reserveClientEstrella() {
    this.reservationEstrella.trackName = 'Estrella';
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.reservationEstrella)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.message = "Klient ajale registreeritud!";
        }
      });
  }
  reserveClientLiveFearless() {
    this.reservationLiveFearless.trackName = 'Live Fearless';
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.reservationLiveFearless)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.message = "Klient ajale registreeritud!";
        }
      });
  }
  reserveClientRedBull() {
    this.reservationRedBull.trackName = 'Red Bull';
    let client = new HttpClient();
    client.fetch('http://localhost:8080', {
      'method': "POST",
      'body': json(this.reservationRedBull)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.message = "Klient ajale registreeritud!";
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
