package org.forten.si.dto;

import org.forten.utils.common.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by student1 on 2017/7/10.
 */
public class Student4Export {
    private String name;
    private String gender;
    private Date birthday;
    private String address;

    public Student4Export() {
    }

    public Student4Export(String name, String gender, Date birthday, String address) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public String getBirthdayStr(){
        return DateUtil.convertDateToString(birthday,"yyyy-MM-dd");
    }

    public int getAge() {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        now.setTime(new Date());
        birth.setTime(birthday);
        return now.get(Calendar.YEAR)- birth.get(Calendar.YEAR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student4Export that = (Student4Export) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student4Export{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}
