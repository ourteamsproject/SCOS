package es.source.code.model;

import java.io.Serializable;

/**
 * Created by Cristo on 2017/10/8.
 */

public class User implements Serializable {
    public String userNmae;
    public String password;
    public Boolean oldUser;

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getOldUser() {
        return oldUser;
    }

    public void setOldUser(Boolean oldUser) {
        this.oldUser = oldUser;
    }
}
