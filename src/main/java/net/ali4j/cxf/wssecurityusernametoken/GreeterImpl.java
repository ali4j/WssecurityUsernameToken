package net.ali4j.cxf.wssecurityusernametoken;

import java.util.logging.Logger;


@javax.jws.WebService(serviceName = "GreeterService",
        portName = "GreeterPort",
        endpointInterface = "net.ali4j.cxf.wssecurityusernametoken.Greeter",
        targetNamespace = "http://wssecurityusernametoken.cxf.ali4j.net/greeter")
public class GreeterImpl implements Greeter{
    private static final Logger LOG =
            Logger.getLogger(GreeterImpl.class.getPackage().getName());

    public String greetMe(String me) {
        LOG.info("Executing operation greetMe");
        System.out.println("Executing operation greetMe");
        System.out.println("Message received: " + me + "\n");
        return "Hello " + me;
    }

    public void greetMeOneWay(String me) {
        LOG.info("Executing operation greetMeOneWay");
        System.out.println("Executing operation greetMeOneWay\n");
        System.out.println("Hello there " + me);
    }

    public String sayHi() {
        LOG.info("Executing operation sayHi");
        System.out.println("Executing operation sayHi\n");
        return "Bonjour";
    }
}
