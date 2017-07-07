package org.forten.si.dto;

import org.forten.utils.security.SHA1Util;
import org.forten.utils.system.Assert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by student1 on 2017/7/3.
 */
public class Student4Save {
    @NotBlank(message = "请输入姓名")
    @Length(min=2,max=10,message = "请输入{min}到{max}个之间的字符")
    private String name;
    @NotBlank(message = "请输入密码")
    @Length(min=40,max=40,message = "密码长度有误")
    private String gender;
    @NotBlank(message = "请输入身份证号")
    @Pattern(regexp = "^[1-9]\\d{5}[1-2]\\d{3}((0[1-9])|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$",message = "请输入正确的18位身份证号")
    private String idCardNum;
    @NotBlank(message = "请输入email")
    @Email(message = "请输入正确的email格式")
    private String email;
    @NotBlank(message = "请输入电话")
    private String tel;
    @NotBlank(message = "请输入地址")
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "不可输入未来的日期")
    private Date birthday;
    @NotBlank(message = "请选择学历")
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
