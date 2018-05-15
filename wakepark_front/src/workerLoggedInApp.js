import {inject, Aurelia} from 'aurelia-framework';


@inject(Aurelia)
export class workerLoggedInApp {
  constructor(aurelia) {
    this.aurelia = aurelia;
    this.name = sessionStorage.getItem("currentUser");

  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
        {route: ['', 'login', 'employeeHome'], name: 'employeeHome', moduleId: 'employeeHome/employeeHome', title: 'Broneeri sõiduaeg', nav: true},
        {route: 'register', name: 'register', moduleId: 'register/register', title: 'Registreeri kasutaja', nav: true},
        {route: 'employeeUpdate', name: 'employeeUpdate', moduleId: 'employeeUpdate/employeeUpdate', title: 'Uuenda kasutajat', nav: true},
        {route: 'logout', redirect: 'employeeHome'}
      ]
    )
  }

  activate() {
    this.name = sessionStorage.getItem("currentUser");
  }

  logout() {
    sessionStorage.setItem("currentUser", null);
    sessionStorage.setItem("currentUserStatus", null);
    this.router.navigate("/logout");
    this.aurelia.setRoot("app");
  }
}
