package com.tugasakhir.skripsi;

import org.json.JSONObject;

public class PojoLogUser {
    String WaktuLog, UserLog, InfoLog;

    public PojoLogUser(String date, String user, String information) {

    }

    public String getWaktuLog() {
        return WaktuLog;
    }

    public void setWaktuLog(String waktuLog) {
        WaktuLog = waktuLog;
    }

    public String getUserLog() {
        return UserLog;
    }

    public void setUserLog(String userLog) {
        UserLog = userLog;
    }

    public String getInfoLog() {
        return InfoLog;
    }

    public void setInfoLog(String infoLog) {
        InfoLog = infoLog;
    }

    PojoLogUser(JSONObject obj){
        try {
            String date = obj.getString("date");
            String user = obj.getString("user");
            String information = obj.getString("information");

            this.WaktuLog = date;
            this.UserLog = user;
            this.InfoLog = information;

        }catch (Exception e) {
            e.printStackTrace();
       }
    }
}
