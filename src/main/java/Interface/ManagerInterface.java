package Interface;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public interface ManagerInterface {
    public abstract Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException;
}
