import environment from './environment';

export function configure(aurelia) {
  aurelia.use
    .standardConfiguration()
    .feature('resources')
    .globalResources(["userLoggedInApp", "workerLoggedInApp"]);

  if (environment.debug) {
    aurelia.use.developmentLogging();
  }

  if (environment.testing) {
    aurelia.use.plugin('aurelia-testing');
  }
  aurelia.start().then(() => {
    console.log("uus refresh");
    console.log(sessionStorage.getItem("currentUser"));
    let root;
    if (sessionStorage.getItem("currentUser") != null && sessionStorage.getItem("currentUser") !== "null") {
      if (sessionStorage.getItem("currentUserStatus") === "1") {
        console.log("see on tavakasutaja");
        root = 'userLoggedInApp';
      } else {
        console.log("see on töötaja");
        root = 'workerLoggedInApp';
      }
      aurelia.setRoot(root);
    }
    aurelia.setRoot('app');
  });
}
