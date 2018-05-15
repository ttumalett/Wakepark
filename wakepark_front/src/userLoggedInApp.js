import {inject, Aurelia} from 'aurelia-framework';

@inject(Aurelia)
export class userLoggedInApp {
  constructor(aurelia) {
    this.aurelia = aurelia;
    this.name = sessionStorage.getItem("currentUser");
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
        {route: 'userSettings', name: 'userSettings', moduleId: 'userSettings/userSettings', title: 'Profiil', nav: true},
        {route: 'contacts', name: 'contacts', moduleId: 'contacts/contacts', title: 'Kontaktid', nav: true},
        {route: 'info', name: 'info', moduleId: 'info/info', title: 'Info', nav: true},
        {route: ['', 'login', 'userHome', 'logout'], name: 'userHome', moduleId: 'userHome/userHome', title: 'Broneeri', nav: true},
        {route: 'logout', redirect: 'userHome'}
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
