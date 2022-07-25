package com.epam.shchehlov.bean;

import com.epam.shchehlov.entity.User;
import com.epam.shchehlov.entity.attribute.Role;

/**
 * Class for temporary storage of data entered by the user during registration.
 *
 * @author B.Shchehlov.
 */
public class UserRegistrationBean {

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private boolean mailing;
    private String userCaptcha;
    private Role role;

    public UserRegistrationBean(String firstName, String lastName, String email, String login, String password, boolean mailing, String userCaptcha, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.mailing = mailing;
        this.userCaptcha = userCaptcha;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMailing() {
        return mailing;
    }

    public void setMailing(boolean mailing) {
        this.mailing = mailing;
    }

    public String getUserCaptcha() {
        return userCaptcha;
    }

    public void setUserCaptcha(String userCaptcha) {
        this.userCaptcha = userCaptcha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User createUser() {
        return new User(firstName, lastName, email, login, password, mailing, role);
    }

    @Override
    public String toString() {
        return "UserRegistrationBean{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mailing=" + mailing +
                ", userCaptcha='" + userCaptcha + '\'' +
                ", role=" + role +
                '}';
    }
}