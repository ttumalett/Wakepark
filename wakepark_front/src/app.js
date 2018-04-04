export class App {
  constructor() {
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
      {route: ['', 'home'], name: 'home', moduleId: 'home/index', title: "Avaleht", nav: true},
      {route: 'login', name: 'login', moduleId: 'login/login', title: "Logi sisse", nav: true},
      {route: 'register', name: 'register', moduleId: 'register/register', title: 'Registreeri', nav: true},
      {route: 'contacts', name: 'contacts', moduleId: 'contacts/contacts', title: 'Kontaktid', nav: true},
      {route: 'info', name: 'info', moduleId: 'info/info', title: 'Info', nav: true}
      ]
    )
  }
}



