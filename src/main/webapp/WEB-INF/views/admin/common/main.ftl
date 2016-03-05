[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"]/]
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html;" charset="UTF-8"/>
    <title>${msg("admin.common.main")}</title>
    <link rel="stylesheet" type="text/css" href="${path }/resources/css/main/main-all.css"/>
    <link rel="stylesheet" type="text/css" href="${path }/resources/css/main/main-base.css"/>
    <link rel="stylesheet" type="text/css" href="${path }/resources/css/main/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path }/resources/css/main/jquery-ui-1.8.22.custom.css"/>
    <script type="text/javascript" src="${path }/resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${path }/resources/js/main/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="${path }/resources/js/main/main-index.js"></script>
</head>
<body>
<div class="warp">
    <!--头部开始-->
    <div class="top_c">
        <div class="top-menu">
            <ul class="top-menu-nav">
                <li><a href="#">首页</a></li>
                <li><a href="#">查询界面<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><a target="Conframe" href="Template/find-form.html">表单样式</a></li>
                        <li><a target="Conframe" href="Template/find-alert.html">弹出窗口</a></li>
                        <li><a target="Conframe" href="Template/find-order.html">查询排序</a></li>
                        <li><a target="Conframe" href="Template/find-1.html">查询结果一</a></li>
                        <li><a target="Conframe" href="Template/find-2.html">查询结果二</a></li>
                    </ul>
                </li>
                <li><a href="#">维护界面<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-add.html">增加</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-edit.html">修改</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-del.html">删除</a></li>
                    </ul>
                </li>
                <li><a href="#">表单风格<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-Master-slave.html">主从表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-collapse.html">折叠表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs.html">标签式表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tree.html">树+表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-guide.html">向导</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs-list.html">标签页+列表</a></li>
                    </ul>
                </li>
                <li><a href="#">提示<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">权限提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">出错提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">警告提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">询问提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框一</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框二</a></li>
                    </ul>
                </li>
                <li><a href="#">辅助信息<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/order.html">采购订单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">数据说明</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">操作记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">提示</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    <div class="top-nav">
    [@shiro.hasPermission name="system:menu:view"]
        <span>上午好，欢迎您，邱秋！&nbsp;&nbsp;</span>
        <a href="#">修改密码</a> | <a href="../logout.jsp">安全退出</a></div>
    [/@shiro.hasPermission]
    </div>
    <!--头部结束-->
    <!--左边菜单开始-->
    <div class="left_c left">


        <h1>${msg("main.menu.list")}</h1>


        <div class="acc">

        [#list menus as menu]
            [@shiro.hasRole name="38"]
                <div>
                    <a class="one">${menu.name}</a>
                    [#list menu.subMenus as sm]
                        <ul class="kid">
                            <li><b class="tip"></b><a target="Conframe" href="${path }${sm.path }">${sm.name }</a></li>
                        </ul>
                    [/#list]
                </div>
            [/@shiro.hasRole]
        [/#list]

            <div id="datepicker"></div>
        </div>


    </div>
    <!--左边菜单结束-->
    <!--右边框架开始-->
    <div class="right_c">
        <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>

    </div>
    <div class="Conframe">
        <iframe name="Conframe" id="Conframe"></iframe>
    </div>
    <!--右边框架结束-->

    <!--底部开始-->
    <div class="bottom_c">Copyright &copy;2010-2015</div>
    <!--底部结束-->
</div>
</body>
</html>
