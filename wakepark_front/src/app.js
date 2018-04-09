export class App {
  constructor() {
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
      {route: ['', 'home/index'], name: 'home', moduleId: 'home/index', title: "Avaleht", nav: true},
      {route: 'login', name: 'login', moduleId: 'login/login', title: "Logi sisse", nav: true},
      {route: ['contacts', 'logout'], name: 'contacts', moduleId: 'contacts/contacts', title: 'Kontaktid', nav: true},
      {route: 'info', name: 'info', moduleId: 'info/info', title: 'Info', nav: true}
      ]
    )
  }
}



