import {inject, Aurelia} from 'aurelia-framework';


@inject(Aurelia)
export class workerLoggedInApp {
  constructor(aurelia) {
    console.log("jou");
    this.aurelia = aurelia;
  }

  configureRouter(config, router) {
    console.log("teeb");
    this.router = router;
    config.title = "Wakepark";
    config.map([
        {route: ['', 'login', 'employeeHome'], name: 'employeeHome', moduleId: 'employeeHome/employeeHome', title: 'Broneeri sõiduaeg', nav: true},
        {route: 'register', name: 'register', moduleId: 'register/register', title: 'Registreeri kasutaja', nav: true},
        {route: 'employeeUpdate', name: 'employeeUpdate', moduleId: 'employeeUpdate/employeeUpdate', title: 'Uuenda kasutajat', nav: true},
        {route: 'logout', redirect: 'register'}
      ]
    )
  }
  logout() {
    console.log("töötab");
    sessionStorage.setItem("currentUser", null);
    sessionStorage.setItem("currentUserStatus", null);
    this.router.navigate("/logout");
    this.aurelia.setRoot("app");
  }
}
