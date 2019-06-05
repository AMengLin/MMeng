/**
 * 针对至店铺注册，编辑页面
 * 1、获取初始信息（商铺分类，所属区域的列表信息，因为下拉列表，所以需要动态获取）填充到表单中
 * 2、点击提交时，获取表单的信息，通过ajax提交到后台
 */
$(function(){
	//获取店铺的初始信息，商铺分类，所属区域的列表信息
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	//注册店铺
	var registerUrl = '/o2o/shopadmin/registershop'
	//获取店铺信息的方法
	function getShopInitInfo(){
		//initUrl:路径   data:后台通过json传过来的数据
		$.getJSON(initUrl,function(data){
			//数据返回成功
			if(data.success){
				//初始商铺分类变量
				var tempHtml = '';
				//初始所属区域分类变量
				var tempAreaHtml = '';
				//遍历从后台获取到的所属区域列表数据,该遍历写法是新版jq的写法
				data.shopCategoryList.map(function(item,index){
					//遍历后，把shopCategoryId及shopCategoryName填充进去
					tempHtml+='<option data-id="' + item.shopCategoryId + '">' 
					+ item.shopCategoryName + '</option>';
					//遍历后，把shopCategoryName填充进去
				})
				data.areaList.map(function(data,index){
					//遍历后，把areaId及areaName填充进去
					tempAreaHtml+='<option data-id="' + item.areaId + '">'
					+ item.areaName + '</option>';
				})
				//获取到数据后，把数据填充到前台页面上
			}
		})
	}
})