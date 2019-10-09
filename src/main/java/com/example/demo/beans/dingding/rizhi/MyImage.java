package com.example.demo.beans.dingding.rizhi;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
//@IdClass(MyImageRelated.class)
@Entity
@Table(name = "DDT_xunJianImage")
public class MyImage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String url;

    @ManyToOne()
    @JoinColumn(name = "jiFangXunJian_ID")
    private JiFangXunJian jiFangXunJian;


    public MyImage(){

    }

    public MyImage(Integer ID, String url,JiFangXunJian jiFangXunJian) {
        this.ID = ID;
        this.url = url;
        this.jiFangXunJian = jiFangXunJian;
    }

    @Override
    public String toString() {
        return "MyImage{" +
                "ID=" + ID +
                ", url=" + url +
                ", jiFangXunJian=" + jiFangXunJian.getID() +
                '}';
    }
}
