package net.th.order_service.domain;

import java.util.Date;

public class UserDomain {

    public String Id;

    public String name;

    public String eamil;

    public Date creatTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "UserDomain{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", eamil='" + eamil + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}
