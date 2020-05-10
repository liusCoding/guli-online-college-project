//温馨提示弹框
var tipNew = function () {
    $(".new-tips-close").bind("click", function() {$(".tip-new-warp").remove();});
}
//移动端导航显示与隐藏
var wmNavFun = function() {
    var wmBtn = $(".mw-nav-btn"),
    	hmMask = $(".h-mobile-mask"),
        wH = $(window).height();
    $(".head-mobile").css("height", wH+"px");
    wmBtn.click(function() {
        if (!wmBtn.hasClass("mw-tap")) {
            $(this).addClass("mw-tap");
            $("html").addClass("active");
            hmMask.show().css("opacity","1");
        } else {
            $(this).removeClass("mw-tap");
            $("html").removeClass("active");
            hmMask.css("opacity","0").hide();
        };
    });
    hmMask.click(function() {
    	if(!hmMask.is(":hidden")) {
    		wmBtn.removeClass("mw-tap");
            $("html").removeClass("active");
            hmMask.css("opacity","0").hide();
    	}
    });
};
//向上滚动方法
var upSlideFun = function(od) {
    var _upWrap = $(od),
          _sTime = 5000,
          _moving;
    _upWrap.hover(function() {
        clearInterval(_moving);
    }, function() {
        _moving = setInterval(function() {
            var _mC = _upWrap.find("li:first");
            var _mH = _mC.height();
            _mC.animate({"margin-top" : -_mH + "px"}, 600, function() {
                _mC.css("margin-top", 0).appendTo(_upWrap);
            });
        }, _sTime);
    }).trigger("mouseleave");
}
//滚动定位
function cTabFun(op) {
    var cTab = op;
    cTab.each(function() {
        var _this = $(this),
            _tName = _this.attr("name");
        _this.click(function() {
        	_this.parent().parent().find("a").removeClass("current");
            _this.addClass("current").siblings().removeClass("current");
            $("html,body").animate({"scrollTop":$("."+_tName+"-content").offset().top}, 500);
        })
    })
}
// 课程分享
function shareShow() {
    //share show
    $(".kcShare").hover(function() {
        var _this = $(this);
        _this.stop().animate({"width" : "205px"}, 200);
        _this.siblings("span").css({"width" : "60px"});
        _this.children("#bdshare").stop().animate({"right" : "0"}, 200);
    }, function() {
        var _this = $(this);
        _this.stop().animate({"width" : "48px"}, 200);
        _this.children("#bdshare").stop().animate({"right" : "-160px"}, 200);
    });
}
//tree menu
function treeMenu() {
    //一级目录
    $("#lh-menu>ul>li>a").each(function() {
        var _this = $(this);
        _this.click(function() {
            if (_this.siblings("ol").is(":hidden")) {
                _this.siblings("ol").slideDown(150);
                _this.addClass("current-1");
                _this.parent("li").siblings().children("ol").slideUp(150);
                _this.parent("li").siblings().children("a:first").removeClass("current-1");
            } else {
                _this.siblings("ol").slideUp(150);
                _this.removeClass("current-1");
            };
        });
    })
}
//答疑回复
function replyFun() {
    var hfElem = '<section class="n-reply-wrap"><fieldset>' +
                 '<textarea name=""></textarea>' +
                 '</fieldset><p class="of mt5 tar pl10 pr10">' +
                 '<span class="fl"><tt class="c-red">回复内容不能为空！</tt></span>'+
                 '<u class="hand mr10 qxBtn c-999">取消</u>' +
                 '<a href="javascript: void(0)" title="回复" class="lh-reply-btn" onclick="addReply(this)">回复</a>' +
                 '</p></section>';
    $(".question-list>ul>li").each(function() {
        var _this = $(this),
            qxFun = function() {
                //_this.find(".n-reply").find(".n-reply-wrap").remove();
                _this.find(".n-reply").slideUp(150);
            };
        _this.find(".noter-dy").click(function() {
            if (_this.find(".n-reply").is(":hidden")) {
               // _this.find(".n-reply").slideDown(150).prepend(hfElem);
            	 _this.find(".n-reply").slideDown(150);
            } else {
                qxFun();
            };
        });
        _this.find(".qxBtn").bind("click", function() {qxFun();});
    });
}
//选项卡公共方法
function cardChange(oTitle, oCont, current, callback) {
    var oTitle = $(oTitle),
        oCont = $(oCont),
        _index;
    oTitle.click(function() {
        _index = oTitle.index(this);
        $(this).addClass(current).siblings().removeClass(current);
        oCont.eq(_index).show().siblings().hide();
        if(typeof callback === "function") {callback();};
    }).eq(0).click();
}
// scrollLoad 滚动响应加载调用图片方法
var scrollLoad = (function (options) {
    var defaults = (arguments.length == 0) ? { src: 'xSrc', time: 500} : { src: options.src || 'xSrc', time: options.time ||500};
    var camelize = function (s) {
        return s.replace(/-(\w)/g, function (strMatch, p1) {
            return p1.toUpperCase();
        });
    };
    this.getStyle = function (element, property) {
        if (arguments.length != 2) return false;
        var value = element.style[camelize(property)];
        if (!value) {
            if (document.defaultView && document.defaultView.getComputedStyle) {
                var css = document.defaultView.getComputedStyle(element, null);
                value = css ? css.getPropertyValue(property) : null;
            } else if (element.currentStyle) {
                value = element.currentStyle[camelize(property)];
            }
        }
        return value == 'auto' ? '' : value;
    };
    var _init = function () {
    	 if(document.getElementById("aCoursesList")==null){
         	return;
         }
        var offsetPage = window.pageYOffset ? window.pageYOffset : window.document.documentElement.scrollTop,	//滚动条滚动高度
            offsetWindow = offsetPage + Number(window.innerHeight ? window.innerHeight : document.documentElement.clientHeight),
            docImg = document.getElementById("aCoursesList").getElementsByTagName("img"),			//通过ID查找获取图片节点
            _len = docImg.length;
        if (!_len) return false;
        for (var i = 0; i < _len; i++) {
            var attrSrc = docImg[i].getAttribute(defaults.src),
                o = docImg[i], tag = o.nodeName.toLowerCase();
            if (o) {
                postPage = o.getBoundingClientRect().top + window.document.documentElement.scrollTop + window.document.body.scrollTop;
                postWindow = postPage + Number(this.getStyle(o, 'height').replace('px', ''));	
                if ((postPage > offsetPage && postPage < offsetWindow) || (postWindow > offsetPage && postWindow < offsetWindow)) {	//判断元素始终在可见区域内
                    if (tag === "img" && attrSrc !== null) {
                        o.setAttribute("src", attrSrc);
                    }
                    o = null;
                }
            }
        };
        window.onscroll = function () {
            setTimeout(function () {
                _init();
            }, defaults.time);
        }
    };
    return _init();
});
//公共弹框
/*******
 *** @param dTitle : 弹框标题名称;
 *** @param index : 调用弹框的类型; 
 *** index == 0 : 支付结果反馈弹出框;
 *** index == 1 : 正确提示弹出框;
 *** index == 2 : 错误提示弹出框;  
 *** index == 3 : 确认提示弹出框；
*/
function dialog(dTitle,msg,index,url) {
	 $("#tisbutt,.dClose,#qujiao").click();
    var oBg = $('<div class="bMask"></div>').appendTo($("body")),
        dialogEle = $('<div class="dialogWrap"><div class="dialog-ele"><h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span class="d-s-head-txt">'+dTitle+'</span></h4><div class="of"><div id="dcWrap" class="mt20 mb20 ml20 mr20 "></div></div></div></div>').appendTo($("body"));
    var dCont = [
            "<div class='d-tips-1'><em class='pa d-t-icon-3'></em><p class='fsize14 c-666'>"+msg+"</p><div class='tac mt30'><a href='javascript:void(0)' title='' class='order-submit dClose'>确定</a></div></div>",
            "<div class='d-tips-2'><em class='pa d-t-icon-2'></em><p class='fsize14 c-666'>"+msg+"</p><div class='tac mt30'><a href='javascript:void(0)' title='' class='order-submit dClose'>确定</a></div></div>",
            "<div class='d-tips-3'><em class='pa d-t-icon-3'></em><p class='fsize14 c-666'>"+msg+"</p><div class='tac mt30'><a href='"+url+"' title='' class='order-submit'>确定</a><a href='javascript:void(0)' title='' class='goBack-btn ml10 dClose'>取消</a></div></div>",
            "<div class='d-tips-4><em class='pa d-t-icon-1'></em><p class='fsize14 c-666'>你选择使用工商银行网银进行在线支付，在你支付成功后我们将尽快发送给你购买的课程。祝你学习愉快！</p><div class='tac mt20'><a href='' title='' class='blue-btn mr10'>支付过程发生问题</a><a href='' title='' class='blue-btn ml10'>已成功完成支付</a></div><p class='tar mt20 c-666'>如有疑问请询问客服：400-6587-777</p></div>",
            "<div class='d-tips-5'><em class='pa d-t-icon-4'></em><p class='fsize14 c-666 disIb ml5'>恭喜，你已成功激活。现在你可以：</p><div class='tac mt20 mb10'><a href='' title='' class='blue-btn mr10'>登录网页</a><a href='' title='' class='blue-btn'>查看课程</a></div></div>",
            "<div class='d-tips-6'>"+msg+"</div>"
        ];
    $("#dcWrap").html(dCont[index]);
    var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
        dH = dialogEle.height(),
        dW = dialogEle.width(),
        dHead = $(".dialog-ele>h4");
    dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
    dHead.css({"width" : (dW-"12")}); //ie7下兼容性;
    $("#tisbutt,.dClose,#qujiao").bind("click", function() {dialogEle.remove();oBg.remove();});
    
    
}


