package net.ali4j.cxf.wssecurityusernametoken.client;

import net.ali4j.cxf.wssecurityusernametoken.Greeter;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9000/SoapContext/GreeterPort?wsdl");
        QName qname = new QName("http://wssecurityusernametoken.cxf.ali4j.net/greeter", "GreeterService");
        Service service = Service.create(url, qname);
        Greeter greeter = service.getPort(Greeter.class);

        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(greeter);
        Endpoint endpoint = client.getEndpoint();

        Map props = new HashMap();
        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        props.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientUTPasswordCallback.class.getName());
        props.put(WSHandlerConstants.USER, "Alice");

        WSS4JOutInterceptor wwsout = new WSS4JOutInterceptor(props);
        endpoint.getOutInterceptors().add(wwsout);
        System.out.println(greeter.greetMe("ehsan"));
    }


}
