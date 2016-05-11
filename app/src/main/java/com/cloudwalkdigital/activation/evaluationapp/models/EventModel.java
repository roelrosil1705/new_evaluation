package com.cloudwalkdigital.activation.evaluationapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by henry on 10/05/2016.
 */
public class EventModel extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String jonum;
    private String eventdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJonum() {
        return jonum;
    }

    public void setJonum(String jonum) {
        this.jonum = jonum;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }
}
