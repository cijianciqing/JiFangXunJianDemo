package com.example.demo.beans.dingding.rizhi;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "DDT_jiFangXunJian")
public class JiFangXunJian implements Serializable{

    @Id
    private String ID;
    private String jiFang;
    private Date riQi;
    private Double wenDu;
    private Double shiDu;
    private String ups;
    private String kongTiao;
    private String mieHuoQi;
    private String weiSheng;
    private String sheBei;
    private String yiChang;

    private Date createTime;
    private String creator;


//    @OneToMany(mappedBy = "jiFangXunJian",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    mapperBy:由多的一方维护关联关系，1的一方放弃维护，后面的值是多的一方--类中的属性
//    在此例中jiFangXunJian是MyImage类中的一个属性
    @OneToMany(mappedBy = "jiFangXunJian",fetch=FetchType.EAGER,cascade = {CascadeType.REMOVE})
    private List<MyImage> images;

    public JiFangXunJian(){}


    @Override
    public String toString() {
        return "JiFangXunJian{" +
                "ID=" + ID +
                ", jiFang='" + jiFang + '\'' +
                ", riQi=" + riQi +
                ", wenDu=" + wenDu +
                ", shiDu=" + shiDu +
                ", ups='" + ups + '\'' +
                ", kongTiao='" + kongTiao + '\'' +
                ", mieHuoQi='" + mieHuoQi + '\'' +
                ", weiSheng='" + weiSheng + '\'' +
                ", sheBei='" + sheBei + '\'' +
                ", yiChang='" + yiChang + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", images=" + images +
                '}';
    }
}