var ajaxUrl;//记录上次ajax分页的url
var ajaxparameters;//记录上次ajax分页的参数
var ajaxcallBack;
/*
 * ajax分页方法获取数据
 */
function ajaxPage(url,parameters,pageNum,callBack){
	ajaxUrl = url;
	ajaxparameters = parameters;
	ajaxcallBack=callBack;
	parameters='page.currentPage='+pageNum+''+parameters;
	$.ajax({
		type : "POST",
		dataType : "text",
		url:baselocation+url,
		data : parameters,
		cache : true,
		async : false,
		success : callBack
	});
}
//点击分页
function goPageAjax(pageNum){
	ajaxPage(ajaxUrl,ajaxparameters,pageNum,ajaxcallBack);
}

/*
 * 公共点赞
 * targetId 点赞的目标id
 * type 点赞类型 1问答点赞 2问答评论点赞
 * obj 当前标签对象
 */
function addPraise(targetId,type,obj){
	if(isLogin()){
		$.ajax({
			url:baselocation + "/praise/ajax/add",
			data:{
				"praise.targetId":targetId,
				"praise.type":type
			},
			type:"post",
			dataType:"json",
			async:true,
			success:function(result){
				if(result.success==true){
					dialog('提示',"点赞成功",0);
					//点赞数加一
					var praiseNum = $(".addPraise"+targetId+"_"+type).html();
					$(".addPraise"+targetId+"_"+type).html(praiseNum*1+1);
					
					//修改点赞数
					var priaseCount=parseInt($(obj).children("span").html());
					$(obj).children("span").html(priaseCount+1);
				}else{
					dialog('提示',result.message,1);
				}
			}
		})
	}else{
		//先关闭 弹出
		 $("#tisbutt,.dClose,#qujiao").click();
		lrFun();
	}
}

