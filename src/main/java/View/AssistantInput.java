package View;

import java.util.Scanner;

public class AssistantInput extends Assistant{

    Scanner scn = new Scanner(System.in);

    public String askUserRequest(){
        String str = scn.nextLine();
        return str;
    }
}
