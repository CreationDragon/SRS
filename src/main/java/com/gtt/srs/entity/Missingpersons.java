package com.gtt.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Missingpersons {
    private int personsId;
    private String personsName;
    private Integer personsAge;
    private Integer personsBodyheight;
    private String personsFeature;
    private String personsAddress;
    private Integer personsDna;
    private String personsDateDiscovered;
    private String personsDiscoverySites;
    private String personsContact;
    private String personsRescueunit;
    private String personsReleasedate;
    private String personsNote;
    private Integer userId;
    private Integer missState;
    private String personsGender;
    private String personsDress;
    private String psersonsPic;
    private Integer state;

    @Id
    @Column(name = "persons_id", nullable = false)
    public int getPersonsId() {
        return personsId;
    }

    public void setPersonsId(int personsId) {
        this.personsId = personsId;
    }

    @Basic
    @Column(name = "persons_name", nullable = true, length = 60)
    public String getPersonsName() {
        return personsName;
    }

    public void setPersonsName(String personsName) {
        this.personsName = personsName;
    }

    @Basic
    @Column(name = "persons_age", nullable = true)
    public Integer getPersonsAge() {
        return personsAge;
    }

    public void setPersonsAge(Integer personsAge) {
        this.personsAge = personsAge;
    }

    @Basic
    @Column(name = "persons_bodyheight", nullable = true)
    public Integer getPersonsBodyheight() {
        return personsBodyheight;
    }

    public void setPersonsBodyheight(Integer personsBodyheight) {
        this.personsBodyheight = personsBodyheight;
    }

    @Basic
    @Column(name = "persons_feature", nullable = true, length = 300)
    public String getPersonsFeature() {
        return personsFeature;
    }

    public void setPersonsFeature(String personsFeature) {
        this.personsFeature = personsFeature;
    }

    @Basic
    @Column(name = "persons_address", nullable = true, length = 300)
    public String getPersonsAddress() {
        return personsAddress;
    }

    public void setPersonsAddress(String personsAddress) {
        this.personsAddress = personsAddress;
    }

    @Basic
    @Column(name = "persons_DNA", nullable = true)
    public Integer getPersonsDna() {
        return personsDna;
    }

    public void setPersonsDna(Integer personsDna) {
        this.personsDna = personsDna;
    }

    @Basic
    @Column(name = "persons_DateDiscovered", nullable = true, length = 30)
    public String getPersonsDateDiscovered() {
        return personsDateDiscovered;
    }

    public void setPersonsDateDiscovered(String personsDateDiscovered) {
        this.personsDateDiscovered = personsDateDiscovered;
    }

    @Basic
    @Column(name = "persons_DiscoverySites", nullable = true, length = 300)
    public String getPersonsDiscoverySites() {
        return personsDiscoverySites;
    }

    public void setPersonsDiscoverySites(String personsDiscoverySites) {
        this.personsDiscoverySites = personsDiscoverySites;
    }

    @Basic
    @Column(name = "persons_Contact", nullable = true, length = 60)
    public String getPersonsContact() {
        return personsContact;
    }

    public void setPersonsContact(String personsContact) {
        this.personsContact = personsContact;
    }

    @Basic
    @Column(name = "persons_rescueunit", nullable = true, length = 300)
    public String getPersonsRescueunit() {
        return personsRescueunit;
    }

    public void setPersonsRescueunit(String personsRescueunit) {
        this.personsRescueunit = personsRescueunit;
    }

    @Basic
    @Column(name = "persons_RELEASEDATE", nullable = true, length = 30)
    public String getPersonsReleasedate() {
        return personsReleasedate;
    }

    public void setPersonsReleasedate(String personsReleasedate) {
        this.personsReleasedate = personsReleasedate;
    }

    @Basic
    @Column(name = "persons_Note", nullable = true, length = 300)
    public String getPersonsNote() {
        return personsNote;
    }

    public void setPersonsNote(String personsNote) {
        this.personsNote = personsNote;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "persons_dress", nullable = true, length = 255)
    public String getPersonsDress() {
        return personsDress;
    }

    public void setPersonsDress(String personsDress) {
        this.personsDress = personsDress;
    }

    @Basic
    @Column(name = "miss_state", nullable = true)
    public Integer getMissState() {
        return missState;
    }

    public void setMissState(Integer missState) {
        this.missState = missState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Missingpersons that = (Missingpersons) o;

        if (personsId != that.personsId) return false;
        if (personsName != null ? !personsName.equals(that.personsName) : that.personsName != null) return false;
        if (personsAge != null ? !personsAge.equals(that.personsAge) : that.personsAge != null) return false;
        if (personsBodyheight != null ? !personsBodyheight.equals(that.personsBodyheight) : that.personsBodyheight != null)
            return false;
        if (personsFeature != null ? !personsFeature.equals(that.personsFeature) : that.personsFeature != null)
            return false;
        if (personsAddress != null ? !personsAddress.equals(that.personsAddress) : that.personsAddress != null)
            return false;
        if (personsDna != null ? !personsDna.equals(that.personsDna) : that.personsDna != null) return false;
        if (personsDateDiscovered != null ? !personsDateDiscovered.equals(that.personsDateDiscovered) : that.personsDateDiscovered != null)
            return false;
        if (personsDiscoverySites != null ? !personsDiscoverySites.equals(that.personsDiscoverySites) : that.personsDiscoverySites != null)
            return false;
        if (personsContact != null ? !personsContact.equals(that.personsContact) : that.personsContact != null)
            return false;
        if (personsRescueunit != null ? !personsRescueunit.equals(that.personsRescueunit) : that.personsRescueunit != null)
            return false;
        if (personsReleasedate != null ? !personsReleasedate.equals(that.personsReleasedate) : that.personsReleasedate != null)
            return false;
        if (personsNote != null ? !personsNote.equals(that.personsNote) : that.personsNote != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (missState != null ? !missState.equals(that.missState) : that.missState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personsId;
        result = 31 * result + (personsName != null ? personsName.hashCode() : 0);
        result = 31 * result + (personsAge != null ? personsAge.hashCode() : 0);
        result = 31 * result + (personsBodyheight != null ? personsBodyheight.hashCode() : 0);
        result = 31 * result + (personsFeature != null ? personsFeature.hashCode() : 0);
        result = 31 * result + (personsAddress != null ? personsAddress.hashCode() : 0);
        result = 31 * result + (personsDna != null ? personsDna.hashCode() : 0);
        result = 31 * result + (personsDateDiscovered != null ? personsDateDiscovered.hashCode() : 0);
        result = 31 * result + (personsDiscoverySites != null ? personsDiscoverySites.hashCode() : 0);
        result = 31 * result + (personsContact != null ? personsContact.hashCode() : 0);
        result = 31 * result + (personsRescueunit != null ? personsRescueunit.hashCode() : 0);
        result = 31 * result + (personsReleasedate != null ? personsReleasedate.hashCode() : 0);
        result = 31 * result + (personsNote != null ? personsNote.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (missState != null ? missState.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "persons_gender", nullable = true, length = 30)
    public String getPersonsGender() {
        return personsGender;
    }

    public void setPersonsGender(String personsGender) {
        this.personsGender = personsGender;
    }

    @Basic
    @Column(name = "psersons_pic", nullable = true, length = 255)
    public String getPsersonsPic() {
        return psersonsPic;
    }

    public void setPsersonsPic(String psersonsPic) {
        this.psersonsPic = psersonsPic;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
