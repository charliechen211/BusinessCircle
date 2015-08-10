package com.t.utils;

import java.sql.Types;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

public class MysqlOrder extends Order {
	private static final long serialVersionUID = 7821607348093388564L;

	private boolean ascending;
	private boolean ignoreCase;
	private String propertyName;

	protected MysqlOrder(String propertyName, boolean ascending) {
		super(propertyName, ascending);
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
			throws HibernateException {
		String[] columns = criteriaQuery.getColumnsUsingProjection(criteria,this.propertyName);
		Type type = criteriaQuery.getTypeUsingProjection(criteria,this.propertyName);
		StringBuffer fragment = new StringBuffer();
		for (int i = 0; i < columns.length; ++i) {
			SessionFactoryImplementor factory = criteriaQuery.getFactory();
			boolean isVarchar=(type.sqlTypes(factory)[i] == Types.VARCHAR);
			
			// 如果是字符串字段则需要按照中文排序则需要加入该函数的引用
			if(isVarchar){
				fragment.append("convert(");
			}
			
			boolean lower = this.ignoreCase && isVarchar;
			if (lower) {
				fragment.append(factory.getDialect().getLowercaseFunction()).append('(');
			}
			fragment.append(columns[i]);
			if (lower){
				fragment.append(')');
			}
			// 如果是字符串字段则需要按照中文排序则需要加入该函数的引用
			if(isVarchar){
				fragment.append(" using utf8)");
			}
			
			fragment.append((this.ascending) ? " asc" : " desc");
			if (i >= columns.length - 1){
				continue;
			}
			fragment.append(", ");
		}
		return fragment.toString();
	}
	
	  public static MysqlOrder asc(String propertyName){
	    return new MysqlOrder(propertyName, true);
	  }

	  public static MysqlOrder desc(String propertyName){
	    return new MysqlOrder(propertyName, false);
	  }

}
