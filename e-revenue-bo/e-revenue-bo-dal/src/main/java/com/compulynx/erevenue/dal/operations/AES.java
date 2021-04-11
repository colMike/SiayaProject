/*     */ package com.compulynx.erevenue.dal.operations;
/*     */ 
/*     */ import java.security.Key;
/*     */ import java.security.MessageDigest;
/*     */ import java.util.Arrays;
/*     */ import java.util.Scanner;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AES
/*     */ {
/*     */   private static final String ALGORITHM = "AES";
/*     */   private static Scanner scanner;
/*     */   
/*     */   public static void main(String[] args) {
/*  25 */     print("1) For Encrypt, 2) For Decrypt>>");
/*  26 */     scanner = new Scanner(System.in);
/*  27 */     String sAction = scanner.nextLine();
/*     */     
/*  29 */     if (sAction.toLowerCase().equalsIgnoreCase("1")) {
/*  30 */       print("Enter String To Encrypt");
/*  31 */       scanner = new Scanner(System.in);
/*  32 */       String enStr = scanner.nextLine();
/*     */       
/*     */       try {
/*  35 */         print("Encrypted String Is >> " + encrypt(enStr));
/*  36 */       } catch (Exception ex) {
/*  37 */         ex.printStackTrace();
/*     */       } 
/*  39 */     } else if (sAction.toLowerCase().equalsIgnoreCase("2")) {
/*  40 */       print("Enter String To decrypt");
/*  41 */       scanner = new Scanner(System.in);
/*  42 */       String decStr = scanner.nextLine();
/*     */       try {
/*  44 */         print("Decrypted String in main>> " + decrypt(decStr));
/*  45 */       } catch (Exception ex) {
/*  46 */         ex.printStackTrace();
/*     */       } 
/*     */     } else {
/*  49 */       print("Invalid Action Selected");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void print(String value) {
/*  54 */     System.out.println(value);
/*     */   }
/*     */   
/*     */   public static String encrypt(String input) throws Exception {
/*     */     try {
/*  59 */       Cipher cipher = Cipher.getInstance("AES");
/*  60 */       cipher.init(1, generateKey());
/*  61 */       return encodeString(cipher.doFinal(input.getBytes()));
/*  62 */     } catch (Exception e) {
/*  63 */       e.printStackTrace();
/*  64 */       return "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String decrypt(String input) throws Exception {
/*     */     try {
/*  70 */       Cipher cipher = Cipher.getInstance("AES");
/*  71 */       cipher.init(2, generateKey());
/*  72 */       String value = new String(cipher.doFinal(decodeString(input)));
/*  73 */       return value;
/*  74 */     } catch (Exception e) {
/*  75 */       e.printStackTrace();
/*  76 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Key generateKey() throws Exception {
/*     */     try {
/*  83 */       byte[] keyval = "@compulynx#54321".getBytes();
/*  84 */       MessageDigest digest = MessageDigest.getInstance("SHA-1");
/*  85 */       keyval = digest.digest(keyval);
/*  86 */       keyval = Arrays.copyOf(keyval, 16);
/*  87 */       Key key = new SecretKeySpec(keyval, "AES");
/*  88 */       return key;
/*  89 */     } catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String encodeString(byte[] input) {
/*  96 */     return Base64.encodeBase64URLSafeString(input);
/*     */   }
/*     */   
/*     */   public static byte[] decodeString(String output) {
/* 100 */     return Base64.decodeBase64(output);
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\operations\AES.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */