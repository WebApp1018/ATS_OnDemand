package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class JobTemplateModel implements Serializable {
    int Aflag, BUID,CID,JTUID,JTcatID,JTemplateID,JTnumopening,JTpriority,JTtravel
            ,Taflag,UID,deptbudget,deptfiscalstart,deptforcast,deptforecasthcount,fk_JID,fkmajor_id,notes_id
            ,sub_id;

    List JTjobhours;
    String Businessunit,DeptHead,DeptHeadID,DeptQtype,DeptQuestion,DeptSitelink,Deptsite,EEODisposition
            ,Intdname ,JTIntNotes ,JTJobQ_num,JTMaster,JTMonstercity,JTZip,JTbenefit,JTcity,JTconfidentialpost
            ,JTcountry,JTdegree,JTdepartment,JTexpyears,JTintranet,JTintskills,JTjobduration,JTjobkeyword
            ,JTjoblevel,JTjoblocation,JTjobtype,JTlanguage,JTonlinepost,JTorder,JTpubnet,JTpunet,JTrelocate
            ,JTsalarycurrency,JTsalaryrange,JTsaltype,JTstate,Keyword,LocationCode,TDescription,TReqSkills
            ,Tdepid,TemplateTitle,address1,address2,city,country,ddescription,depcareerupdate,deptLang,deptText
            ,deptUID,dept_no,deptcustomcatidone,deptcustomcatidtwo,deptnameN,deptnameinternal,deptofficerTitle,deptofficerempid
            ,deptofficerfirstname,deptofficerlastname,deptofficermiddlename,deptofficersurname,deptstaff,dname
            ,fee,feetype,fkkeyholder,genfax_no,genphone_no,jtroletype,latitude,longitude,notes,postalcode,salary
            ,state,subcatname,subcatname_CHI,subcatname_DEU,subcatname_FRA,subcatname_SPA,subcatname_ZHO,templatedate,type,JTeeoc_job_cat
            ,JTtotalconfidential;


    public int getAflag() {
        return Aflag;
    }

    public void setAflag(int aflag) {
        Aflag = aflag;
    }

    public int getBUID() {
        return BUID;
    }

    public void setBUID(int BUID) {
        this.BUID = BUID;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getJTUID() {
        return JTUID;
    }

    public void setJTUID(int JTUID) {
        this.JTUID = JTUID;
    }

    public int getJTcatID() {
        return JTcatID;
    }

    public void setJTcatID(int JTcatID) {
        this.JTcatID = JTcatID;
    }

    public String getJTeeoc_job_cat() {
        return JTeeoc_job_cat;
    }

    public void setJTeeoc_job_cat(String JTeeoc_job_cat) {
        this.JTeeoc_job_cat = JTeeoc_job_cat;
    }

    public int getJTemplateID() {
        return JTemplateID;
    }

    public void setJTemplateID(int JTemplateID) {
        this.JTemplateID = JTemplateID;
    }

    public int getJTnumopening() {
        return JTnumopening;
    }

    public void setJTnumopening(int JTnumopening) {
        this.JTnumopening = JTnumopening;
    }

    public int getJTpriority() {
        return JTpriority;
    }

    public void setJTpriority(int JTpriority) {
        this.JTpriority = JTpriority;
    }

    public String getJTtotalconfidential() {
        return JTtotalconfidential;
    }

    public void setJTtotalconfidential(String JTtotalconfidential) {
        this.JTtotalconfidential = JTtotalconfidential;
    }

    public int getJTtravel() {
        return JTtravel;
    }

    public void setJTtravel(int JTtravel) {
        this.JTtravel = JTtravel;
    }

    public int getTaflag() {
        return Taflag;
    }

    public void setTaflag(int taflag) {
        Taflag = taflag;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getDeptbudget() {
        return deptbudget;
    }

    public void setDeptbudget(int deptbudget) {
        this.deptbudget = deptbudget;
    }

    public int getDeptfiscalstart() {
        return deptfiscalstart;
    }

    public void setDeptfiscalstart(int deptfiscalstart) {
        this.deptfiscalstart = deptfiscalstart;
    }

    public int getDeptforcast() {
        return deptforcast;
    }

    public void setDeptforcast(int deptforcast) {
        this.deptforcast = deptforcast;
    }

    public int getDeptforecasthcount() {
        return deptforecasthcount;
    }

    public void setDeptforecasthcount(int deptforecasthcount) {
        this.deptforecasthcount = deptforecasthcount;
    }

    public int getFk_JID() {
        return fk_JID;
    }

    public void setFk_JID(int fk_JID) {
        this.fk_JID = fk_JID;
    }

    public int getFkmajor_id() {
        return fkmajor_id;
    }

    public void setFkmajor_id(int fkmajor_id) {
        this.fkmajor_id = fkmajor_id;
    }

    public int getNotes_id() {
        return notes_id;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public List getJTjobhours() {
        return JTjobhours;
    }

    public void setJTjobhours(List JTjobhours) {
        this.JTjobhours = JTjobhours;
    }

    public String getBusinessunit() {
        return Businessunit;
    }

    public void setBusinessunit(String businessunit) {
        Businessunit = businessunit;
    }

    public String getDeptHead() {
        return DeptHead;
    }

    public void setDeptHead(String deptHead) {
        DeptHead = deptHead;
    }

    public String getDeptHeadID() {
        return DeptHeadID;
    }

    public void setDeptHeadID(String deptHeadID) {
        DeptHeadID = deptHeadID;
    }

    public String getDeptQtype() {
        return DeptQtype;
    }

    public void setDeptQtype(String deptQtype) {
        DeptQtype = deptQtype;
    }

    public String getDeptQuestion() {
        return DeptQuestion;
    }

    public void setDeptQuestion(String deptQuestion) {
        DeptQuestion = deptQuestion;
    }

    public String getDeptSitelink() {
        return DeptSitelink;
    }

    public void setDeptSitelink(String deptSitelink) {
        DeptSitelink = deptSitelink;
    }

    public String getDeptsite() {
        return Deptsite;
    }

    public void setDeptsite(String deptsite) {
        Deptsite = deptsite;
    }

    public String getEEODisposition() {
        return EEODisposition;
    }

    public void setEEODisposition(String EEODisposition) {
        this.EEODisposition = EEODisposition;
    }

    public String getIntdname() {
        return Intdname;
    }

    public void setIntdname(String intdname) {
        Intdname = intdname;
    }

    public String getJTIntNotes() {
        return JTIntNotes;
    }

    public void setJTIntNotes(String JTIntNotes) {
        this.JTIntNotes = JTIntNotes;
    }

    public String getJTJobQ_num() {
        return JTJobQ_num;
    }

    public void setJTJobQ_num(String JTJobQ_num) {
        this.JTJobQ_num = JTJobQ_num;
    }

    public String getJTMaster() {
        return JTMaster;
    }

    public void setJTMaster(String JTMaster) {
        this.JTMaster = JTMaster;
    }

    public String getJTMonstercity() {
        return JTMonstercity;
    }

    public void setJTMonstercity(String JTMonstercity) {
        this.JTMonstercity = JTMonstercity;
    }

    public String getJTZip() {
        return JTZip;
    }

    public void setJTZip(String JTZip) {
        this.JTZip = JTZip;
    }

    public String getJTbenefit() {
        return JTbenefit;
    }

    public void setJTbenefit(String JTbenefit) {
        this.JTbenefit = JTbenefit;
    }

    public String getJTcity() {
        return JTcity;
    }

    public void setJTcity(String JTcity) {
        this.JTcity = JTcity;
    }

    public String getJTconfidentialpost() {
        return JTconfidentialpost;
    }

    public void setJTconfidentialpost(String JTconfidentialpost) {
        this.JTconfidentialpost = JTconfidentialpost;
    }

    public String getJTcountry() {
        return JTcountry;
    }

    public void setJTcountry(String JTcountry) {
        this.JTcountry = JTcountry;
    }

    public String getJTdegree() {
        return JTdegree;
    }

    public void setJTdegree(String JTdegree) {
        this.JTdegree = JTdegree;
    }

    public String getJTdepartment() {
        return JTdepartment;
    }

    public void setJTdepartment(String JTdepartment) {
        this.JTdepartment = JTdepartment;
    }

    public String getJTexpyears() {
        return JTexpyears;
    }

    public void setJTexpyears(String JTexpyears) {
        this.JTexpyears = JTexpyears;
    }

    public String getJTintranet() {
        return JTintranet;
    }

    public void setJTintranet(String JTintranet) {
        this.JTintranet = JTintranet;
    }

    public String getJTintskills() {
        return JTintskills;
    }

    public void setJTintskills(String JTintskills) {
        this.JTintskills = JTintskills;
    }

    public String getJTjobduration() {
        return JTjobduration;
    }

    public void setJTjobduration(String JTjobduration) {
        this.JTjobduration = JTjobduration;
    }

    public String getJTjobkeyword() {
        return JTjobkeyword;
    }

    public void setJTjobkeyword(String JTjobkeyword) {
        this.JTjobkeyword = JTjobkeyword;
    }

    public String getJTjoblevel() {
        return JTjoblevel;
    }

    public void setJTjoblevel(String JTjoblevel) {
        this.JTjoblevel = JTjoblevel;
    }

    public String getJTjoblocation() {
        return JTjoblocation;
    }

    public void setJTjoblocation(String JTjoblocation) {
        this.JTjoblocation = JTjoblocation;
    }

    public String getJTjobtype() {
        return JTjobtype;
    }

    public void setJTjobtype(String JTjobtype) {
        this.JTjobtype = JTjobtype;
    }

    public String getJTlanguage() {
        return JTlanguage;
    }

    public void setJTlanguage(String JTlanguage) {
        this.JTlanguage = JTlanguage;
    }

    public String getJTonlinepost() {
        return JTonlinepost;
    }

    public void setJTonlinepost(String JTonlinepost) {
        this.JTonlinepost = JTonlinepost;
    }

    public String getJTorder() {
        return JTorder;
    }

    public void setJTorder(String JTorder) {
        this.JTorder = JTorder;
    }

    public String getJTpubnet() {
        return JTpubnet;
    }

    public void setJTpubnet(String JTpubnet) {
        this.JTpubnet = JTpubnet;
    }

    public String getJTpunet() {
        return JTpunet;
    }

    public void setJTpunet(String JTpunet) {
        this.JTpunet = JTpunet;
    }

    public String getJTrelocate() {
        return JTrelocate;
    }

    public void setJTrelocate(String JTrelocate) {
        this.JTrelocate = JTrelocate;
    }

    public String getJTsalarycurrency() {
        return JTsalarycurrency;
    }

    public void setJTsalarycurrency(String JTsalarycurrency) {
        this.JTsalarycurrency = JTsalarycurrency;
    }

    public String getJTsalaryrange() {
        return JTsalaryrange;
    }

    public void setJTsalaryrange(String JTsalaryrange) {
        this.JTsalaryrange = JTsalaryrange;
    }

    public String getJTsaltype() {
        return JTsaltype;
    }

    public void setJTsaltype(String JTsaltype) {
        this.JTsaltype = JTsaltype;
    }

    public String getJTstate() {
        return JTstate;
    }

    public void setJTstate(String JTstate) {
        this.JTstate = JTstate;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getTDescription() {
        return TDescription;
    }

    public void setTDescription(String TDescription) {
        this.TDescription = TDescription;
    }

    public String getTReqSkills() {
        return TReqSkills;
    }

    public void setTReqSkills(String TReqSkills) {
        this.TReqSkills = TReqSkills;
    }

    public String getTdepid() {
        return Tdepid;
    }

    public void setTdepid(String tdepid) {
        Tdepid = tdepid;
    }

    public String getTemplateTitle() {
        return TemplateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        TemplateTitle = templateTitle;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDdescription() {
        return ddescription;
    }

    public void setDdescription(String ddescription) {
        this.ddescription = ddescription;
    }

    public String getDepcareerupdate() {
        return depcareerupdate;
    }

    public void setDepcareerupdate(String depcareerupdate) {
        this.depcareerupdate = depcareerupdate;
    }

    public String getDeptLang() {
        return deptLang;
    }

    public void setDeptLang(String deptLang) {
        this.deptLang = deptLang;
    }

    public String getDeptText() {
        return deptText;
    }

    public void setDeptText(String deptText) {
        this.deptText = deptText;
    }

    public String getDeptUID() {
        return deptUID;
    }

    public void setDeptUID(String deptUID) {
        this.deptUID = deptUID;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDeptcustomcatidone() {
        return deptcustomcatidone;
    }

    public void setDeptcustomcatidone(String deptcustomcatidone) {
        this.deptcustomcatidone = deptcustomcatidone;
    }

    public String getDeptcustomcatidtwo() {
        return deptcustomcatidtwo;
    }

    public void setDeptcustomcatidtwo(String deptcustomcatidtwo) {
        this.deptcustomcatidtwo = deptcustomcatidtwo;
    }

    public String getDeptnameN() {
        return deptnameN;
    }

    public void setDeptnameN(String deptnameN) {
        this.deptnameN = deptnameN;
    }

    public String getDeptnameinternal() {
        return deptnameinternal;
    }

    public void setDeptnameinternal(String deptnameinternal) {
        this.deptnameinternal = deptnameinternal;
    }

    public String getDeptofficerTitle() {
        return deptofficerTitle;
    }

    public void setDeptofficerTitle(String deptofficerTitle) {
        this.deptofficerTitle = deptofficerTitle;
    }

    public String getDeptofficerempid() {
        return deptofficerempid;
    }

    public void setDeptofficerempid(String deptofficerempid) {
        this.deptofficerempid = deptofficerempid;
    }

    public String getDeptofficerfirstname() {
        return deptofficerfirstname;
    }

    public void setDeptofficerfirstname(String deptofficerfirstname) {
        this.deptofficerfirstname = deptofficerfirstname;
    }

    public String getDeptofficerlastname() {
        return deptofficerlastname;
    }

    public void setDeptofficerlastname(String deptofficerlastname) {
        this.deptofficerlastname = deptofficerlastname;
    }

    public String getDeptofficermiddlename() {
        return deptofficermiddlename;
    }

    public void setDeptofficermiddlename(String deptofficermiddlename) {
        this.deptofficermiddlename = deptofficermiddlename;
    }

    public String getDeptofficersurname() {
        return deptofficersurname;
    }

    public void setDeptofficersurname(String deptofficersurname) {
        this.deptofficersurname = deptofficersurname;
    }

    public String getDeptstaff() {
        return deptstaff;
    }

    public void setDeptstaff(String deptstaff) {
        this.deptstaff = deptstaff;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFeetype() {
        return feetype;
    }

    public void setFeetype(String feetype) {
        this.feetype = feetype;
    }

    public String getFkkeyholder() {
        return fkkeyholder;
    }

    public void setFkkeyholder(String fkkeyholder) {
        this.fkkeyholder = fkkeyholder;
    }

    public String getGenfax_no() {
        return genfax_no;
    }

    public void setGenfax_no(String genfax_no) {
        this.genfax_no = genfax_no;
    }

    public String getGenphone_no() {
        return genphone_no;
    }

    public void setGenphone_no(String genphone_no) {
        this.genphone_no = genphone_no;
    }

    public String getJtroletype() {
        return jtroletype;
    }

    public void setJtroletype(String jtroletype) {
        this.jtroletype = jtroletype;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

    public String getSubcatname_CHI() {
        return subcatname_CHI;
    }

    public void setSubcatname_CHI(String subcatname_CHI) {
        this.subcatname_CHI = subcatname_CHI;
    }

    public String getSubcatname_DEU() {
        return subcatname_DEU;
    }

    public void setSubcatname_DEU(String subcatname_DEU) {
        this.subcatname_DEU = subcatname_DEU;
    }

    public String getSubcatname_FRA() {
        return subcatname_FRA;
    }

    public void setSubcatname_FRA(String subcatname_FRA) {
        this.subcatname_FRA = subcatname_FRA;
    }

    public String getSubcatname_SPA() {
        return subcatname_SPA;
    }

    public void setSubcatname_SPA(String subcatname_SPA) {
        this.subcatname_SPA = subcatname_SPA;
    }

    public String getSubcatname_ZHO() {
        return subcatname_ZHO;
    }

    public void setSubcatname_ZHO(String subcatname_ZHO) {
        this.subcatname_ZHO = subcatname_ZHO;
    }

    public String getTemplatedate() {
        return templatedate;
    }

    public void setTemplatedate(String templatedate) {
        this.templatedate = templatedate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void initModel(JSONObject jsonObject){
        try {
            setJTemplateID(jsonObject.getInt("JTemplateID"));
            setJTjobkeyword(jsonObject.getString("JTjobkeyword"));
            setTDescription(jsonObject.getString("TDescription"));
            setTemplateTitle(jsonObject.getString("TemplateTitle"));
            setKeyword(jsonObject.getString("Keyword"));
            setJTbenefit(jsonObject.getString("JTbenefit"));
            setJTnumopening(jsonObject.getInt("JTnumopening"));
            setJTjobtype(jsonObject.getString("JTjobtype"));
            setSalary(jsonObject.getString("salary"));
            setJTsalaryrange(jsonObject.getString("JTsalaryrange"));
            setJTsalarycurrency(jsonObject.getString("JTsalarycurrency"));
            setJTsaltype(jsonObject.getString("JTsaltype"));
            setJTpriority(jsonObject.getInt("JTpriority"));
            setAflag(jsonObject.getInt("Aflag"));
            setJTjobduration(jsonObject.getString("JTjobduration"));
            setJTeeoc_job_cat(jsonObject.getString("JTeeoc_job_cat"));
            setJTcatID(jsonObject.getInt("JTcatID"));
            setJTdepartment(jsonObject.getString("JTdepartment"));
            setJTZip(jsonObject.getString("JTZip"));
            setJTcountry(jsonObject.getString("JTcountry"));
            setJTstate(jsonObject.getString("JTstate"));
            setJTjoblocation(jsonObject.getString("JTjoblocation"));
            setJTcity(jsonObject.getString("JTcity"));
            setJTjoblevel(jsonObject.getString("JTjoblevel"));
            setTDescription(jsonObject.getString("TDescription"));
            setJTexpyears(jsonObject.getString("JTexpyears"));
            setJTdegree(jsonObject.getString("JTdegree"));
            setJTtravel(jsonObject.getInt("JTtravel"));
            setJTIntNotes(jsonObject.getString("JTIntNotes"));
            setJTjobkeyword(jsonObject.getString("JTjobkeyword"));
            setJTbenefit(jsonObject.getString("JTbenefit"));
            setJTlanguage(jsonObject.getString("JTlanguage"));
            setJTtotalconfidential(jsonObject.getString("JTtotalconfidential"));
            setUID(jsonObject.getInt("UID"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
