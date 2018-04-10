import {inject, Aurelia} from 'aurelia-framework';

@inject(Aurelia)
export class userLoggedInApp {
  constructor(aurelia) {
    this.aurelia = aurelia;
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
        {route: 'contacts', name: 'contacts', moduleId: 'contacts/contacts', title: 'Kontaktid', nav: true},
        {route: 'info', name: 'info', moduleId: 'info/info', title: 'Info', nav: true},
        {route: ['', 'login', 'userHome'], name: 'userHome', moduleId: 'userHome/userHome', title: 'Broneeri', nav: true},
        {route: 'logout', redirect: 'info'}
      ]
    )
  }

  logout() {
    this.router.navigate("/logout");
    this.aurelia.setRoot("app");
  }
}
