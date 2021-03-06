package com.sept.rest.webservices.restfulwebservices.model;

import javax.persistence.*;


@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String phonenumber;
    @Column
    private String aboutme;
    @Column
    private String avatar;
    @Column
    private String background;

    public Profile(long l, String test, String test1, String test2, String s, String s1, String aboutMe_test, String link, String link1) {
    }

    public Profile() {

    }


    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    //oneToOne Relationship with UserID
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private DAOUser user;


    public void setID(Long id) {
    	this.id = id;
    }

    public long getID() {
    	return id;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    public String getUsername() {
    	return username;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhonenumber() {
    	return phonenumber;
    }
    
    public void setPhonenumber(String phonenumber) {
    	this.phonenumber = phonenumber;
    }
    
    public String getAboutme() {
    	return aboutme;
    }
    
    public void setAboutme(String aboutme) {
    	this.aboutme = aboutme;
    }
     public void setAvatar(String avatar){
        this.avatar = avatar;
     }
     public String getAvatar(){
        return avatar;
     }

    @Override
    public String toString() {
        return String.format("username: %s, avatar: %s", username, avatar);
    }
}