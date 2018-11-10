package net.ali4j.cxf.wssecurityusernametoken;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;

public class ServerUTPasswordCallback implements CallbackHandler {
    private Map<String, String> passwords =
            new HashMap<String, String>();

    public ServerUTPasswordCallback() {
        passwords.put("Alice", "ecilA");
        passwords.put("abcd", "dcba");
    }

    public void handle(Callback[] callbacks) {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];

            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
    }

}
