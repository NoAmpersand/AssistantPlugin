package View;

import java.util.Scanner;

public class AssistantInput extends Assistant{

    Scanner scn = new Scanner(System.in);
    private String request;

    public String askUserRequest(){
        String str = scn.nextLine();
        return str;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
