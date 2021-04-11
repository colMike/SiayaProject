/**
 * Transaction Held Angular Module
 */
'use strict';
angular.module('app.rptSummary', []).controller("rptSummaryCtrl",["$scope","$filter","serviceTxnSvc","summarySvc","$summaryValid","$rootScope","blockUI","logger","$location","$http","$window","memStatementSvc",function($scope, $filter, summarySvc, serviceTxnSvc,$summaryValid, $rootScope, blockUI, logger, $location,$http, $window,memStatementSvc) {
	$scope.transMemList = [];
	$scope.mem = [];
	$scope.eType = "P";
	$scope.serviceSelect="";

	$scope.services=[
	                 {'id':'s','name':'Service'},
	                 {'id':'M','name':'Market'},
	                 {'id':'u','name':'User'},
	                 {'id':'d','name':'Device'},
	                 {'id':'w','name':'Ward'},
	                 {'id':'sb','name':'SubCounty'}
	                 ];

	$scope.loadParentSerData = function() {
		$scope.services = [];
		serviceTxnSvc.GetParentServices($scope).success(
				function(response) {
					return $scope.services = response;
				});
	}
	//$scope.loadParentSerData();
	$scope.exportPdfReport = function(mem) {
		if (!$summaryValid($scope.serviceSelect)) {
			logger.logWarning("Opss! You may have skipped specifying the filter type. Please try again.")
			return false;
		}
		if (!$summaryValid(mem.FromDt)) {
			logger.logWarning("Opss! You may have skipped specifying the From Date. Please try again.")
			return false;
		}
		else if (!$summaryValid(mem.ToDt)) {
			logger.logWarning("Opss! You may have skipped specifying the To Date. Please try again.")
			return false;
		}


		var FromDt = $filter('date')(mem.FromDt, 'yyyy-MM-dd');
		var ToDt = $filter('date')(mem.ToDt, 'yyyy-MM-dd');

		if($scope.serviceSelect=='s'){
			$scope.url = '/erevenue/reports?type=S&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='M'){
			$scope.url = '/erevenue/reports?type=M&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='u'){
			$scope.url = '/erevenue/reports?type=u&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='d'){
			$scope.url = '/erevenue/reports?type=d&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='gs'){
			$scope.url = '/erevenue/reports?type=gs&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='w'){
			$scope.url = '/erevenue/reports?type=w&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='sb'){
			$scope.url = '/erevenue/reports?type=sb&eType=P&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}

	}
	$scope.exportExcelReport = function(mem) {
		if (!$summaryValid($scope.serviceSelect)) {
			logger.logWarning("Opss! You may have skipped specifying the filter type. Please try again.")
			return false;
		}
		if (!$summaryValid(mem.FromDt)) {
			logger.logWarning("Opss! You may have skipped specifying the From Date. Please try again.")
			return false;
		}
		if (!$summaryValid(mem.ToDt)) {
			logger.logWarning("Opss! You may have skipped specifying the To Date. Please try again.")
			return false;
		}

		var FromDt = $filter('date')(mem.FromDt, 'yyyy-MM-dd');
		var ToDt = $filter('date')(mem.ToDt, 'yyyy-MM-dd');

		if($scope.serviceSelect=='s'){
			$scope.url = '/erevenue/reports?type=S&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='M'){
			$scope.url = '/erevenue/reports?type=M&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}

		if($scope.serviceSelect=='u'){
			$scope.url = '/erevenue/reports?type=u&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='d'){
			$scope.url = '/erevenue/reports?type=d&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='gs'){
			$scope.url = '/erevenue/reports?type=gs&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='w'){
			$scope.url = '/erevenue/reports?type=w&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}
		if($scope.serviceSelect=='sb'){
			$scope.url = '/erevenue/reports?type=sb&eType=E&FrDt='+ FromDt + '&ToDt=' + ToDt;
		}

	}
	$scope.loadAmpuntDetail=function(){
		memStatementSvc.GetCurrentDetailedTxn().success(function (response) {
			$scope.tempAmountList = response;
			if($scope.tempAmountList.length>0){

				for (var i = 0; i <= $scope.tempAmountList.length - 1; i++) {

					$scope.totalAmount=$scope.tempAmountList[i].totalAmount//+$scope.tempAmountList[i].invalidAmount;
					$scope.voideAmount=$scope.tempAmountList[i].voidAmount;
					$scope.invalidAmount=$scope.tempAmountList[i].invalidAmount;
					//	 $scope.netAmount=(($scope.totalAmount-$scope.voideAmount)-$scope.invalidAmount);
					$scope.netAmount=$scope.tempAmountList[i].netAmount;
					$scope.billNo=$scope.tempAmountList[i].billNo;
				}
			}
		})
	}
	$scope.loadAmpuntDetail();

} ]).factory('summarySvc', function($http) {
	var shpMemStatement = {};

	return shpMemStatement;
}).factory('$summaryValid', function() {
	return function(valData) {
		if (angular.isUndefined(valData))
			return false;
		else {
			if (valData == null)
				return false;
			else {
				if (valData.toString().trim() == "")
					return false;
				else
					return true;
			}
		}
		return false;
	};

});
