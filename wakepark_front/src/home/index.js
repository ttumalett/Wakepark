import {HttpClient} from 'aurelia-fetch-client'

export class Home {

  reservationList = [];
  estrellaReservations = [];
  redBullReservations = [];
  liveFearlessReservations = [];

  constructor() {
  }

  activate() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/reservations')
      .then(response => response.json())
      .then(reservations => {
        this.reservationList = reservations.sort(function(a, b){
          console.log(a.reservationStart.dayOfYear + " "
            + a.reservationStart.hour + ":"  + a.reservationStart.minute.toString().padStart(2, "0")
          + " " + a.reservationEnd.hour + ":" + a.reservationEnd.minute.toString().padStart(2, "0"));
          return new Date(a.reservationStart.hour + ":"  + a.reservationStart.minute)
            - new Date(b.reservationStart.hour + ":"  + b.reservationStart.minute);
        });
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
