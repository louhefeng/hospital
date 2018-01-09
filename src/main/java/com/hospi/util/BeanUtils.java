package com.hospi.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.hospi.dao.bean.Column;
import com.hospi.dao.bean.Id;
import com.hospi.dao.bean.Table;

@SuppressWarnings({ "unused", "unchecked" ,"rawtypes"})
public class BeanUtils {

	public static void main(String[] args) {
//		Tuser te=new Tuser();
//		te.setCname("孙俊虎");
//		//te.setCid("123");
//		//te.setCmail("findme1110@sina.com");
//		System.out.println(BeanUtils.createInsert(te));
//		System.out.println(BeanUtils.createUpdate(te));
//		System.out.println(BeanUtils.createDelete(te));
	}
	/**
	 * 生成insert语句
	 * @param o
	 * @return
	 */
	public static Map createInsert(Object o) {
		String tableName=getTableName(o);
		return createInsert(tableName,o);
	}
    /**
     * 生成update语句
     * @param o
     * @return
     */
	public static Map createUpdate(Object o) {
		String tableName=getTableName(o);
		return createUpdate(tableName,o);
	}
    /**
     * 生成createDelete语句
     * @param o
     * @return
     */
	public static Map createDelete(Object o) {
		String tableName=getTableName(o);
		return createDelete(tableName,o);
	}
	
	public static Map createDelete(String tableName,Object o) {
    	StringBuffer sqlBuffer = new StringBuffer() ;
    	List<Object> param = new Vector<Object>();
		sqlBuffer.append("DELETE FROM ").append(tableName).append(" WHERE ") ;
		List<Map> props=BeanUtils.getFiledsInfo(o);
		for(Map prop : props){
			if(prop.get("id") != null){
				sqlBuffer.append(prop.get("name")).append("=?"); 
				param.add(prop.get("value")) ; 
			}
		}
		Map ret_urn = new HashMap();
		ret_urn.put("sql", sqlBuffer.toString());
		ret_urn.put("params", param);
		return ret_urn;
	}
	
	public static Map createUpdate(String tableName,Object o) {
		StringBuffer sqlBuffer = new StringBuffer() ;
		List<Object> param = new Vector<Object>();
		sqlBuffer.append("UPDATE ").append(tableName).append(" SET ") ;
		List<Map> props=BeanUtils.getFiledsInfo(o);
		Object idName=""; 
		Object idValue=null; 
		for(Map prop : props){
			if(prop.get("id") == null){
				if(prop.get("name") !=null && prop.get("value") !=null){
					sqlBuffer.append(prop.get("name")).append("=?").append(",") ; 
					param.add(prop.get("value")) ; 
				} 
			}else{
				idName =prop.get("name"); 
				idValue =prop.get("value"); 
			}
		}
		int length = sqlBuffer.length() ;
		sqlBuffer.delete(length-1, length).append(" WHERE ").append(idName).append("=?") ;
		param.add( idValue );
		
		Map ret_urn = new HashMap();
		ret_urn.put("sql", sqlBuffer.toString());
		ret_urn.put("params", param);
		return ret_urn;
	}

	public static Map createInsert(String tableName,Object o) {
		StringBuffer sqlBuffer = new StringBuffer() ; 
		List<Object> param = new Vector<Object>();
		Map ret_urn = new HashMap();
		sqlBuffer.append("INSERT INTO ").append(tableName).append("(");
		List<Map> props=BeanUtils.getFiledsInfo(o);
		for(Map prop : props){
			if(prop.get("name") !=null && prop.get("value") !=null){
				sqlBuffer.append(prop.get("name")).append(",") ; 
				param.add(prop.get("value")) ; 
			}
		}
		int length = sqlBuffer.length() ;
		sqlBuffer.delete(length-1, length).append(") values("); 
		int size = param.size() ;
		for(int x=0;x<size;x++){
			if(x != 0){
				sqlBuffer.append(",") ;  
			}
			sqlBuffer.append("?") ; 
		}
		sqlBuffer.append(")");
		ret_urn.put("sql", sqlBuffer.toString());
		ret_urn.put("params", param);
		return ret_urn;
	}
	
	/**
	 * 根据注解获取表名
	 * */
	public static String getTableName(Object target) {
		String tableName = null ;
		Class<?> clazz = target.getClass() ;
		tableName = getTableNameForClass(clazz);
		return tableName ;
	}
	public static String getTableNameForClass(Class<?> clazz) {
		String tableName;
		Table table = clazz.getAnnotation(Table.class) ; 
		if(null != table){
			tableName = table.name() ;
			if("".equalsIgnoreCase(tableName)){
				tableName = clazz.getSimpleName() ;
			}
		}else{
			tableName = clazz.getSimpleName() ;
		}
		return tableName;
	}
	/**
	 * 根据属性名获取属性值
	 * */
	public static Object getFieldValueByName(String fieldName, Object o) {
        try {  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
            String getter = "get" + firstLetter + fieldName.substring(1);  
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  
            return value;  
        } catch (Exception e) {  
            System.out.println(e.getMessage());
            return null;  
        }  
    } 
    
    /**
     * 获取属性名数组
     * */
    public static String[] getFiledName(Object o){
    	Field[] fields=o.getClass().getDeclaredFields();
       	String[] fieldNames=new String[fields.length];
    	for(int i=0;i<fields.length;i++){
    		fieldNames[i]=fields[i].getName();
    	}
    	return fieldNames;
    }
    
    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * 
     */
   public static List getFiledsInfo(Object o){
    	Field[] fields=o.getClass().getDeclaredFields();
       	String[] fieldNames=new String[fields.length];
       	List list = new ArrayList();
       	Map infoMap=null;
    	for(Field field:fields){
    		infoMap = new HashMap();
    		Id id = field.getAnnotation(Id.class);
			if(id != null){
				infoMap.put("id", "true");
			}
			Column cols= field.getAnnotation(Column.class);
			if(cols != null){
				infoMap.put("name", cols.name());
			}else{
				infoMap.put("name", field.getName());
			}
    		infoMap.put("type", field.getType().toString());
    		infoMap.put("value", getFieldValueByName(field.getName(), o));

    		list.add(infoMap);
    	}
    	return list;
    }
    
    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
	public static Object[] getFiledValues(Object o){
    	String[] fieldNames=getFiledName(o);
    	Object[] value=new Object[fieldNames.length];
    	for(int i=0;i<fieldNames.length;i++){
    		value[i]=getFieldValueByName(fieldNames[i], o);
    	}
    	return value;
    }
}