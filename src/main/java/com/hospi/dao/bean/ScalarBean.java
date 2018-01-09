/*    */ package com.hospi.dao.bean;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.hibernate.SQLQuery;
/*    */ import org.hibernate.type.BigDecimalType;
/*    */ import org.hibernate.type.BooleanType;
/*    */ import org.hibernate.type.CharacterType;
/*    */ import org.hibernate.type.DoubleType;
/*    */ import org.hibernate.type.FloatType;
/*    */ import org.hibernate.type.IntegerType;
/*    */ import org.hibernate.type.LongType;
/*    */ import org.hibernate.type.ShortType;
/*    */ import org.hibernate.type.StringType;
/*    */ import org.hibernate.type.TimestampType;
/*    */ 
/*    */ public class ScalarBean
/*    */ {
/*    */   public static <T> void addPropertySclar(SQLQuery sqlQuery, Class<T> clazz, List<String> fieldList)
/*    */   {
/* 39 */     if (clazz == null) {
/* 40 */       throw new NullPointerException("[clazz] could not be null!");
/*    */     }
/* 42 */     if ((fieldList != null) && (fieldList.size() > 0)) {
/* 43 */       Field[] fields = clazz.getDeclaredFields();
/* 44 */       for (String fieldName : fieldList)
/* 45 */         for (Field field : fields) {
/* 46 */           String fieldGetName = field.getName();
/* 47 */           String getName = fieldGetName.substring(12, fieldGetName.length());
/* 48 */           if (fieldName.equals(getName))
/* 49 */             if ((field.getType() == Long.TYPE) || (field.getType() == Long.class))
/* 50 */               sqlQuery.addScalar(getName, LongType.INSTANCE);
/* 51 */             else if ((field.getType() == Integer.TYPE) || (field.getType() == Integer.class))
/* 52 */               sqlQuery.addScalar(getName, IntegerType.INSTANCE);
/* 53 */             else if ((field.getType() == Character.TYPE) || (field.getType() == Character.class))
/* 54 */               sqlQuery.addScalar(getName, CharacterType.INSTANCE);
/* 55 */             else if ((field.getType() == Short.TYPE) || (field.getType() == Short.class))
/* 56 */               sqlQuery.addScalar(getName, ShortType.INSTANCE);
/* 57 */             else if ((field.getType() == Double.TYPE) || (field.getType() == Double.class))
/* 58 */               sqlQuery.addScalar(getName, DoubleType.INSTANCE);
/* 59 */             else if ((field.getType() == Float.TYPE) || (field.getType() == Float.class))
/* 60 */               sqlQuery.addScalar(getName, FloatType.INSTANCE);
/* 61 */             else if ((field.getType() == Boolean.TYPE) || (field.getType() == Boolean.class))
/* 62 */               sqlQuery.addScalar(getName, BooleanType.INSTANCE);
/* 63 */             else if (field.getType() == String.class)
/* 64 */               sqlQuery.addScalar(getName, StringType.INSTANCE);
/* 65 */             else if (field.getType() == Date.class)
/* 66 */               sqlQuery.addScalar(getName, TimestampType.INSTANCE);
/* 67 */             else if (field.getType() == BigDecimal.class)
/* 68 */               sqlQuery.addScalar(getName, BigDecimalType.INSTANCE);
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\admin\Desktop\shopun.jar
 * Qualified Name:     p2p.dao.base.bean.ScalarBean
 * JD-Core Version:    0.6.0
 */