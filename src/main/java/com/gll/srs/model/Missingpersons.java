package com.gll.srs.model;

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
    private String option;


    public int getPersonsId() {
        return personsId;
    }

    public void setPersonsId(int personsId) {
        this.personsId = personsId;
    }

    public String getPersonsName() {
        return personsName;
    }

    public void setPersonsName(String personsName) {
        this.personsName = personsName;
    }

    public Integer getPersonsAge() {
        return personsAge;
    }

    public void setPersonsAge(Integer personsAge) {
        this.personsAge = personsAge;
    }

    public Integer getPersonsBodyheight() {
        return personsBodyheight;
    }

    public void setPersonsBodyheight(Integer personsBodyheight) {
        this.personsBodyheight = personsBodyheight;
    }

    public String getPersonsFeature() {
        return personsFeature;
    }

    public void setPersonsFeature(String personsFeature) {
        this.personsFeature = personsFeature;
    }

    public String getPersonsAddress() {
        return personsAddress;
    }

    public void setPersonsAddress(String personsAddress) {
        this.personsAddress = personsAddress;
    }

    public Integer getPersonsDna() {
        return personsDna;
    }

    public void setPersonsDna(Integer personsDna) {
        this.personsDna = personsDna;
    }

    public String getPersonsDateDiscovered() {
        return personsDateDiscovered;
    }

    public void setPersonsDateDiscovered(String personsDateDiscovered) {
        this.personsDateDiscovered = personsDateDiscovered;
    }

    public String getPersonsDiscoverySites() {
        return personsDiscoverySites;
    }

    public void setPersonsDiscoverySites(String personsDiscoverySites) {
        this.personsDiscoverySites = personsDiscoverySites;
    }

    public String getPersonsContact() {
        return personsContact;
    }

    public void setPersonsContact(String personsContact) {
        this.personsContact = personsContact;
    }

    public String getPersonsRescueunit() {
        return personsRescueunit;
    }

    public void setPersonsRescueunit(String personsRescueunit) {
        this.personsRescueunit = personsRescueunit;
    }

    public String getPersonsReleasedate() {
        return personsReleasedate;
    }

    public void setPersonsReleasedate(String personsReleasedate) {
        this.personsReleasedate = personsReleasedate;
    }

    public String getPersonsNote() {
        return personsNote;
    }

    public void setPersonsNote(String personsNote) {
        this.personsNote = personsNote;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMissState() {
        return missState;
    }

    public void setMissState(Integer missState) {
        this.missState = missState;
    }

    public String getPersonsGender() {
        return personsGender;
    }

    public void setPersonsGender(String personsGender) {
        this.personsGender = personsGender;
    }

    public String getPersonsDress() {
        return personsDress;
    }

    public void setPersonsDress(String personsDress) {
        this.personsDress = personsDress;
    }

    public String getPsersonsPic() {
        return psersonsPic;
    }

    public void setPsersonsPic(String psersonsPic) {
        this.psersonsPic = psersonsPic;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
