/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.MarketBalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.Market;
/*    */ import com.compulynx.erevenue.models.Ward;
/*    */ import java.util.List;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Path("/market")
/*    */ public class MarketSvc
/*    */ {
/*    */   @Autowired
/*    */   MarketBalImpl marketBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtMarkets")
/*    */   public Response GetMarkets() {
/* 40 */     List<Market> markets = this.marketBal.GetAllMarkets();
/* 41 */     return Response.status(200).entity(markets).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtActiveWards")
/*    */   public Response GetActiveWards() {
/* 48 */     List<Ward> wards = this.marketBal.GetActiveWards();
/* 49 */     return Response.status(200).entity(wards).build();
/*    */   }
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updMarket")
/*    */   public Response UpdateMarket(Market market) {
/* 56 */     ErevenueResponse response = this.marketBal.UpdateMarket(market);
/* 57 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\MarketSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */