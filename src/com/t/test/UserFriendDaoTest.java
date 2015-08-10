package com.t.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.t.core.dao.UserFriendDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class UserFriendDaoTest {
	@Resource
	private UserFriendDao dao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFanNum() {
		assertEquals(dao.getFanNum(8), new Integer(1));
	}

	@Test
	public void testGetFollowNum() {
		assertEquals(dao.getFollowNum(2), new Integer(2));
	}

}
