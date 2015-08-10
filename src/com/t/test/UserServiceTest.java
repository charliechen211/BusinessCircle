package com.t.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.t.service.interfaces.IUserService;
import com.t.utils.Constants;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class UserServiceTest {
	@Autowired
	private IUserService userServe;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		assertEquals(userServe.login("13611345613", "1234"), new Integer(1));
		assertEquals(userServe.login("13611345613", "123"), new Integer(-1));
	}

	@Test
	public void testRegister() {
	//	assertEquals(userServe.register("13611345613", "123", 1, 20, "龙儿", 1, 1, 1, 12,""),new Integer(-1));
	}

	@Test
	public void testAddFriend() {
		assertEquals(userServe.addFriend(1, 1), new Integer(Constants.ERROR));
	}

	@Test
	public void testDeleteFriend() {
		assertEquals(userServe.deleteFriend(1, 1), new Integer(Constants.ERROR));
	}

}
