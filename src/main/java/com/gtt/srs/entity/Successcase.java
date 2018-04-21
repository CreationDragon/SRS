package com.gtt.srs.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Successcase {
    private int caseId;
    private String caseTitle;
    private String caseContent;
    private String caseDate;

    @Id
    @Column(name = "case_id", nullable = false)
    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "case_title", nullable = true, length = 200)
    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    @Basic
    @Column(name = "case_content", nullable = true, length = 200)
    public String getCaseContent() {
        return caseContent;
    }

    public void setCaseContent(String caseContent) {
        this.caseContent = caseContent;
    }

    @Basic
    @Column(name = "case_date", nullable = true, length = 20)
    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Successcase that = (Successcase) o;

        if (caseId != that.caseId) return false;
        if (caseTitle != null ? !caseTitle.equals(that.caseTitle) : that.caseTitle != null) return false;
        if (caseContent != null ? !caseContent.equals(that.caseContent) : that.caseContent != null) return false;
        if (caseDate != null ? !caseDate.equals(that.caseDate) : that.caseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseId;
        result = 31 * result + (caseTitle != null ? caseTitle.hashCode() : 0);
        result = 31 * result + (caseContent != null ? caseContent.hashCode() : 0);
        result = 31 * result + (caseDate != null ? caseDate.hashCode() : 0);
        return result;
    }
}
