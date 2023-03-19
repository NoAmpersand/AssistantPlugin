package Settings;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;


public abstract class  Constants {
    public static final String APPLICATION_NAME = "assistant";
    /**
     * Global instance of the JSON factory.
     */
    public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    public static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    public static final String CREDENTIALS_FILE_PATH = "/client.json";



}
