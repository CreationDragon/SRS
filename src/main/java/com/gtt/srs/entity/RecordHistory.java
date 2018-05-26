package com.gtt.srs.entity;

import javax.persistence.*;

@Entity
@Table(name = "record_history", schema = "srs", catalog = "")
public class RecordHistory {
    private int recordId;
    private Integer misspersonId;
    private Integer userId;

    @Id
    @Column(name = "record_id", nullable = false)
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "missperson_id", nullable = true)
    public Integer getMisspersonId() {
        return misspersonId;
    }

    public void setMisspersonId(Integer misspersonId) {
        this.misspersonId = misspersonId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordHistory that = (RecordHistory) o;

        if (recordId != that.recordId) return false;
        if (misspersonId != null ? !misspersonId.equals(that.misspersonId) : that.misspersonId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordId;
        result = 31 * result + (misspersonId != null ? misspersonId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
