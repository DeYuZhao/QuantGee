<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <title>账号登录</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../../css/ucenter/main.css">
    <link rel="stylesheet" href="../../css/ucenter/login.css">
    <link rel="stylesheet" href="../../css/ucenter/register.css"/>

    <script type="text/javascript" src="../../js/ucenter/login.js"></script>
</head>

<body>
<div class="header">
    <div class="control">
				<span class="p_center fl">
		            <a href="../../view/market/marketInfo.html" class="logo">
		            	<img src="../../images/ucenter/logo.png" width="134px" height="48px"/>
		            </a>
		            <span id="test" class="person">用户登录</span>
				</span>
    </div>
</div>

<div id="showcasebg">
    <div class="loginbox" id="loginbox">
        <h1>账号登录</h1>
        <form action="login" method="post">
            <input type="text" id="txt_userName" name="accountVO.accountID" maxlength="32"
                   onfocus="if(this.value=='请输入帐号') {this.value='';}this.style.color='#000';"
                   onblur="if(this.value=='') {this.value='请输入帐号';this.style.color='#fff';}" value="请输入帐号"/>
            <input type="password" id="txt_password" name="accountVO.password" placeholder="请输入密码"/>
            <input type="submit" id="txt_submit" class="submit" value="登录"
                   style="width:252px;height: 48px;font-size: 20px;text-align: center;background: #19A3D1;"/>
        </form>
        <div class="forget">
            <a href="/account/ForgetPassword" target="_parent">忘记密码？</a>
        </div>
        <input type="hidden" id="returnUrl" value="/">
        <div class="regist">
            <span>没有账号？</span>
            <a onclick="myfunction()">免费注册&gt;&gt;</a>
        </div>
    </div>

    <div class="registbox" id="registbox">
        <h1>账号注册</h1>
        <form action="login">
            <input type="text" id="txt_userName" name="accountVO.accountID" maxlength="32"
                   onfocus="if(this.value=='请输入帐号') {this.value='';}this.style.color='#000';"
                   onblur="if(this.value=='') {this.value='请输入帐号';this.style.color='#fff';}" value="请输入帐号"/>
            <input type="password" id="txt_password" name="accountVO.password" placeholder="请输入密码"/>
            <input type="password" id="txt_password" placeholder="请确认密码"/>
            <input type="text" id="txt_userName" name="accountVO.phoneNumber" maxlength="32"
                   onfocus="if(this.value=='请输入手机号码') {this.value='';}this.style.color='#000';"
                   onblur="if(this.value=='') {this.value='请输入手机号码';this.style.color='#fff';}" value="请输入手机号码"/>
            <input type="submit" id="txt_submit" class="submit" value="注册"
                   style="width:252px;height: 48px;font-size: 20px;text-align: center;background: #19A3D1;"/>
        </form>
    </div>

    <div id="image_1" class="showcase" style="background-color:#5bc4f2; ">
        <div class="showarea">
            <img src="../../images/login_background.jpg" alt="" style="float:left;">
        </div>
    </div>

</div>
</body>

</html>