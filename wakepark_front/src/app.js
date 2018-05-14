export class App {
  constructor() {
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Wakepark";
    config.map([
      {route: ['login', 'logout'], name: 'login', moduleId: 'login/login', title: "Logi sisse", nav: true},
      {route: 'contacts', name: 'contacts', moduleId: 'contacts/contacts', title: 'Kontaktid', nav: true},
      {route: 'info', name: 'info', moduleId: 'info/info', title: 'Hinnakiri', nav: true},
      {route: ['', 'index'], name: 'home', moduleId: 'home/index', title: "Avaleht", nav: true}
      ]
    )
  }
}



