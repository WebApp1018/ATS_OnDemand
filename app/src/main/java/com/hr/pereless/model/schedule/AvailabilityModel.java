package com.hr.pereless.model.schedule;

import android.util.Log;

import org.json.JSONObject;

public class AvailabilityModel {
    String shiftto,schpublic,userlink,schshift,schrepeat,Shiftfrom,schLocation,Sch_id,EndTime,InvID,Aflag,offset,End_time_utc,schtype,PlanUUID;
    String Start_time_utc,timezone,sch_UUID,PlanID,AllDate, LeaderID ,StartTime,schtext,schshow,Subject,timezonestr,UID,CID,RID,JID,Enterdate;

    public String getShiftto() {
        return shiftto;
    }

    public void setShiftto(String shiftto) {
        this.shiftto = shiftto;
    }

    public String getSchpublic() {
        return schpublic;
    }

    public void setSchpublic(String schpublic) {
        this.schpublic = schpublic;
    }

    public String getUserlink() {
        return userlink;
    }

    public void setUserlink(String userlink) {
        this.userlink = userlink;
    }

    public String getSchshift() {
        return schshift;
    }

    public void setSchshift(String schshift) {
        this.schshift = schshift;
    }

    public String getSchrepeat() {
        return schrepeat;
    }

    public void setSchrepeat(String schrepeat) {
        this.schrepeat = schrepeat;
    }

    public String getShiftfrom() {
        return Shiftfrom;
    }

    public void setShiftfrom(String shiftfrom) {
        Shiftfrom = shiftfrom;
    }

    public String getSchLocation() {
        return schLocation;
    }

    public void setSchLocation(String schLocation) {
        this.schLocation = schLocation;
    }

    public String getSch_id() {
        return Sch_id;
    }

    public void setSch_id(String sch_id) {
        Sch_id = sch_id;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getInvID() {
        return InvID;
    }

    public void setInvID(String invID) {
        InvID = invID;
    }

    public String getAflag() {
        return Aflag;
    }

    public void setAflag(String aflag) {
        Aflag = aflag;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getEnd_time_utc() {
        return End_time_utc;
    }

    public void setEnd_time_utc(String end_time_utc) {
        End_time_utc = end_time_utc;
    }

    public String getSchtype() {
        return schtype;
    }

    public void setSchtype(String schtype) {
        this.schtype = schtype;
    }

    public String getPlanUUID() {
        return PlanUUID;
    }

    public void setPlanUUID(String planUUID) {
        PlanUUID = planUUID;
    }

    public String getStart_time_utc() {
        return Start_time_utc;
    }

    public void setStart_time_utc(String start_time_utc) {
        Start_time_utc = start_time_utc;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSch_UUID() {
        return sch_UUID;
    }

    public void setSch_UUID(String sch_UUID) {
        this.sch_UUID = sch_UUID;
    }

    public String getPlanID() {
        return PlanID;
    }

    public void setPlanID(String planID) {
        PlanID = planID;
    }

    public String getAllDate() {
        return AllDate;
    }

    public void setAllDate(String allDate) {
        AllDate = allDate;
    }

    public String getLeaderID() {
        return LeaderID;
    }

    public void setLeaderID(String leaderID) {
        LeaderID = leaderID;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getSchtext() {
        return schtext;
    }

    public void setSchtext(String schtext) {
        this.schtext = schtext;
    }

    public String getSchshow() {
        return schshow;
    }

    public void setSchshow(String schshow) {
        this.schshow = schshow;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTimezonestr() {
        return timezonestr;
    }

    public void setTimezonestr(String timezonestr) {
        this.timezonestr = timezonestr;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getJID() {
        return JID;
    }

    public void setJID(String JID) {
        this.JID = JID;
    }

    public String getEnterdate() {
        return Enterdate;
    }

    public void setEnterdate(String enterdate) {
        Enterdate = enterdate;
    }

    public void initModel(JSONObject jsonObject){
        try {
            setUID(jsonObject.getString("UID"));
            setShiftfrom(jsonObject.getString("Shiftfrom"));
            setShiftto(jsonObject.getString("shiftto"));
            setAllDate(jsonObject.getString("AllDate"));
            setSch_id(jsonObject.getString("Sch_id"));

        }catch (Exception e){
            Log.d("Exception == ", e.toString());
        }
    }
}
