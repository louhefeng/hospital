package com.hospi.dao.imp;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hospi.dao.BaseDaoI;
import com.hospi.exception.DataAccessException;
import com.hospi.util.BeanUtils;


@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) throws DataAccessException {
		Map m = BeanUtils.createInsert(o);
		String sql = "";
		String params = "";
		try {
			sql = (String) m.get("sql");
			List l = (List) m.get("params");
			params = l.toString();
			Integer count = Integer.valueOf(executeSQL(sql, l));
			if(sql.indexOf("INSERT")>-1){
				BigInteger lastId = (BigInteger) getCurrentSession().createSQLQuery("select last_insert_id();").uniqueResult();
				return lastId;
			}else{
				return count; 
			}
		} catch (Exception ex) {
			throw new DataAccessException("保存失败：" + sql + params, ex);
		}
	}

	public void delete(T o) throws DataAccessException {
		Map m = BeanUtils.createDelete(o);
		String sql = "";
		String params = "";
		try {
			sql = (String) m.get("sql");
			List l = (List) m.get("params");
			params = l.toString();
			executeSQL(sql, l);
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + sql + params, ex);
		}
	}

	public void update(T o) throws DataAccessException {
		Map m = BeanUtils.createUpdate(o);
		String sql = "";
		String params = "";
		try {
			sql = (String) m.get("sql");
			List l = (List) m.get("params");
			params = l.toString();
			executeSQL(sql, l);
		} catch (Exception ex) {
			throw new DataAccessException("修改失败：" + sql + params, ex);
		}
	}

	public Long countSQL(String sql, List<Object> param) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return Long.valueOf(q.uniqueResult().toString());
	}

	public List<T> findPage(String sql, List<Object> param, Integer page,
			Integer rows, Object obj) {
		if ((page == null) || (page.intValue() < 1)) {
			page = Integer.valueOf(1);
		}
		if ((rows == null) || (rows.intValue() < 1)) {
			rows = Integer.valueOf(10);
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		q.setFirstResult((page.intValue() - 1) * rows.intValue())
				.setMaxResults(rows.intValue());
		List<Map> list = q.setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP).list();
		List beans = new ArrayList();
		for (Map Map : list) {
			try {
				Object bean = obj.getClass().newInstance();
				toBean(bean, Map);
				beans.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beans;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findDTPage(String sql, List<Object> param, Integer page,
			Integer rows, Object obj) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		q.setFirstResult(page.intValue()).setMaxResults(rows.intValue());
		List<Map> list = q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		List beans = new ArrayList();
		for (Map Map : list) {
			try {
				Object bean = obj.getClass().newInstance();
				toBean(bean, Map);
				beans.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beans;
	}

	public List<T> findList(String sql, List<Object> param, Object obj) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List<Map> list = q.setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP).list();
		List beans = new ArrayList();
		for (Map Map : list) {
			try {
				Object bean = obj.getClass().newInstance();
				toBean(bean, Map);
				beans.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return beans;
	}

	public Object findById(String tableName, String idKey,
			Serializable idValue, Object obj) {
		String sql = "select * from " + tableName + " where " + idKey + "= ?";
		List values = new ArrayList();
		values.add(idValue);
		return find(sql, values, obj);
	}

	public Object find(String sql, List<Object> param, Object obj) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List list = q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		Object bean = null;
		if ((list != null) && (list.size() > 0)) {
			try {
				bean = obj.getClass().newInstance();
				toBean(bean, (Map) list.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}

	public List<Map> findMap(String sql, List<Object> param) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List list = q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		return list;
	}

	private Object toBean(Object javabean, Map data) {
		Method[] methods = javabean.getClass().getDeclaredMethods();
		for (Method method : methods)
			try {
				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					//忽略大小写获取map中的值
					for (Object o : data.keySet()) {
						if(o!=null && o.toString().equalsIgnoreCase(field)){
							Class[] parameterTypes = method.getParameterTypes();
							for(int i = 0;i<parameterTypes.length;i++){
								if("String".equals(parameterTypes[i].getSimpleName())){
									method.invoke(javabean, new Object[] { data.get(o.toString())});
								}else if("Long".equals(parameterTypes[i].getSimpleName())){
									method.invoke(javabean, Long.parseLong(data.get(o.toString())+""));
								}else if("Integer".equals(parameterTypes[i].getSimpleName())){
									method.invoke(javabean, Integer.parseInt(data.get(o.toString())+""));
								}else{
									method.invoke(javabean, new Object[] { data.get(o.toString()) });
								}
							}
						}
						continue;
					}
				}
			} catch (Exception localException) {
			}
		return javabean;
	}

	public int executeSQL(String sql, List<Object> param) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	public boolean validate(String sql, List<Object> param) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if ((param != null) && (param.size() > 0)) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		Long l = Long.valueOf(q.uniqueResult().toString());
		return l.longValue() > 0L;
	}

}