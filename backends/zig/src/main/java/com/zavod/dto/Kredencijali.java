package com.zavod.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kredencijali")
public class Kredencijali {

    private String email;
    private String lozinka;

    public Kredencijali() {
    }

    public Kredencijali(String email, String password) {
        this.email = email;
        this.lozinka = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
