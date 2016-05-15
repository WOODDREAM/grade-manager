<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="col-lg-12">
    <section class="panel">
        <div class="panel-body">
            <div class="row">
                <section class="panel">
                    <header class="panel-heading">
                        创建课程
                    </header>
                    <div class="panel-body">
                        <div class="form">
                            <form class="cmxform form-horizontal tasi-form" id="createClassForm" method="post">
                                <div class="form-group ">
                                    <label for="classname" class="control-label col-lg-2">名称</label>

                                    <div class="col-lg-10">
                                        <input class=" form-control" id="className" name="className" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="classPeriod" class="control-label col-lg-2">学时</label>

                                    <div class="col-lg-10">
                                        <input class=" form-control" id="classPeriod" name="classPeriod" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="classCredit" class="control-label col-lg-2">学分</label>

                                    <div class="col-lg-10">
                                        <input class="form-control " id="classCredit" name="classCredit" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="classUnit" class="control-label col-lg-2">上课单位(周)</label>

                                    <div class="col-lg-10">
                                        <input class="form-control " id="classUnit" name="classUnit" type="text"
                                               value="周" readonly>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button class="btn btn-danger" type="submit">确定</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
</aside>
