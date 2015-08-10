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

import com.t.bean.CommentBean;
import com.t.service.interfaces.ICommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class CommentServiceTest {

	@Resource
	private ICommentService commentService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFetchComments() {
		List<CommentBean> commentBeans = new ArrayList<CommentBean>();
		commentBeans = commentService.fetchComments(10, 1);
		assertEquals(commentBeans, new ArrayList<CommentBean>());
	}

	@Test
	public void testAddComments() {
		Integer n = commentService.addComments(new Integer(1),new Integer(2),new Integer(1),"物超所值",new Double(4),new Double(4),new Double(4),new Double(40));
		assertEquals(n, new Integer(49));
	}

	@Test
	public void testGetAnalysisResult() {
		List<Float> list = new ArrayList<Float>();
		list.add(4.0f);
		list.add(4.0f);
		list.add(4.0f);
		assertEquals(commentService.getAnalysisResult(5), list);
	}

}
