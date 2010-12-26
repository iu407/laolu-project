<%@ page language="java"   contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/meta.jsp" %>
<script type="text/javascript" src="${ctx }/static/javascripts/jquery.cookie.js"              language="JavaScript"></script>
<script type="text/javascript" src="${ctx }/static/javascripts/jquery.cycle.all.min.js"       language="JavaScript"></script>
<script type="text/javascript" src="${ctx }/static/javascripts/script.js"                     language="JavaScript"></script>

<script type="text/javascript">
            (function($) {
                $(function() {
                    $('#slideshow').cycle({
                        fx:     'scrollHorz',
                        timeout: 7000,
                        next:   '#rarr',
                        prev:   '#larr'
                    });
                })
            })(jQuery)
</script>
</head>
<body>
<%@ include file="common/menu.jsp" %>
<div class="container_12">

<div class="slideshow">
			<div id="slideshow">
            <div class="slide ">
            <div class="post">
                <a href="${ctx }" ><img width="640" height="290" src="${ctx }/static/images/first.jpg"  /></a>
                <div class="post-category"><a href="${ctx }" title="View all posts in Architecture" rel="category">Architecture</a> / <a href="${ctx }" title="View all posts in Photography" rel="category">Photography</a></div>
                <h2><a href="#">从网站上选择杂志</a></h2>

                <div class="post-meta">by <span class="post-author"><a href="${ctx }" title="Posts by admin">admin</a></span> on <span
                        class="post-date">Nov 13, 2010</span> &bull; <a href="${ctx }" class="comments_popup_link" title="Comment on Nunc eget libero non purus molestie vehicula">8 Comments</a> </div>
                <div class="post-content">挑选中您喜欢的杂志</div>
            </div>
            
       		</div>
            <div class="slide ">
            <div class="post">
                <a href="${ctx }"><img width="640" height="290" src="${ctx }/static/images/first1.jpg"  /></a>                <div class="post-category"><a href="${ctx }" title="View all posts in News" rel="category">News</a> / <a href="${ctx }" title="View all posts in Typography" rel="category">Typography</a></div>
                <h2><a href="#">放入购物车</a></h2>

                <div class="post-meta">by <span class="post-author"><a href="${ctx }" title="Posts by admin">admin</a></span> on <span
                        class="post-date">Nov 13, 2010</span> &bull; <a href="${ctx }" class="comments_popup_link" title="Comment on Aenean at quam vitae justo gravida aliquet">4 Comments</a> </div>
                <div class="post-content">我们进为您提供免费的配送服务！</div>
            </div>
       		</div>
            
            
            <div class="slide ">
            <div class="post">
                <a href="${ctx }"><img width="640" height="290" src="${ctx }/static/images/first2.jpg" class="attachment-slide wp-post-image" alt="Aenean at quam vitae justo gravida aliquet" title="Aenean at quam vitae justo gravida aliquet" /></a>                <div class="post-category"><a href="${ctx }" title="View all posts in News" rel="category">News</a> / <a href="${ctx }" title="View all posts in Typography" rel="category">Typography</a></div>
                <h2><a href="#">到期还书</a></h2>
                <div class="post-meta">by <span class="post-author"><a href="${ctx }" title="Posts by admin">admin</a></span> on <span
                        class="post-date">Nov 13, 2010</span> &bull; <a href="${ctx }"class="comments_popup_link" title="Comment on Aenean at quam vitae justo gravida aliquet">4 Comments</a> </div>
                <div class="post-content">还书时，将会有专人取回你借阅的杂志。并给您送上下一期的杂志！</div>
            </div>
       		</div>
        
        </div>
            <a href="javascript: void(0);" id="larr"></a>
            <a href="javascript: void(0);" id="rarr"></a>
</div>

</div>
<%@ include file="common/foot.jsp" %></body>
</html>
