package Services;

import java.time.LocalDate;

public class ContactService extends Service {
    public int calculateAge(String birthday) {
        int age = 0;
        String[] split = birthday.split("/");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getDayOfMonth();
        int currentYear = currentDate.getYear();
        int currentDay = currentDate.getDayOfMonth();

        if (currentMonth < month) {
            age = currentYear - year - 1;
        } else if (month == currentMonth) {
            if (currentDay < day) {
                age = currentYear - year - 1;
            } else {
                age = currentYear - year;
            }
        } else {
            age = currentYear - year;
        }

        return age;


    }
}
