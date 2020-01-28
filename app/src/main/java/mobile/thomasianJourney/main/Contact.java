package mobile.thomasianJourney.main;

public class Contact {

    String title;
    String description;
    String date;
    String id;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Contact() {

    }
    public Contact(String title, String description, String date, String id, String status) {

        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.status = status;
    }
    public Contact(String title, String description, String date, String id) {

        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
    }
    public Contact(String title, String description, String date) {

        this.title = title;
        this.description = description;
        this.date = date;

    }
    public Contact(String id){
        this.id = id;
    }
}