/**
 * 查询未读消息
 */
function queryUnReadNum(){
	if(!isLogin()){
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url:baselocation+"/uc/ajax/queryUnReadLetter",
		cache : true,
		async : true,
		success : function(result) {
			var letter = result.entity;
			if(letter==null){
				return;
			}
			//未读系统消息数
			var systemNum = letter.SMNum;
			//未读站内信数
			var letterNum = letter.mNum;
			
			//总未读消息数
			unReadNum = letter.unReadNum;
			if(unReadNum!=0){
				$("#headerMsgCountId").next().show();
			}
			$("#headerMsgCountId").attr("title",unReadNum+"条未读消息");
			//$("#headerMsgCountId").html(unReadNum);
		}
	});
}

/*
* 快捷登录/注册
* type 1 头部点击登陆 2 注册 选中
*/
function lrFun(type) {
    var oBg = $('<div class="bMask"></div>').appendTo($("body")),
        dialogEle = $('<div class="dialogWrap" style="position: absolute;"><div class="dialog-ele"><h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span id="d-s-head-tab" class="d-s-head-tab"><a href="javascript:void(0)" class="current">登录</a><a href="javascript:void(0)">注册</a></span></h4><div class="of"><div id="lrEleWrap" class="mt10 mb20 ml20"></div></div></div></div>').appendTo($("body")),
        rlEle = [
            '<div id="d-s-head-cont" class="lrWrap">'+
                '<section class="dis e-login-ele">'+
                    '<div class="mt10">'+
                        '<p class="e-l-jy"></p>'+
                    '</div>'+
                    '<div>'+
                        '<ol class="e-login-options">'+
                            '<li>'+
                                '<input id="u-email" type="text" placeholder="请输入登录邮箱"  name="" value="" onkeyup="$(this).next().html(\'\');">'+
                                '<p class="lr-tip-wrap"></p>'+
                            '</li>'+
                            '<li>'+
                                '<input id="u-password" type="password" placeholder="请输入密码"  name="" value="" onkeyup="$(this).next().html(\'\');">'+
                                '<p class="lr-tip-wrap"></p>'+
                            '</li>'+
                        '</ol>'+
                        /*'<section class="hLh30 of pl10"><span class="fr"><a href="/uc/register" class="c-master fsize12">没有账号？去注册→</a></span>'+*/
                        '<section class="hLh30 of pl10">'+
                        '<span class="fl"><label class="hand c-999 vam"><input type="checkbox" style="vertical-align: -2px;" id="autoThirty">自动登录</label><a class="vam ml10 c-blue" title="" href="/front/passwordRecovery">忘记密码?</a></span></section>'+
                        '<section class="mt20 tac">'+
                            '<a href="javascript:void(0)" title="登 录" class="e-login-btn" onclick="dialogLogin('+type+')">登 录</a>'+
                        '</section>'+
                      /*  '<section class="mt20 sf-lr-wrap tac">'+
                            '<h6 class="hLh20 mb15"><span class="c-666 fsize14">第三方快捷登录</span></h6>'+
                            '<a href="" title="QQ登录" class="qq-sf">&nbsp;</a>'+
                            '<a href="" title="微信登录" class="wx-sf">&nbsp;</a>'+
                            '<a href="" title="微博登录" class="wb-sf">&nbsp;</a>'+
                        '</section>'+  */
                    '</div>'+
                '</section>'+
                '<section class="undis e-login-ele">'+
                    '<div class="mt10">'+
                        '<p class="e-l-jy"></p>'+
                    '</div>'+
                    '<div>'+
                        '<ol class="e-login-options">'+
                            '<li>'+
                                '<input id="u-email-reg" type="text" placeholder="请输入登录邮箱"  name="" value="" onkeyup="$(this).next().html(\'\');">'+
                                '<p class="lr-tip-wrap"></p>'+
                            '</li>'+
                            '<li>'+
	                            '<input id="u-mobile-reg" type="text" placeholder="请输入用户手机号"  name="" value="" onkeyup="$(this).next().html(\'\');" maxlength="11">'+
	                            '<p class="lr-tip-wrap"></p>'+
	                        '</li>'+
                            '<li>'+
                                '<input id="u-password-reg" type="password" placeholder="请输入密码"  name="" value="" onkeyup="$(this).next().html(\'\');">'+
                                '<p class="lr-tip-wrap"></p>'+
                            '</li>'+
                            '<li>'+
                                '<input id="u-passwordre-reg" type="password" placeholder="请再输入一次密码"  name="" value="" onkeyup="$(this).next().html(\'\');">'+
                                '<p class="lr-tip-wrap"></p>'+
                            '</li>'+
                            '<li>'+
                                '<input id="u-randomcode-reg" class="fl" style="width: 100px;" type="text" placeholder="请输入验证码"  name="" value="" onkeyup="$(this).next().next().next().html(\'\');" maxlength="4">'+
                                '<a href="javascript:void(0)" title="" class="vam ml10 disIb fl"><img onclick="this.src=\'/ran/random?random=\'+Math.random()" alt="验证码，点击图片更换" src="/ran/random" width="86" height="40"></a>'+
                                '<span class="c-999 fl ml10">看不清<br><a href="javascript:void(0)" class="js-verify-refresh c-green" onclick="$(this).parent().prev().find(\'img\').click()">换一张</a></span>'+
                                '<p class="lr-tip-wrap"><span class="c-red"></p>'+
                                '<p class="clear"></p>'+
                            '</li>'+
                        '</ol>'+
                        '<section class="mt20 tac">'+
                            '<a href="javascript: void(0)" onclick="dialogRegister()" title="注 册" class="e-login-btn">注 册</a>'+
                        '</section>'+
                    /*    '<section class="mt20 sf-lr-wrap tac">'+
                            '<h6 class="hLh20 mb15"><span class="c-666 fsize14">第三方快捷登录</span></h6>'+
                            '<a href="" title="QQ登录" class="qq-sf">&nbsp;</a>'+
                            '<a href="" title="微信登录" class="wx-sf">&nbsp;</a>'+
                            '<a href="" title="微博登录" class="wb-sf">&nbsp;</a>'+
                        '</section>'+  */
                    '</div>'+
                '</section>'+
            '</div>'
        ];
    $("#lrEleWrap").html(rlEle[0]);
    placeholderFun();//placeholder兼容IE方法
    var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
        dH = dialogEle.height(),
        dW = dialogEle.width(),
        dHead = $(".dialog-ele>h4");
    dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
    dHead.css({"width" : (dW-"12")}); //ie7下兼容性;
    cardChange("#d-s-head-tab>a", "#d-s-head-cont>section", "current", function() {
    	var dH = dialogEle.height();
    	dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
    });
    $(".dClose").bind("click", function() {dialogEle.remove();oBg.remove();});
	if(type==2){
        $("#d-s-head-tab").find("a:eq(1)").click();
    }
}
//placeholder兼容IE方法
function placeholderFun() {
  //判断浏览器是否支持placeholder属性
  supportPlaceholder='placeholder'in document.createElement('input');

  //当浏览器不支持placeholder属性时，调用placeholder函数
  if(!supportPlaceholder){
  	$("input").not("input[type='password']").each(//把input绑定事件 排除password框  
          function(){  
              if($(this).val()=="" && $(this).attr("placeholder")!=""){  
                  $(this).val($(this).attr("placeholder"));  
                  $(this).focus(function(){  
                      if($(this).val()==$(this).attr("placeholder")) $(this).val("");  
                  });  
                  $(this).blur(function(){  
                      if($(this).val()=="") $(this).val($(this).attr("placeholder"));  
                  });  
              }  
      });  
      //对password框的特殊处理
      var pwdField    = $("input[type=password]");
      pwdField.each(function() {
    	  var _this = $(this),
    	   	  index = _this.index(),
    	      pwdVal = _this.attr('placeholder');
    	  _this.after('<input id="pwdPlaceholder'+index+'" type="text" value='+pwdVal+' autocomplete="off" />');
    	  var pwdFieldColn = _this.next();  
    	  pwdFieldColn.show();  
          _this.hide();
            
          pwdFieldColn.focus(function(){  
        	  pwdFieldColn.hide();  
              _this.show();  
              _this.focus();  
          });  
            
          _this.blur(function(){  
              if(_this.val() == '') {  
            	  pwdFieldColn.show();  
                  _this.hide();  
              }  
          });
      });
  }
}
//右侧浮动 在线客服，官方微信， 返回顶部
function goTopFun() {
    var _gt = $("#goTopBtn");
    _gt.bind("click", function() {
        $("html,body").animate({"scrollTop" : 0}, 500);
    })
    var goTop = function() {
        var sTop = $(document).scrollTop();
        (sTop > 120) ? _gt.fadeIn(50) : _gt.fadeOut(50);
    }
    $(window).bind("scroll" , goTop);
}

