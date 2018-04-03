export class App {
  constructor() {
    this.message = 'Hello Dog hello!';
  }
  loginPage() {
    var loginPage = "<div class=\"login-banner\">\n" +
      "        <p class=\"login-text\">LOGI SISSE <br> JA BRONEERI SÕIDUAEG</p>\n" +
      "    </div>\n" +
      "    <div class=\"login-container\">\n" +
      "        <form class=\"login-form\">\n" +
      "            <div style=\"padding-top: 10px;\">\n" +
      "                <input type=\"text\" id=\"username\" name=\"username\" class=\"form-input\" placeholder=\"Kasutajanimi\"  required/>\n" +
      "            </div>                                                                                                            \n" +
      "            <div class=\"form-group\">\n" +
      "                <input type=\"password\" id=\"password\" name=\"password\" class=\"form-input\" placeholder=\"Parool\"  required/>\n" +
      "            </div>                                                    \n" +
      "            <button type=\"submit\" class=\"login-button\">Logi sisse</button>                                                \n" +
      "        </form>\n" +
      "    </div>";
    document.getElementById("content").innerHTML = loginPage;
  }

  contactPage() {
    var contactPage = "<div class=\"heading\" style=\"width: 800px;\">\n" +
      "        <h2 class=\"contact\">KONTAKT</h2>\n" +
      "    </div>\n" +
      "    <div class=\"contact-container\">\n" +
      "        <div class=\"contact-text-container\">\n" +
      "            <p class=\"contact-text\">PARK ON AVATUD IGA PÄEV 12.00-22.00!<br> OOTAME KÕIKI SÕITMA</p>\n" +
      "            <p class=\"contact-text\" style=\"margin-bottom: 150px;\">Asume Trapi teel, Männikul, Valdeku karjääri ääres</p>\n" +
      "            <p class=\"contact-text\">Tel: +372 56652241</p>\n" +
      "        </div>\n" +
      "        <div class=\"map\">\n" +
      "            <iframe\n" +
      "              width=\"350\"\n" +
      "              height=\"310\"\n" +
      "              frameborder=\"0\" style=\"border:0\"\n" +
      "              src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyAomN7LMEYxnAZxG1MwETDjyRaImlFmGLE\n" +
      "                &q=Männiku+wakepark\" allowfullscreen>\n" +
      "            </iframe>\n" +
      "        </div>\n" +
      "    </div>";
    document.getElementById("content").innerHTML = contactPage;

  }

  registerPage() {
    var registerPage = "    <div class=\"register-banner\">\n" +
      "        <div class=\"register-text\">REGISTREERI <br> KASUTAJA </div>\n" +
      "    </div>\n" +
      "    <div class=\"register-container\">\n" +
      "        <form class=\"register-form\">\n" +
      "            <div style=\"padding-top: 10px;\">\n" +
      "                <input type=\"text\" id=\"firstname\" name=\"firstname\" class=\"form-input\" placeholder=\"Eesnimi\"  required/>\n" +
      "            </div>                                                                                                            \n" +
      "            <div class=\"form-group\">\n" +
      "                <input type=\"text\" id=\"surname\" name=\"surname\" class=\"form-input\" placeholder=\"Perekonnanimi\"  required/>\n" +
      "            </div>\n" +
      "            <div class=\"form-group\">\n" +
      "                <input type=\"text\" id=\"username\" name=\"username\" class=\"form-input\" placeholder=\"Kasutajanimi\"  required/>\n" +
      "            </div>\n" +
      "            <div class=\"form-group\">\n" +
      "                <input type=\"text\" id=\"password_reg\" name=\"password_reg\" class=\"form-input\" placeholder=\"Parool\"  required/>\n" +
      "            </div>\n" +
      "            <div class=\"form-group\">\n" +
      "                <input type=\"email\" id=\"email\" name=\"email\" class=\"form-input\" placeholder=\"E-mail\"  required/>\n" +
      "            </div>\n" +
      "            <div class=\"form-group\">\n" +
      "                <input type=\"number\" id=\"phonenumber\" name=\"phonenumber\" class=\"form-input\" placeholder=\"Telefoninumber\"  required/>\n" +
      "            </div>\n" +
      "            <button type=\"submit\" class=\"submit-button\">Registreeri</button>                                                \n" +
      "        </form>\n" +
      "    </div>";
    document.getElementById("content").innerHTML = registerPage;
  }

}



