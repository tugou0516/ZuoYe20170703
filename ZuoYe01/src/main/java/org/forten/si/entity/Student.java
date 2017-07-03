package org.forten.si.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
@Entity
@Table(name="stu_contact_info")
public class Student {
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String gender;
    @Column(name="id_card_num")
    private String idCardNum;
    @Column
    private String email;
    @Column
    private String tel;
    @Column
    private String address;
    @Column
    private Date birthday;
    @Column(name="edu_bg")
    private String eduBg;
    @Column
    private String status;
    @Column(name="regist_time")
    private Date registTime;

    public Student() {
        this.registTime = new Date();
        this.status = "BM";
    }

    public int getId() {
        return id;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
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
