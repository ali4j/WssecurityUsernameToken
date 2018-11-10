package net.ali4j.cxf.wssecurityusernametoken;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;

import javax.xml.ws.Endpoint;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Server {
    protected Server() {
        System.out.println("Starting Server");

        Object implementor = new GreeterImpl();
        String address = "http://localhost:9000/SoapContext/GreeterPort";
        EndpointImpl impl = (EndpointImpl) Endpoint.publish(address, implementor);

        Map<String, Object> inProps = new HashMap<>();
        inProps.put("action", "UsernameToken");
        inProps.put("passwordType", "PasswordText");
        inProps.put("passwordCallbackClass", "net.ali4j.cxf.wssecurityusernametoken.ServerUTPasswordCallback");

        impl.getInInterceptors().add(new WSS4JInInterceptor(inProps));
    }

    public static void main(String args[]) throws Exception {

        SpringBusFactory bf = new SpringBusFactory();
        URL busFile = Server.class.getResource("/wssec.xml");
        Bus bus = bf.createBus(busFile.toString());

        BusFactory.setDefaultBus(bus);

        new Server();
        System.out.println("Server ready...");

        Thread.sleep(15 * 60 * 1000);

        bus.shutdown(true);
        System.out.println("Server exiting");
        System.exit(0);
    }


}
