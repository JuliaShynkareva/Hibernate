package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Администратор on 09.11.2016.
 */

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "firstname", length = 30)
    private String firstname;

    @Column(name = "lastname", length = 30)
    private String lastname;

    @Column(name = "age", length = 30)
    private Integer age;

    @Column(name = "sex", length = 10)
    private String sex;

    @Column(name = "phone_number", length = 30)
    private String phone_number;

    @Column(name = "city", length = 30)
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (getId() != null ? !getId().equals(profile.getId()) : profile.getId() != null) return false;
        if (getEmail() != null ? !getEmail().equals(profile.getEmail()) : profile.getEmail() != null) return false;
        if (getFirstname() != null ? !getFirstname().equals(profile.getFirstname()) : profile.getFirstname() != null)
            return false;
        if (getLastname() != null ? !getLastname().equals(profile.getLastname()) : profile.getLastname() != null)
            return false;
        if (getAge() != null ? !getAge().equals(profile.getAge()) : profile.getAge() != null) return false;
        if (getSex() != null ? !getSex().equals(profile.getSex()) : profile.getSex() != null) return false;
        if (getPhone_number() != null ? !getPhone_number().equals(profile.getPhone_number()) : profile.getPhone_number() != null)
            return false;
        return !(getCity() != null ? !getCity().equals(profile.getCity()) : profile.getCity() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFirstname() != null ? getFirstname().hashCode() : 0);
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
        result = 31 * result + (getPhone_number() != null ? getPhone_number().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
