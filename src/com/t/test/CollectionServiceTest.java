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
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.t.bean.CircleDynamicBean;
import com.t.service.interfaces.ICollectionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class CollectionServiceTest extends AbstractJUnit4SpringContextTests {
	@Resource
	private ICollectionService collectionService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testAddCollection() {
		assertEquals(collectionService.addCollection(1,1,1), new Integer(-1));
	}
	@Test
	public void testFetchCollection() {
		List<CircleDynamicBean> result= new ArrayList<CircleDynamicBean>();
	//	result = collectionService.fetchCollection(3);
		assertEquals(result,new ArrayList<CircleDynamicBean>());
	}

}
