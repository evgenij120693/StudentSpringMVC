package ru.svetozarov.models.pojo;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class Lection {
    private int id;
    private String name;
    private String text;
    private String subject;
    private int idGroup;
    private String date;

    public Lection() {
    }

    public Lection(int id, String name, String text, String subject, int idGroup, String date) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.subject = subject;
        this.idGroup = idGroup;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
