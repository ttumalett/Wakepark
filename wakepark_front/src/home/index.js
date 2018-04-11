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
