/*     */ package com.compulynx.erevenue.dal.operations;
/*     */ 
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class DBOperations {
/*     */   public static void DisposeSql(Connection connection, PreparedStatement statement, ResultSet set) {
/*     */     try {
/*  12 */       if (set != null) {
/*  13 */         set.close();
/*  14 */         set = null;
/*     */       } 
/*  16 */       if (statement != null) {
/*  17 */         statement.close();
/*  18 */         statement = null;
/*     */       } 
/*  20 */       if (connection != null) {
/*  21 */         connection.close();
/*  22 */         connection = null;
/*     */       } 
/*  24 */     } catch (SQLException e) {
/*  25 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void DisposeSql(Connection connection, CallableStatement statement, ResultSet set) {
/*     */     try {
/*  32 */       if (set != null) {
/*  33 */         set.close();
/*  34 */         set = null;
/*     */       } 
/*  36 */       if (statement != null) {
/*  37 */         statement.close();
/*  38 */         statement = null;
/*     */       } 
/*  40 */       if (connection != null) {
/*  41 */         connection.close();
/*  42 */         connection = null;
/*     */       } 
/*  44 */     } catch (SQLException e) {
/*  45 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(Connection connection, CallableStatement statement) {
/*     */     try {
/*  51 */       if (statement != null) {
/*  52 */         statement.close();
/*  53 */         statement = null;
/*     */       } 
/*  55 */       if (connection != null) {
/*  56 */         connection.close();
/*  57 */         connection = null;
/*     */       } 
/*  59 */     } catch (SQLException e) {
/*  60 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(Connection connection, PreparedStatement statement) {
/*     */     try {
/*  66 */       if (statement != null) {
/*  67 */         statement.close();
/*  68 */         statement = null;
/*     */       } 
/*  70 */       if (connection != null) {
/*  71 */         connection.close();
/*  72 */         connection = null;
/*     */       } 
/*  74 */     } catch (SQLException e) {
/*  75 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(PreparedStatement statement) {
/*     */     try {
/*  81 */       if (statement != null) {
/*  82 */         statement.close();
/*  83 */         statement = null;
/*     */       } 
/*  85 */     } catch (SQLException e) {
/*  86 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(Connection connection) {
/*     */     try {
/*  92 */       if (connection != null) {
/*  93 */         connection.close();
/*  94 */         connection = null;
/*     */       } 
/*  96 */     } catch (SQLException e) {
/*  97 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(Connection connection, ResultSet set) {
/*     */     try {
/* 103 */       if (set != null) {
/* 104 */         set.close();
/* 105 */         set = null;
/*     */       } 
/* 107 */       if (connection != null) {
/* 108 */         connection.close();
/* 109 */         connection = null;
/*     */       } 
/* 111 */     } catch (SQLException e) {
/* 112 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(PreparedStatement statement, ResultSet set) {
/*     */     try {
/* 118 */       if (set != null) {
/* 119 */         set.close();
/* 120 */         set = null;
/*     */       } 
/* 122 */       if (statement != null) {
/* 123 */         statement.close();
/* 124 */         statement = null;
/*     */       } 
/* 126 */     } catch (SQLException e) {
/* 127 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DisposeSql(ResultSet set) {
/*     */     try {
/* 133 */       if (set != null) {
/* 134 */         set.close();
/* 135 */         set = null;
/*     */       } 
/* 137 */     } catch (SQLException e) {
/* 138 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\operations\DBOperations.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */