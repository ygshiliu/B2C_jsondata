package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.google.gson.Gson;
import com.wnn.bean.T_mall_class_1;
import com.wnn.bean.T_mall_class_2;
import com.wnn.bean.T_mall_trade_mark;
import com.wnn.dao.T_mall_class_1_Mapper;
import com.wnn.dao.T_mall_class_2_Mapper;
import com.wnn.dao.T_mall_trade_mark_Mapper;

public class TestJson {
	
	@Test
	public void test01() throws Exception{
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		 SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		 
		 SqlSession session = factory.openSession();
		 T_mall_class_1_Mapper mapper = session.getMapper(T_mall_class_1_Mapper.class);
		 System.out.println(mapper);
		List<T_mall_class_1> list = mapper.select_class_1();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		FileOutputStream out = new FileOutputStream("e:/class_1.js");
		out.write(json.getBytes());
		out.close();
		
		System.out.println("class1=====>>"+json);
		
		T_mall_class_2_Mapper mapper2 = session.getMapper(T_mall_class_2_Mapper.class);
		
		for (T_mall_class_1 t_mall_class_1 : list) {
			Integer id = t_mall_class_1.getId();
			List<T_mall_class_2> class2 = mapper2.select_class_2(id);
			String json2 = gson.toJson(class2);
			FileOutputStream out2 = new FileOutputStream("e:/class_2_"+id+".js");
			out2.write(json2.getBytes());
			out2.close();
			System.out.println("class2=="+id+"===>>"+json2);
		}
		
		T_mall_trade_mark_Mapper mapper3 = session.getMapper(T_mall_trade_mark_Mapper.class);
		
		for (T_mall_class_1 t_mall_class_1 : list) {
			Integer id = t_mall_class_1.getId();
			 List<T_mall_trade_mark> tm = mapper3.select_spu_tm(id);
			String json3 = gson.toJson(tm);
			FileOutputStream out3 = new FileOutputStream("e:/tm_class_1_"+id+".js");
			out3.write(json3.getBytes());
			out3.close();
			System.out.println("tm=="+id+"===>>"+json3);
		}
		
		 
	}
}
