package ru.svetozarov.models.pojo;

import javax.persistence.*;

/**
 * Created by Шмыга on 23.02.2017.
 */
@Entity
@Table(name = "student", schema = "example", catalog = "student")
public class Student {
    public Student(int id, String name, String birth_date, String sex, int idGroup) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.sex = sex;
        this.idGroup = idGroup;
    }

    public Student() {
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;

    @Column
    private String birth_date;

    @Column
    private String sex;

    private int idGroup;

    @Column(name = "email")
    private String email;
}
