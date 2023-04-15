package Object;

import com.google.api.client.util.DateTime;



public class EventModel {
    private String summary;
    private String description;
    private String location;
    private DateTime startDateTime;
    private DateTime endDateTime;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void printAllInfo() {
        if (this.summary != null) {
            System.out.println("Summary: " + this.summary);
        }
        if (this.description != null) {
            System.out.println("Description: " + this.description);
        }
        if (this.location != null) {
            System.out.println("Location: " + this.location);
        }
        if (this.startDateTime != null) {
            System.out.println("Start Date/Time: " + this.startDateTime);
        }
        if (this.endDateTime != null) {
            System.out.println("End Date/Time: " + this.endDateTime);
        }
    }
}
