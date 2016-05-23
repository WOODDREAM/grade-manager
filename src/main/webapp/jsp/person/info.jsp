<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- page start-->
<div class="row">
    <div class="col-lg-2">
        <input hidden="hidden" value="${message}"/>
    </div>
    <aside class="profile-info col-lg-8" id="personContent">
        <section class="panel">
            <div class="panel-body bio-graph-info">
                <h1>个人档案</h1>

                <div class="row">
                    <div class="bio-row">
                        <span>姓名 : <p id="personNamed">${person.name}</p></span>
                    </div>
                    <div class="bio-row">
                        <span>邮箱 :  <p id="personEmailed">${person.email}</p></span>
                    </div>
                    <div class="bio-row">
                        <span>手机号码 :  <p id="personMobiled">${person.mobile}</p></span>
                    </div>
                    <div class="bio-row">
                        <span>所在学校 : <p id="personSchooled"> ${person.school}</p></span>
                    </div>
                </div>
            </div>
        </section>
        <section class="panel">
            <div class="bio-graph-heading">
                个人档案修改
            </div>
            <div class="panel-body bio-graph-info">
                <h1> 档案信息</h1>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">姓名</label>

                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="person-name" placeholder=" "/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">邮箱</label>

                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="person-email" placeholder=" "/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">所在学校</label>

                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="person-school" placeholder=" "/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <button type="button" class="btn btn-info" id="submitEditInfo">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <section class="panel">
            <div class="panel panel-primary">
                <header class="panel-heading">
                    修改密码
                </header>
                <div class="panel-body">
                    <form class="form-horizontal cmxform" role="form">
                        <div class="form-group">
                            <label class="col-lg-2 control-label">新密码</label>

                            <div class="col-lg-6">
                                <input type="password" class="form-control" id="n-password" name="password"
                                       placeholder=" "/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">重试密码</label>

                            <div class="col-lg-6">
                                <input type="password" class="form-control" id="confirm_password"
                                       name="confirm_password" placeholder=" "/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button type="button" class="btn btn-info" id="submitPassword">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>

        <section class="panel">
            <div class="panel panel-primary">
                <div class="panel-heading">修改手机号</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-lg-2 control-label">手机号码</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="mobileEdit" placeholder=" "/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">验证码</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="mobileCode" placeholder=" "/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button type="button" class="btn btn-info" id="getCode">获取验证码</button>
                                <button type="button" class="btn btn-info" id="submitMobileForm" disabled="disabled">
                                    保存
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </aside>
</div>
<script src="/js/jquery.js"></script>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>

<script src="/js/person.js"></script>

