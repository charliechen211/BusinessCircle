package com.t.utils;

import java.util.Comparator;
import com.t.bean.ResultBean;

@SuppressWarnings("rawtypes")
public class ComparatorIndex implements Comparator {

	public int compare(Object arg0, Object arg1) {
		ResultBean user0 = (ResultBean) arg0;
		ResultBean user1 = (ResultBean) arg1;
		if (user0.getWeight() > user1.getWeight())
			return 1;
		else
			return 0;
	}
}
