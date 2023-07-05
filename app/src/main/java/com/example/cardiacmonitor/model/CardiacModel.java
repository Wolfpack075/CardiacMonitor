package com.example.cardiacmonitor.model;

import java.io.Serializable;

public class CardiacModel implements Serializable {
    public String date, time, systolic, diastolic, heartRate, comment;
    public long id;
    public CardiacModel() {

        /**
         * get input from users from the data entry page
         * Users can give the necessary data to
         * monitor them.
         * @param date
         * @param time
         * @param systolic
         * @param diastolic
         * @param heartRate
         * @param comment
         */

        this.id = 0L;
        this.date = "";
        this.time = "";
        this.systolic = "";
        this.diastolic = "";
        this.heartRate = "";
        this.comment = "";
    }

    /**
     * get date from user
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * get time
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * get systolic
     * @return systolic
     */
    public String getSystolic() {
        return systolic;
    }
    /**
     * get diastolic
     * @return diastolic
     */
    public String getDiastolic() {
        return diastolic;
    }
    /**
     * get heart rate
     * @return heart rate
     */
    public String getHeartRate() {
        return heartRate;
    }

    /**
     * get comment
     * @return comment
     */

    public String getComment() {
        return comment;
    }


    /**
     * get id
     * @return id
     */
    public long getId() {
        return id;
    }
    /**
     * set date
     * @param date
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * set time
     * @param  time
     */
    public void setTime(String time) {
        this.time = time;
    }


    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }
    /**
     * user can give comment.
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setId(long id) {
        this.id = id;
    }

    public CardiacModel( String date, String time, String systolic, String diastolic, String heartRate, String comment) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heartRate;
        this.comment = comment;
    }

}
