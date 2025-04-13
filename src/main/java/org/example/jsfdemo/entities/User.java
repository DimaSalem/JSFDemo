package org.example.jsfdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Size(max = 255)
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;

    @Size(max = 255)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 255)
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Size(max = 255)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private Integer age;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ATTACH_ID", referencedColumnName = "ATTACH_ID")
    private Attachment attach;


    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;

    @Column(name = "STATUS")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public Attachment getAttach() {
        return attach;
    }

    public void setAttach(Attachment attach) {
        this.attach = attach;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}