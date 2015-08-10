package com.t.utils;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class Page<T> extends QueryParameter implements Serializable {

	private List<T> result = null;

	private int totalCount = -1;
	
	private String originalOrder = null;

	public Page() {
		this.pageNo = 1;
		this.pageSize = Constants.DEFAUT_PAGE_SIZE;
		this.autoCount = true;
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageSize, boolean autoCount) {
		this.pageSize = pageSize;
		this.autoCount = autoCount;
	}
	
	/**
	 * 取得倒转的排序方向
	 */
	public String getInverseOrder() {
		if (order.endsWith(DESC))
			return ASC;
		else
			return DESC;
	}

	/**
	 * 页内的数据列表.
	 */
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 总记录数.
	 */
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 计算总页数.
	 */
	public int getTotalPages() {
		if (totalCount == -1)
			return -1;

		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 返回下页的页号,序号从1开始.
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一页. 
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 返回上页的页号,序号从1开始.
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
	
	/**
	 * 重载setOrder方法，当参数错误时，将order置空。
	 */
	@Override
	public void setOrder(String order) {
		try {
			originalOrder = order.trim();
			if (0 == originalOrder.length()) {
				originalOrder = null;
			}
			super.setOrder(originalOrder);
		} catch (IllegalArgumentException e) {
			super.setOrder(null);
		}
	}
	
	@Override
	public void setOrderBy(String orderBy) {
		if (orderBy != null) {
			orderBy = orderBy.trim();
			if (0 == orderBy.length()) {
				orderBy = null;
			}
			super.setOrderBy(orderBy);
		}
	}
	
	public boolean checkOrder() {
		if (null == order) {
			return false;
		}
		if (null == orderBy) {
			return true;
		}
		return order.split(ORDER_TOKEN).length == orderBy.split(ORDER_TOKEN).length;
	}
	
	public boolean checkOrderBy(String[] validOrderByArray) {
		List<String> orderByList=StringUtils.parseStringToStringList(orderBy, ORDER_TOKEN);
		if (null == orderByList) {
			return true;
		}
		boolean match = false;
		for (String orderBy : orderByList) {
			match = false;
			for (String validOrderBy : validOrderByArray) {
				if (orderBy.equals(validOrderBy)) {
					match = true;
					break;
				}
			}
			if (!match) {
				return false;
			}
		}
		return true;
	}
}

