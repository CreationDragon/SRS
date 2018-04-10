package com.gll.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Volunteer {
    private int volunteerId;
    private String volunteerName;
    private Integer volunteerAge;
    private String volunteerGender;
    private String volunteerAddress;
    private String volunteerEmail;
    private String volunteerPhone;
    private String volunteerRegisterDate;

    @Id
    @Column(name = "volunteer_id", nullable = false)
    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    @Basic
    @Column(name = "volunteer_name", nullable = true, length = 255)
    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    @Basic
    @Column(name = "volunteer_age", nullable = true)
    public Integer getVolunteerAge() {
        return volunteerAge;
    }

    public void setVolunteerAge(Integer volunteerAge) {
        this.volunteerAge = volunteerAge;
    }

    @Basic
    @Column(name = "volunteer_gender", nullable = true, length = 255)
    public String getVolunteerGender() {
        return volunteerGender;
    }

    public void setVolunteerGender(String volunteerGender) {
        this.volunteerGender = volunteerGender;
    }

    @Basic
    @Column(name = "volunteer_address", nullable = true, length = 255)
    public String getVolunteerAddress() {
        return volunteerAddress;
    }

    public void setVolunteerAddress(String volunteerAddress) {
        this.volunteerAddress = volunteerAddress;
    }

    @Basic
    @Column(name = "volunteer_email", nullable = true, length = 255)
    public String getVolunteerEmail() {
        return volunteerEmail;
    }

    public void setVolunteerEmail(String volunteerEmail) {
        this.volunteerEmail = volunteerEmail;
    }

    @Basic
    @Column(name = "volunteer_phone", nullable = true, length = 255)
    public String getVolunteerPhone() {
        return volunteerPhone;
    }

    public void setVolunteerPhone(String volunteerPhone) {
        this.volunteerPhone = volunteerPhone;
    }

    @Basic
    @Column(name = "volunteer_register_date", nullable = true)
    public String getVolunteerRegisterDate() {
        return volunteerRegisterDate;
    }

    public void setVolunteerRegisterDate(String volunteerRegisterDate) {
        this.volunteerRegisterDate = volunteerRegisterDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Volunteer volunteer = (Volunteer) o;

        if (volunteerId != volunteer.volunteerId) return false;
        if (volunteerName != null ? !volunteerName.equals(volunteer.volunteerName) : volunteer.volunteerName != null)
            return false;
        if (volunteerAge != null ? !volunteerAge.equals(volunteer.volunteerAge) : volunteer.volunteerAge != null)
            return false;
        if (volunteerGender != null ? !volunteerGender.equals(volunteer.volunteerGender) : volunteer.volunteerGender != null)
            return false;
        if (volunteerAddress != null ? !volunteerAddress.equals(volunteer.volunteerAddress) : volunteer.volunteerAddress != null)
            return false;
        if (volunteerEmail != null ? !volunteerEmail.equals(volunteer.volunteerEmail) : volunteer.volunteerEmail != null)
            return false;
        if (volunteerPhone != null ? !volunteerPhone.equals(volunteer.volunteerPhone) : volunteer.volunteerPhone != null)
            return false;
        if (volunteerRegisterDate != null ? !volunteerRegisterDate.equals(volunteer.volunteerRegisterDate) : volunteer.volunteerRegisterDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = volunteerId;
        result = 31 * result + (volunteerName != null ? volunteerName.hashCode() : 0);
        result = 31 * result + (volunteerAge != null ? volunteerAge.hashCode() : 0);
        result = 31 * result + (volunteerGender != null ? volunteerGender.hashCode() : 0);
        result = 31 * result + (volunteerAddress != null ? volunteerAddress.hashCode() : 0);
        result = 31 * result + (volunteerEmail != null ? volunteerEmail.hashCode() : 0);
        result = 31 * result + (volunteerPhone != null ? volunteerPhone.hashCode() : 0);
        result = 31 * result + (volunteerRegisterDate != null ? volunteerRegisterDate.hashCode() : 0);
        return result;
    }
}
