/*    */ package com.compulynx.erevenue.bo.servlet;
/*    */ 
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.servlet.ServletContextListener;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyServletContextListener
/*    */   implements ServletContextListener
/*    */ {
/*    */   @Resource(name = "jdbc/erevenueDS")
/*    */   private DataSource ds;
/*    */   
/*    */   public void contextInitialized(ServletContextEvent servletContextEvent) {
/* 23 */     System.out.println("contextInitialized");
/* 24 */     ServletContext context = servletContextEvent.getServletContext();
/* 25 */     context.setAttribute("ds", this.ds);
/*    */   }
/*    */ 
/*    */   
/*    */   public void contextDestroyed(ServletContextEvent servletContextEvent) {
/* 30 */     System.out.println("contextDestroyed");
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\servlet\MyServletContextListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */