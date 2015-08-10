package com.t.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bsh.This;

import com.t.bean.ItemBean;
import com.t.service.interfaces.IItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class ItemServiceTest {

	@Resource
	private IItemService itemService;
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindMerchantItemsById() {
		List<ItemBean> list = new ArrayList<ItemBean>();
		//list = itemService.findMerchantItemsById(1);
		assertEquals(list, new ArrayList<ItemBean>());
	}

	@Test
	public void testGetItemByItemId() {
		
		ItemBean itemBean0 = new ItemBean();
		ItemBean itemBean4 = new ItemBean();
		itemBean4.setItemId(new Integer(4));
		itemBean4.setItemName(new String("素鸭"));
		itemBean4.setPicture(new String("1387856044434.jpg"));
		itemBean4.setPrice(new Float(25));
		itemBean4.setRate(new Float(3));
		itemBean4.setTags(new ArrayList<String>());
		assertEquals(itemService.getItemByItemId(0),itemBean0);
	}

}
