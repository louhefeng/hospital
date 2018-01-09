/*    */ package com.hospi.exception;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ public class DataAccessException extends RuntimeException
/*    */ {
/*    */   private Throwable cause;
/*    */ 
/*    */   public DataAccessException(String msg, Throwable ex)
/*    */   {
/* 15 */     super(msg);
/* 16 */     this.cause = ex;
/*    */   }
/*    */ 
/*    */   public Throwable getCause() {
/* 20 */     return this.cause == null ? this : this.cause;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 24 */     String message = super.getMessage();
/* 25 */     Throwable cause = getCause();
/* 26 */     if (cause != null) {
/* 27 */       message = message + ";UnChecked异常 ： " + cause;
/*    */     }
/* 29 */     return message;
/*    */   }
/*    */ 
/*    */   public void printStackTrace(PrintStream ps) {
/* 33 */     if (getCause() == null) {
/* 34 */       super.printStackTrace(ps);
/*    */     }
/*    */     else {
/* 37 */       ps.println(this);
/* 38 */       getCause().printStackTrace(ps);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void printStackTrace(PrintWriter pw) {
/* 43 */     if (getCause() == null) {
/* 44 */       super.printStackTrace(pw);
/*    */     } else {
/* 46 */       pw.println(this);
/* 47 */       getCause().printStackTrace(pw);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void printStackTrace() {
/* 52 */     printStackTrace(System.err);
/*    */   }
/*    */ }
 