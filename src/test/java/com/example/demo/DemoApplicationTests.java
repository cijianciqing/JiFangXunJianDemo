package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.dingding.JiFangXunJianDao;
import com.example.demo.dao.dingding.MyImageDao;
import com.example.demo.service.dingding.RiZhiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserDao userDao;

	@Autowired
	JiFangXunJianDao jiFangXunJianDao;

	@Autowired
    MyImageDao myImageDao;

	@Autowired
    RiZhiService riZhiService;

	@Test
	public void testJFXJ(){
		riZhiService.jiFangXunJian();
	}


//	@Test
//	public void testSimpleSpringData(){
//		List<MyImage> myImages = myImageDao.getByJiFangXunJian_ID("16d7045fe5771d32f94e43046a9b8153");
//		for (MyImage myImage: myImages) {
//			System.out.println(myImage);
//		}
//	}

//	@Test
//	public void testDelete(){
//		riZhiService.testDelete();
//	}
}
