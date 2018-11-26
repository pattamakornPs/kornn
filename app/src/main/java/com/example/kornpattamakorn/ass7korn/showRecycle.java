package com.example.kornpattamakorn.ass7korn;

public class showRecycle {
    private String stdid,name,tel,email;
    public showRecycle(String stdid, String name, String tel, String email){
        this.stdid = stdid;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public String getStdid(){
        return stdid;
    }
    public void setStdid(String stdid) {
        this.stdid = stdid;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTel(){
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
