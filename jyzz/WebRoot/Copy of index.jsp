<%@ page language="java"   contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/meta.jsp" %>
</head>
<body>
<%@ include file="common/menu.jsp" %>


	
	<div id="content">

		<!-- Normal content: Stuff that's not going to be put in the left or right column. -->
		<div id="normalcontent">
			<h3><strong>杂志吧</strong><sup>为</sup> <span>您提供杂志借阅服务。</span></h3>
			<div class="contentarea">
				<!-- Normal content area start -->

				<img src="${ctx}/static/images/pic1.jpg" />"  class="left" alt="" />
				<p>杂志借阅服务的说明</p>

				<!-- Normal content area end -->
			</div>
		</div>

	
		<div class="divider1"></div>


		<!-- Primary content: Stuff that goes in the primary content column (by default, the left column) -->
		<div id="primarycontainer">
			<div id="primarycontent">
				<!-- Primary content area start -->
		
				<div class="post">
					<h4>遗留问题</h4>
					<div class="contentarea">
						<div class="details">用户注册没有做</div>	
						<p>用户注册后，自动赋予权限用户权限</p>
						<div class="details">商品管理（上架下架，商品本身的属性）</div>	
						<p>上架下架，商品本身的属性不够完善</p>
						<div class="details">用户首页</div>	
						<p>用户的管理功能</p>
						<ul class="controls">
							<li><a href="#" class="printerfriendly">Printer Friendly</a></li>
							<li><a href="#" class="comments">评论(18)</a></li>
							<li><a href="#" class="more">Read More</a></li>
						</ul>
					</div>
				</div>
		
				<div class="divider2"></div>
		
				<div class="post">
					<h4>Consequat Odio Vestibulum</h4>
					<div class="contentarea">
						<div class="details">Posted by <a href="#">Jane Doe</a> on September 25, 2006</div>	
						<img src="${ctx}static/images/pic3.jpg" />" class="left" alt="A chinese dragon" />
						<p>Aenean felis quisque eros. Cras lobortis commodo lorem ipsum dolor. Vestibulum vel purus. In eget odio in sapien adipiscing blandit. Lorem ipsum dolor sit amet consequat etiam sed dolore.</p>
						<ul class="controls">
							<li><a href="#" class="printerfriendly">Printer Friendly</a></li>
							<li><a href="#" class="comments">Comments (18)</a></li>
							<li><a href="#" class="more">Read More</a></li>				
						</ul>
					</div>
				</div>
	
				<div class="divider2"></div>
		
				<div class="post">
					<h4>Adipiscing Nullam</h4>
					<div class="contentarea">
						<div class="details">Posted by <a href="#">Jane Doe</a> on September 25, 2006</div>	
						<p>Eget odio in sapien adipiscing blandit. Quisque augue tortor, facilisis sit amet, aliquam, suscipit vitae, cursus sed, arcu lorem ipsum dolor sit amet felis quisque eros. Cras lobortis commodo lorem ipsum dolor. Vestibulum vel purus. In eget odio in sapien adipiscing blandit. Quisque augue tortor, facilisis sit amet, aliquam, suscipit lorem ipsum dolor.</p>
						<ul class="controls">
							<li><a href="#" class="printerfriendly">Printer Friendly</a></li>
							<li><a href="#" class="comments">Comments (18)</a></li>
							<li><a href="#" class="more">Read More</a></li>				
						</ul>
					</div>
				</div>

				<!-- Primary content area end -->
			</div>
		</div>

		
		<!-- Secondary content: Stuff that goes in the secondary content column (by default, the narrower right column) -->
		<div id="secondarycontent">
			<!-- Secondary content area start -->
			
			<!-- HINT: Set any div's class to "box" to encapsulate it in (you guessed it) a box -->
			<div class="box">
				<h4>用户评论最多</h4>
				<div class="contentarea">
					<img src="${ctx}/static/images/pic2.jpg" />" class="left" alt="Random foliage" />
					<p>用户阅读最多的杂志 <a href="#">sed commodo</a>.</p>
				</div>
			</div>
		
			<div>
				<h4>常用的服务</h4>
				<div class="contentarea">
					<ul class="linklist">
						<li><a href="#">旅游推荐杂志</a></li>
						<li><a href="#">广告精选杂志</a></li>
						<li><a href="#">休闲类杂志</a></li>
						<li><a href="#">Turpis vestibulum</a></li>
						<li><a href="#">Sed etiam lorem ipsum</a></li>
						<li><a href="#">Sit amet dolore nulla</a></li>
						<li><a href="#">Facilisi sed tortor</a></li>
						<li><a href="#">Aenean felis quisque</a></li>
						<li><a href="#">Eros cras lobortis vel</a></li>
						<li><a href="#">Purus in eget odio sapien</a></li>
						<li><a href="#">Adipiscing blandit</a></li>
					</ul>
				</div>
			</div>

			<!-- Secondary content area end -->
		</div>


	</div>

<%@ include file="common/foot.jsp" %></body>
</html>
