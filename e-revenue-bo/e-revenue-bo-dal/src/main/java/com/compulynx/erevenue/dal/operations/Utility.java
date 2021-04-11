/*    */ package com.compulynx.erevenue.dal.operations;

import java.io.FileInputStream;
import java.util.Properties;

import com.compulynx.erevenue.models.CompasProperties;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Utility
/*    */ {
/*  9 */   private static final String[] lowNames = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   private static final String[] tensNames = new String[] { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
/*    */ 
/*    */   
/* 17 */   private static final String[] bigNames = new String[] { "thousand", "million", "billion" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String convertNumberToWords(int n) {
/* 26 */     if (n < 0) {
/* 27 */       return "minus " + convertNumberToWords(-n);
/*    */     }
/* 29 */     if (n <= 999) {
/* 30 */       return convert999(n);
/*    */     }
/* 32 */     String s = null;
/* 33 */     int t = 0;
/* 34 */     while (n > 0) {
/* 35 */       if (n % 1000 != 0) {
/* 36 */         String s2 = convert999(n % 1000);
/* 37 */         if (t > 0) {
/* 38 */           s2 = s2 + " " + bigNames[t - 1];
/*    */         }
/* 40 */         if (s == null) {
/* 41 */           s = s2;
/*    */         } else {
/* 43 */           s = s2 + "  " + s;
/*    */         } 
/*    */       } 
/* 46 */       n /= 1000;
/* 47 */       t++;
/*    */     } 
/* 49 */     return s;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String convert999(int n) {
/* 54 */     String s1 = lowNames[n / 100] + " hundred";
/* 55 */     String s2 = convert99(n % 100);
/* 56 */     if (n <= 99)
/* 57 */       return s2; 
/* 58 */     if (n % 100 == 0) {
/* 59 */       return s1;
/*    */     }
/* 61 */     return s1 + " " + s2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String convert99(int n) {
/* 67 */     if (n < 20) {
/* 68 */       return lowNames[n];
/*    */     }
/* 70 */     String s = tensNames[n / 10 - 2];
/* 71 */     if (n % 10 == 0) {
/* 72 */       return s;
/*    */     }
/* 74 */     return s + "-" + lowNames[n % 10];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static double calculate(double p, double r, int n) {
/* 85 */     double amount = p * Math.pow(1.0D + r, n);
/*    */     
/* 87 */     double interest = amount - p;
/*    */     
/* 89 */     System.out.println("Compond Interest is " + interest);
/*    */     
/* 91 */     return interest;
/*    */   }

    public CompasProperties getCompasProperties(String param) {
    String path = System.getProperty("catalina.base") + "/conf/application.properties";
    Properties properties = new Properties();

    try {
        FileInputStream file = new FileInputStream(path.trim());
        properties.load(file);
        file.close();
        return new CompasProperties(properties.getProperty(param).trim());
    } catch (Exception var5) {
        var5.printStackTrace();
        return null;
    }
}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\operations\Utility.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */