import {HttpClient, json} from 'aurelia-fetch-client'
import config from 'config';
import {inject} from 'aurelia-framework';

@inject(config)
export class Home {

  reservationList = [];
  estrellaReservations = [];
  redBullReservations = [];
  liveFearlessReservations = [];

  constructor() {
    this.baseUrl = config.baseUrl;
  }

  activate() {
    let client = new HttpClient();
    client.fetch(this.baseUrl + '/reservations')
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
