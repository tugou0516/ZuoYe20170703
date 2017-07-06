package org.forten.si.dto;

/**
 * Created by student1 on 2017/7/6.
 */
public class LoginedUser {
    private int id;
    private String name;

    public LoginedUser() {
    }

    public LoginedUser(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginedUser that = (LoginedUser) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoginedUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
