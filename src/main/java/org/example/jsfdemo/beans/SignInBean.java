package org.example.jsfdemo.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.jsfdemo.entities.Attachment;
import org.example.jsfdemo.entities.User;
import org.example.jsfdemo.services.UserService;
import org.primefaces.model.file.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value= "signInBean")
@SessionScoped
public class SignInBean implements Serializable {
    @EJB
    private UserService userService;
    private User user;
    private UploadedFile file;


    public SignInBean() {
        user = new  User();
    }



    public void exit(String url) {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }
    }

    public void signUp() {
        exit("../views/signUp.xhtml");
    }

    public void signIn(){
        if(user.getUsername() == null) return;
        User tempUser= userService.getUser(user.getUsername());
        if(tempUser == null || !tempUser.getPassword().equals(user.getPassword())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "username or password is not correct!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if(tempUser.getStatus() == 0){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "your account is not active! contact your administration", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        setUser(tempUser);
        exit("../views/user-panel.xhtml");
    }

    public void logout(){
        user = new User();
        exit("../views/signIn.xhtml");
    }
    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
    private void uploadFile(UploadedFile file, String filePath) {
        try (InputStream input = file.getInputStream()) {
            // Define where to save the file (change this path as needed)
            File targetFile = new File(filePath);

            Files.copy(input, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException e) {
        }
    }
    public void updateUser(){
        // upload file
        if(file != null) {
            String fileName= UUID.randomUUID().toString() + "." + getFileExtension(file.getFileName());
            String filePath= "D:\\IdeaProjects\\JSFDemo\\src\\main\\webapp\\imgs\\" + fileName;
            uploadFile(file, filePath);

            Attachment attach = new Attachment();
            attach.setFileName(fileName);
            attach.setFullPath(filePath);
            user.setAttach(attach);
            userService.updateUser(user);
        }

    }





    // setter and getter
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
