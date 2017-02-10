import org.apache.log4j.DailyRollingFileAppender

grails.logging.jul.usebridge = true
// put your url to make tpv redirection work (https://<your-ip>/es.myaurea.backend)
grails.serverURL = "http://dh-frontend.cloudapp.net:18888/es.myaurea.backend"

tpv {
    sabadell {
        merchantCode = '336030101'
        currency = '978'
        merchantURL = 'http://dh-frontend.cloudapp.net:18888/es.myaurea.backend/api/tpvGatewayresp'
        terminal = '1'
        secretKey = 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'
        tpvUrl = 'https://sis-t.redsys.es:25443/sis/realizarPago'
        orderSufix = new Date().format("'D'HHmmdd")
    }
}

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "devops@ubiqware.net"
        password = "favaraca_"
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.socketFactory.port":"465",
                 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback":"false"]
    }
}

ingenico.url = 'http://212.170.103.137/ServicioPrepagoFDI/ServicioPrepagoFDI'
ingenico {
    com400 = '9999299'
    serial = 'WEB9999299'
    operationMode = 'W'
    binId = 10201618
    cardType = 'CHIP'
    captureType = 'AUT'
    chipData = 'NFC#00'
    chipType = -1
}
ingenicoDAO.url = 'http://212.170.103.137/ServicePrepagoDAO/ServicePrepagoDAO'

cxf {
    client {
        servicioPrepagoClient {
            //used in wsdl2java
            wsdl = "ServicioPrepago.wsdl" //only used for wsdl2java script target
            namespace = "es.myaurea.backend.ingenico"

            clientInterface = es.myaurea.backend.ingenico.ServicioPrepagoFDI
            serviceEndpointAddress = "${ingenico.url}"
        }

        servicePrepagoDAOClient {
            //used in wsdl2java
            wsdl = "ServicePrepagoDAO.wsdl" //only used for wsdl2java script target
            namespace = "es.myaurea.backend.ingenicoDAO"

            clientInterface = es.myaurea.backend.ingenicoDAO.ServicePrepagoDAO
            serviceEndpointAddress = "${ingenicoDAO.url}"
        }
    }
}

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "devops@ubiqware.net"
        password = "favaraca_"
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.socketFactory.port":"465",
                 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback":"false"]
    }
}

// log4j configuration
def appName = "${appName}"
catalinaBase = System.properties.getProperty('catalina.base') ?: '.'
def logDirectory = "${catalinaBase}/logs"

log4j.main = {

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
       'org.codehaus.groovy.grails.web.pages',          // GSP
       'org.codehaus.groovy.grails.web.sitemesh',       // layouts
       'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
       'org.codehaus.groovy.grails.web.mapping',        // URL mapping
       'org.codehaus.groovy.grails.commons',            // core / classloading
       'org.codehaus.groovy.grails.plugins',            // plugins
       'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
       'org.springframework',
       'org.hibernate',
       'net.sf.ehcache.hibernate'

  info 'org.apache.cxf'

  trace 'grails.app.conf.BootStrap',
            'es.myaurea.backend.BaseController',
          'es.myaurea.backend.EntityController',
          'grails.app.controllers.es.myaurea.backend.BraceletController',
          'grails.app.controllers.es.myaurea.backend.SignUpController',
          'grails.app.controllers.es.myaurea.backend.TpvGatewayController',
          'grails.app.controllers.es.myaurea.backend.TpvGatewayResponseController',
          'grails.app.services.es.myaurea.backend',
          'es.myaurea.backend.interpreters',
          'es.myaurea.backend.operations',
          'es.myaurea.backend.operations.tpvs.sabadell'
                      /* root logger config */
}

