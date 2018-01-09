package com.hospi.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 基础数据库操作类
 * 
 * @author 孙俊虎
 * 
 */
public interface BaseDaoI<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);
	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);
	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);
	/**
	 * 执行sql，修改，删除，增加记录
	 * @param sql
	 * @param param
	 * @return
	 */
	public int executeSQL(String sql, List<Object> param);
	/**
	 * 查询总数
	 * @param sql
	 * @param param
	 * @return
	 */
	public Long countSQL(String sql, List<Object> param);
    /**
     * 查询分页
     * @param sql
     * @param param
     * @param page
     * @param rows
     * @param obj
     * @return
     */
	public List<T> findDTPage(String sql, List<Object> param, Integer page,Integer rows, Object obj);
	 /**
     * 查询分页
     * @param sql
     * @param param
     * @param page
     * @param rows
     * @param obj
     * @return
     */
	public List<T> findPage(String sql, List<Object> param, Integer page,Integer rows, Object obj);

	/**
	 * 查询不带分页，返回map
	 * @param sql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findMap(String sql,List<Object> param);
	/**
	 * 查询不带分页，返回对象
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<T> findList(String sql, List<Object> param, Object obj);
	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public Object find(String sql, List<Object> param, Object obj);
	/**
	 * 根据id查找对象
	 * @param tableName
	 * @param idKey
	 * @param idValue
	 * @return
	 */
	public Object findById(String tableName, String idKey,Serializable idValue,Object obj);
	/**
	 * 验证数据完整性
	 * @param sql
	 * @param param
	 * @return
	 */
	public boolean validate(String sql, List<Object> param);
	
}
