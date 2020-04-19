package design.swira.aennyapp.pojo.aenny;

public class IntroData {
    String title;
    String body;

    public IntroData() {
    }

    public IntroData(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "IntroData{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
