package org.forten.si.dto;

import org.forten.utils.security.SHA1Util;
import org.forten.utils.system.Assert;

import java.util.Date;

/**
 * Created by student1 on 2017/7/3.
 */
public class Student4Save {
    private String name;
    private String gender;
    private String idCardNum;
    private String email;
    private String tel;
    private String address;
    private Date birthday;
    private String eduBg;

    public Student4Save() {
    }

    public Student4Save(String name, String gender, String idCardNum, String email, String tel, String address, Date birthday, String eduBg) {
        this.name = name;
        this.gender = gender;
        this.idCardNum = idCardNum;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.birthday = birthday;
        this.eduBg = eduBg;
    }

    public String getPassword(){
        Assert.hasText(this.idCardNum,"证件号不可为空");
        return SHA1Util.encryptSHA(this.idCardNum.substring(idCardNum.length()-6));
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

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEduBg() {
        return eduBg;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student4Save that = (Student4Save) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (idCardNum != null ? !idCardNum.equals(that.idCardNum) : that.idCardNum != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        return eduBg != null ? eduBg.equals(that.eduBg) : that.eduBg == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (idCardNum != null ? idCardNum.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (eduBg != null ? eduBg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student4Save{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", eduBg='" + eduBg + '\'' +
                '}';
    }
}
