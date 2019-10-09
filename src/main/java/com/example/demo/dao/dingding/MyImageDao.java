package com.example.demo.dao.dingding;

import com.example.demo.beans.dingding.rizhi.MyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyImageDao extends JpaRepository<MyImage,Integer> {

//    @Query("select count(j) from MyImage j where j.jiFangXunJian.ID = ?1 and j.url = ?2")
//    Integer findByJiFangXunJianAndUrl(String jiFangXunJian, String url);

    List<MyImage> getByJiFangXunJian_ID(String ID);

    @Modifying
    @Query("delete from MyImage m where m.jiFangXunJian.id = ?1")
    Integer deleteByJID(String jid);
}
