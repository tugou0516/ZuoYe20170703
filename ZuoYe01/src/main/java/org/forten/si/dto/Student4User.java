package org.forten.si.dto;

import org.forten.utils.common.DateUtil;
import org.forten.utils.common.StringUtil;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by student1 on 2017/7/5.
 */
public class Student4User {
    private int id;
    private String name;
    private String password;
    private String gender;
    private String idCardNum;
    private String email;
    private String tel;
    private String address;
    private Date birthday;
    private String eduBg;
    private String status;
    private Date registTime;

    public Student4User() {
    }

    public String getRegistTimeStr(){
        String str = DateUtil.convertDateToString(registTime);
        return str;
    }

    public String getBirthdayStr(){
        String str = DateUtil.convertDateToString(birthday,"yyyy-MM-dd");
        return str;
    }

    public String getStatusDes() {
        if(StringUtil.hasText(status)){
            switch (status){
                case "BM":
                    return "报名";
                case "SK":
                    return "上课";
                case "BY":
                    return "毕业";
                case "XX":
                    return "休学";
                case "TX":
                    return "退学";
                case "CX":
                    return "重修";
                default:
                    return "未知";
            }
        }else {
            return "未填写";
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEduBg() {
        return eduBg;
    }

    public String getStatus() {
        return status;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student4User that = (Student4User) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (idCardNum != null ? !idCardNum.equals(that.idCardNum) : that.idCardNum != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (eduBg != null ? !eduBg.equals(that.eduBg) : that.eduBg != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return registTime != null ? registTime.equals(that.registTime) : that.registTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (idCardNum != null ? idCardNum.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (eduBg != null ? eduBg.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (registTime != null ? registTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student4User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", eduBg='" + eduBg + '\'' +
                ", status='" + status + '\'' +
                ", registTime=" + registTime +
                '}';
    }
}
