import environment from './environment';

export function configure(aurelia) {
  aurelia.use
    .standardConfiguration()
    .feature('resources');

  if (environment.debug) {
    aurelia.use.developmentLogging();
  }

  if (environment.testing) {
    aurelia.use.plugin('aurelia-testing');
  }
  aurelia.start().then(() => {
    let root;
    if (sessionStorage.getItem("currentUser") !== null) {
      if (sessionStorage.getItem("currentUserStatus") === 1) {
        root = 'userLoggedInApp'
      } else {
        root = 'workerLoggedInApp'
      }
      aurelia.setRoot();
    }
    aurelia.setRoot('app');
  });
}
