package com.example.demo.dao.dingding;

import com.example.demo.beans.dingding.rizhi.JiFangXunJian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JiFangXunJianDao extends JpaRepository<JiFangXunJian,String> ,JpaSpecificationExecutor<JiFangXunJian>{

    @Query("select j from JiFangXunJian j where j.id =:myID")
    public JiFangXunJian jFindOneByID(@Param("myID") String id);

    List<JiFangXunJian> findByJiFang(String jiFang);



}

