package news;

import java.time.LocalDateTime;

public class Event {

    private String ts;
    private String ss;
    private Object data;

    public Event(String ss, Object data) {
        this.ts = LocalDateTime.now().toString();
        this.ss = ss;
        this.data = data;
    }

    public String getTs() {
        return ts;
    }

    public String getSs() {
        return ss;
    }

    public Object getData() {
        return data;
    }
}