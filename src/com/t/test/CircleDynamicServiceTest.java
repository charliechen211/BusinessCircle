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

import com.t.bean.CircleDynamicBean;
import com.t.service.interfaces.ICircleDynamicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class CircleDynamicServiceTest {

	@Resource
	private ICircleDynamicService circleDynamicService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCircleDynamic() {
		int n = circleDynamicService.addCircleDynamic(2, 0, 3, "味道不错", 1);
		assertEquals(new Integer(n),new Integer(-1));
	}

	@Test
	public void testFetchCircleDynamic() {
		List<CircleDynamicBean> list = new ArrayList<CircleDynamicBean>();
		list = circleDynamicService.fetchCircleDynamic(2);
		assertEquals(list, new ArrayList<CircleDynamicBean>());
	}

}
