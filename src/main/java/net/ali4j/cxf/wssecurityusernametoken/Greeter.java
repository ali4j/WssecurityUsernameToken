package net.ali4j.cxf.wssecurityusernametoken;

import javax.jws.WebService;

@WebService
public interface Greeter {
    String greetMe(String me);
    void greetMeOneWay(String me);
    String sayHi();
}
