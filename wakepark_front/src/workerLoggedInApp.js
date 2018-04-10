import {inject, Aurelia} from 'aurelia-framework';

@inject(Aurelia)
export class workerLoggedInApp {
  constructor(aurelia) {
    this.aurelia = aurelia;
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
        {route: ['', 'login', 'userHome'], name: 'userHome', moduleId: 'userHome/userHome', title: 'Broneeri aeg', nav: true},
        {route: 'register', name: 'register', moduleId: 'register/register', title: 'Registreeri', nav: true},
        {route: 'logout', redirect: 'register'}
      ]
    )
  }

  logout() {
    this.router.navigate("/logout");
    this.aurelia.setRoot("app");
  }
}
