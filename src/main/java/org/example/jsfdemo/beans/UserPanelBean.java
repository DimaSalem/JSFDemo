package org.example.jsfdemo.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.jsfdemo.entities.User;
import org.example.jsfdemo.services.UserService;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import java.io.Serializable;
import java.util.List;

@Named (value = "userPanelBean")
@ViewScoped
public class UserPanelBean implements Serializable {
    private DashboardModel model;

    @EJB
    private UserService userService;
    private List<User> users;

    public UserPanelBean() {
        model = new DefaultDashboardModel();

        // Create columns
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        // Add widgetVar values as widgets
        column1.addWidget("userDashboard");
        column2.addWidget("users");

        // Add columns to the dashboard model
        model.addColumn(column1);
        model.addColumn(column2);
    }

    @PostConstruct
    public void init() {
        users = userService.getUsers();
    }

    public DashboardModel getModel() {
        return model;
    }




    // setter and getter
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
