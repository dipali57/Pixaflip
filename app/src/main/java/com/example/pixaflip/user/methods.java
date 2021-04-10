package com.example.pixaflip.user;

public class methods {
    private int id;
    private static String From;
    private static String To;
    private static String Timestamp;

    public methods(int id, String From, String To, String Timestamp) {
        this.id = id;
        this.From = From;
        this.To = To;
        this.Timestamp = Timestamp;
    }
public methods()
{
    this.From = From;
    this.To = To;
    this.Timestamp = Timestamp;
}
    public methods(String From, String To, String Timestamp) {
        this.From = From;
        this.To = To;
        this.Timestamp = Timestamp;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getFrom() {
        return From;
    }

    public String setFrom(String from) {
        From = from;
        return from;
    }

    public static String getTo() {
        return To;
    }

    public String setTo(String to) {
        To = to;
        return to;
    }

    public static String getTimestamp() {
        return Timestamp;
    }

    public String setTimestamp(String timestamp) {
        Timestamp = timestamp;
        return timestamp;
    }
}
