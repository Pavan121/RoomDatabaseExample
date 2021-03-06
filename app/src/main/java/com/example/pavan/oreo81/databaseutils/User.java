package com.example.pavan.oreo81.databaseutils;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by pavan on 15/11/17.
 * this is an entity class we can also call it an one table from database in simple words
 * columnInfo contains the actual column name in database
 * if we want to change this schema after installation we have to migrate database using migration
 * shown in @{@link AppDatabase}
 * this same entity is used to perform operations on database @{@link UserDao}
 */

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name="last_name")
    private String lastName;

    @ColumnInfo(name = "role")
    private String role;

    @Ignore
    private String standard;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName!=null?lastName:"";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName!=null?firstName:"";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role!=null?role:"";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
