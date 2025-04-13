package org.example.jsfdemo.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value= "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
    private String currentPage = "../views/main.xhtml"; // Default page
    private void exit(String url) {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void goToAuthors() {
        currentPage = "../views/main.xhtml";
    }

    public void goToArticles() {
        currentPage = "../views/main.xhtml";
    }

    public void logout(){

        exit("../views/signIn.xhtml");
    }
}