/**
 * 执行登录
 */
function dialogLogin(type){
	var userName=$("#u-email").val();
    var pwd = $("#u-password").val();
    var autoThirty=$("#autoThirty").prop("checked")
    $("#u-email").next().html('');
    $("#u-password").next().html('');
    $(".e-l-jy").html('');
    if(userName==""||userName==null){
    	$("#u-email").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入正确的邮箱！</span>');
        return false;
    }
    if(pwd==""||pwd==null){
    	$("#u-password").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入正确的密码！</span>');
        return false;
    }
	$.ajax({
		url:baselocation+'/uc/login',
		type:'post',
		dataType:'json',
		data:{
			"account":userName,
			"password":pwd,
			"ipForget":autoThirty
		},
		success:function(result){
			if(result.success==false){
				$(".e-l-jy").html('<font class="fsize12 c-orange">'+result.message+'</font>');
			}else{
				if(type==1){
					window.location.href="/uc/index";
				}else{
					window.location.reload();
				}
			}
		},
		error:function(error){
			alert("系统繁忙，请稍后再操作！");
		}
	});
}

/**
 * 注册新用户 
 */
function dialogRegister() {
	$(".e-l-jy").html('');
	var emailVal=$("#u-email-reg").val();
	if(emailVal==""){//验证邮箱是否为空
		$("#u-email-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入邮箱！</span>');
		return;
	}
	var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
	if(reg.test(emailVal)==false){//格式不正确
		$("#u-email-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入正确的邮箱！</span>');
		return;
	};
	
	var mobileVal=$("#u-mobile-reg").val();
	if(mobileVal==""){//验证手机是否为空
		$("#u-mobile-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入用户手机号！</span>');
		return;
	}
	var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
	if(reg.test(mobileVal)==false){//格式不正确
		$("#u-mobile-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入正确的手机！</span>');
		return;
	};
	
	if($("#u-password-reg").val().trim()==""){//验证密码是否为空
		$("#u-password-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入密码！</span>');
		return;
	}
	if($("#u-password-reg").val().length<6){//验证密码长度
		$("#u-password-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>密码长度不能小于六位！</span>');
		return;
	}
	if(($("#u-password-reg").val()).indexOf(" ")!=-1){
		$("#u-password-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>密码不能包含空格！</span>');
		return false;
	}
	if($("#u-passwordre-reg").val().trim()==""){//验证确认密码是否为空
		$("#u-passwordre-reg").next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入确认密码！</span>');
		return;
	}
	
	if($("#u-randomcode-reg").val().trim()==""){//验证 验证码是否为空
		$("#u-randomcode-reg").next().next().next().html('<span class="c-orange"><em class="icon16 u-a-cw">&nbsp;</em>请输入验证码！</span>');
		return;
	}

	$.ajax({
		url : baselocation + "/uc/createuser",
		data : {"user.email":$("#u-email-reg").val(),"user.password":$("#u-password-reg").val(),
			"confirmPwd":$("#u-passwordre-reg").val(),"registerCode":$("#u-randomcode-reg").val(),
			"user.mobile":$("#u-mobile-reg").val()},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.success==true) {
				window.location.reload();
			}else {
				$(".e-l-jy").html('<font class="fsize12 c-orange">'+result.message+'</font>');
			}
		},
		error : function(error) {
			$(".e-l-jy").html('<font class="fsize12 c-orange">系统繁忙，请稍后再操作</font>');
		}
	});
}

/**
 * 学过此课程的用户
 */
function getCourseLearnedUser(courseId){
	$.ajax({
		type : "POST",
		dataType : "json",
		url:baselocation+"/couserStudyHistory/ajax/courseLearnedUser/"+courseId,
		cache : true,
		async : false,
		success : function(result) {
			if(result.success==true){
				var resultList=result.entity;
				if(resultList.length!=0){
					var useImg="";
	 				var userShowName="";
	 				var resultStr='<section class="c-infor-tabTitle c-tab-title"><a href="" title="">学过此课的人（'+result.message+'）</a></section>';
	 				resultStr+='<section class="buy-cin-list">';
	 				for(var i=0;i<resultList.length;i++){
	 					useImg=resultList[i].userImg;
	 					if(useImg==null || $.trim(useImg)==''){
	 						useImg = baselocation+'/static/inxweb/img/avatar-boy.gif';
	 					}else{
	 						useImg =imagesPath+useImg;
	 					}
	 					userShowName=resultList[i].userShowName;
	 					if(userShowName==null || $.trim(userShowName)==''){
	 						userShowName = resultList[i].userEmail;
	 					}
	 					resultStr=resultStr+'<span title="'+userShowName+'"><img alt="" src="'+useImg+'"><tt class="c-999">'+userShowName+'</tt></span>';
	 				}
	 				resultStr+='</section>';
	 				$("#courseLearnedUserDiv").parent().html(resultStr);
				}
			}
		}
	});
};

/**
 *手机用户个人中心登录拦截跳转
 */
function mobileHrefCheckLogin(hrefUrl){
	if(isLogin()){
		window.location.href=hrefUrl;
	}else{
		lrFun();
	}
}