import {HttpClient, json} from 'aurelia-fetch-client';
import config from 'config';
import {inject} from 'aurelia-framework';

@inject(config)
export class userHome {

  reservationList = [];
  estrellaReservations = [];
  redBullReservations = [];
  liveFearlessReservations = [];

  constructor() {
    this.name = sessionStorage.getItem("currentUser");
    this.message = "";
    this.baseUrl = config.baseUrl;
  }

  tracks = [
    {name : 'Estrella'},
    {name : 'Red Bull'},
    {name : 'Live Fearless'}
  ];
  times = [];
  reservationData = {"action" : "addReservationClient", "client" : sessionStorage.getItem("currentUser")};

  reserveRide() {
    let client = new HttpClient();
    client.fetch(this.baseUrl + '/addReservation', {
      'method': "POST",
      'body': json(this.reservationData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.response === "successful") {
          this.message = "Oled ajale registreeritud!";
          window.location.reload();
        } else {
          this.message = "Ajale registreerumine ebaõnnestus!";
        }
      });
  }

  findNextQuarter(minutes) {
    if (minutes < 15) {
      return 15;
    } else if (minutes < 30) {
      return 30;
    } else if (minutes < 45) {
      return 45;
    } else {
      return 60;
    }
  }

  setMinutesAndHours(hour, startMinutes) {
    for (let minutes = startMinutes; minutes <= 60; minutes += 15) {
      if (minutes !== 60) {
        this.times.push(hour + ":" + minutes);
      } else if (minutes === 60 && hour !== 21) {
        this.times.push((hour + 1) + ":" + "00");
      }
    }
  }

  setTimeOptions() {
    let currentTime = new Date();
    if (currentTime.getHours() >= 22) {
      this.message = "Tänaseks on radadele registreerimine lõppenud!";
    }
    let startOptionHour = (currentTime.getHours() < 12) ? 11 : currentTime.getHours();
    let startOptionMinutes = (currentTime.getHours() < 12) ? 60 : this.findNextQuarter(currentTime.getMinutes());
    for (let hour = startOptionHour; hour <= 21; hour++) {
      if (hour === startOptionHour) {
        this.setMinutesAndHours(hour, startOptionMinutes);
      } else {
        this.setMinutesAndHours(hour, 15);
      }
    }
  }

  activate() {
    this.setTimeOptions();
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
