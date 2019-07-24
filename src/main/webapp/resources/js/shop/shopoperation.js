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
	//js文件被加载的时候，调用getShopInitInfo方法，去后台获取区域信息和店铺类别信息，加载到前端页面上
	getShopInitInfo();
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
				data.areaList.map(function(item,index){
					//遍历后，把areaId及areaName填充进去
					tempAreaHtml+='<option data-id="' + item.areaId + '">'
					+ item.areaName + '</option>';
				})
				//获取到数据后，把数据填充到前台页面上
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
		//创建提交数据的方式
		$('#submit').click(function(){
			//获取表单数据
			//创建shop实体，也就是json对象
			var shop = {};
			shop.shopName = $('#shop-name').val();
			shop.shopAddr = $('#shop-addr').val();
			shop.phone = $('#shop-phone').val();
			shop.shopDesc = $('#shop-desc').val();
			//双重否定定于肯定，当前选择的下拉属性
			shop.shopCategory = {
					shopCategoryId : $('#shop-category').find('option').not(function() {
						return !this.selected;
					}).data('id')
				};
				// 选择选定好的区域信息
				shop.area = {
					areaId : $('#area').find('option').not(function() {
						return !this.selected;
					}).data('id')
				};
			//获取文件流
			var shopImg = $('#shop-img')[0].files[0];
			//创建表单对象，用于接收参数传递到后台
			var formData = new FormData();
			//添加图片流到表单中
			formData.append('shopImg',shopImg);
			//将shop json对象转成字符流保存至表单对象key为shopStr的键值对里
			formData.append('shopStr',JSON.stringify(shop));
			//获取验证码
			var verifyCodeActual = $('#j_captcha').val();
			if(!verifyCodeActual){
				$.toast("请输入验证码");
				return;
			}else{
				formData.append('verifyCodeActual',verifyCodeActual);
			}
			//将数据传到后台处理
			$.ajax({
				
				url : registerUrl,
				type : 'post',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success:function(data){
					if(data.success){
						$.toast("提交成功!");
					}else{
						$.total("提交失败!"+data.errMsg);
					}
					$('#captcha_img').click();
				}
			});
		});
	}
})