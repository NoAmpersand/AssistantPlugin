package Services;

import com.google.api.client.util.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

public class CalendarService extends Service {
    public boolean firstDateIsAfterSecondDate(String date1, String date2) {
        String[] date1Split = date1.split("T");
        String[] yearMonthDaySplit1 = date1Split[0].split("-");
        int year1 = Integer.parseInt(yearMonthDaySplit1[0]);
        int month1 = Integer.parseInt(yearMonthDaySplit1[1]);
        int day1 = Integer.parseInt(yearMonthDaySplit1[2]);
        String[] date1SplitForTime = date1Split[1].split("\\.");
        String[] hourMinutesSecondSplit1 = date1SplitForTime[0].split(":");
        int hour1 = Integer.parseInt(hourMinutesSecondSplit1[0]);
        int minutes1 = Integer.parseInt(hourMinutesSecondSplit1[1]);
        int second1 = Integer.parseInt(hourMinutesSecondSplit1[2]);

        String[] date2Split = date2.split("T");
        String[] yearMonthDaySplit2 = date2Split[0].split("-");
        int year2 = Integer.parseInt(yearMonthDaySplit2[0]);
        int month2 = Integer.parseInt(yearMonthDaySplit2[1]);
        int day2 = Integer.parseInt(yearMonthDaySplit2[2]);
        String[] date2SplitForTime = date2Split[1].split("\\.");
        String[] hourMinutesSecondSplit2 = date2SplitForTime[0].split(":");
        int hour2 = Integer.parseInt(hourMinutesSecondSplit2[0]);
        int minutes2 = Integer.parseInt(hourMinutesSecondSplit2[1]);
        int second2 = Integer.parseInt(hourMinutesSecondSplit2[2]);

        if (year1 > year2) {
            return true;
        } else if (year1 < year2) {
            return false;
        } else {
            if (month1 > month2) {
                return true;
            } else if (month1 < month2) {
                return false;
            } else {
                if (day1 > day2) {
                    return true;
                } else if (day1 < day2) {
                    return false;
                } else {
                    if (hour1 > hour2) {
                        return true;
                    } else if (hour1 < hour2) {
                        return false;
                    } else {
                        if (minutes1 > minutes2) {
                            return true;
                        } else if (minutes1 < minutes2) {
                            return false;
                        } else {
                            if (second1 > second2) {
                                return true;
                            } else {
                                return false;
                            }

                        }
                    }
                }
            }
        }
    }

    public String getLimitForDailyEvent() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.DATE, 1);
        DateTime dateLimit = new DateTime(calendar.getTime());

        return dateLimit.toString();
    }

    public String getLimitForWeeklyEvent() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.DATE, 7);
        DateTime dateLimit = new DateTime(calendar.getTime());

        return dateLimit.toString();
    }

    public DateTime getDate(String day, int hour, int minute, int second) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        switch (day) {
            case "lundi":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;
            case "mardi":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;
            case "mercredi":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;
            case "jeudi":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;
            case "vendredi":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;
            case "samedi":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;
            case "dimanche":
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    calendar.add(Calendar.DATE, 1);
                }
                break;

        }

        setDateHour(calendar, hour);
        setDateMinute(calendar, minute);
        setDateSecond(calendar, second);

        return new DateTime(calendar.getTime());


    }

    public void setDateHour(Calendar calendar, int hour) {

        while (calendar.get(Calendar.HOUR_OF_DAY) != hour) {
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
        calendar.add(Calendar.HOUR_OF_DAY, -1);
    }

    public void setDateMinute(Calendar calendar, int minute) {
        while (calendar.get(Calendar.MINUTE) != minute) {
            calendar.add(Calendar.MINUTE, 1);
        }
        calendar.add(Calendar.MINUTE, -1);
    }

    public void setDateSecond(Calendar calendar, int second) {
        while (calendar.get(Calendar.SECOND) != second) {
            calendar.add(Calendar.SECOND, 1);
        }
    }

}
