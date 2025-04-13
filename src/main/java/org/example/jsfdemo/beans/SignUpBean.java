package org.example.jsfdemo.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.annotation.MultipartConfig;
import org.example.jsfdemo.entities.Attachment;
import org.example.jsfdemo.entities.User;
import org.example.jsfdemo.services.UserService;
import org.primefaces.model.file.UploadedFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value= "signUpBean")
@ViewScoped
@MultipartConfig
public class SignUpBean implements Serializable {
    @EJB
    private UserService userService;

    private User user;
    private String confirmPassword;
    private UploadedFile file;

    public SignUpBean() {
        user = new User();
        confirmPassword = "";
    }
    public void exit(String url) {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }
    }
    public void signIn() {
        exit("../views/signIn.xhtml");
    }

    private void uploadFile(UploadedFile file, String filePath) {
        try (InputStream input = file.getInputStream()) {
            // Define where to save the file (change this path as needed)
            File targetFile = new File(filePath);

            Files.copy(input, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException e) {
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    public void signUp() {
        if (user == null || user.getUsername() == null || user.getPassword() == null) return;

        // check if user exist
        if(userService.getUser(user.getUsername()) != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "username is already in use!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // check if passwords match
        if(!user.getPassword().equals(confirmPassword)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "password doesn't match!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        // calculate age
        int age=  (int) ChronoUnit.YEARS.between(user.getBirthdate(), LocalDate.now());
        if(age < 18){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must be at least 18 years old to create an account!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        user.setAge(age);

        // upload file
        if(file != null) {
            String fileName= UUID.randomUUID().toString() + "." + getFileExtension(file.getFileName());
            String filePath= "D:\\IdeaProjects\\JSFDemo\\src\\main\\webapp\\imgs\\" + fileName;
            uploadFile(file, filePath);

            Attachment attach = new Attachment();
            attach.setFileName(fileName);
            attach.setFullPath(filePath);
            user.setAttach(attach);
        }

        userService.addUser(user);
        exit("../views/signIn.xhtml");
    }














    // setter and getter
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
