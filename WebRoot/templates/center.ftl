<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>分享之家后台管理系统</title>
<#include "common.ftl">
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<ul class="nav">
					<li><a href="logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div>蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div class="selected">天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2></h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>分享之家后台管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="#">管理1</a>
								<ul>
									<li><a target="navTab" rel="listxx" href="xxx/listInfo?runType=0">xx管理</a></li>
								</ul>
							</li>
							<li><a herf="#">管理2</a>
								<ul>
									<li><a href="xxx/listxx" target="navTab" rel="listGames">xx列表</a></li>
									<li><a href="xxx/add" target="navTab" rel="addGame">新增xx</a></li>
								</ul>
							</li>
							<li><a herf="#">推荐管理</a>
								<ul>
									<li><a href="xxx/listInfo" target="navTab" rel="listRecommend">推荐记录管理</a></li>
									<li><a href="xxx/addInfo" target="navTab" rel="addInfo">新增推荐</a></li>
								</ul>
							</li>
							<li>
								<a href="admin/listInfo" rel="listAdmin" target="navTab">管理员列表</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
	<div id="footer">Copyright &copy; 2013 <a href="http://www.sharehome.com" target="_blank">sharehome.com</a></div>
</body>
</html>