package cn.lang.controller;

import com.jfinal.kit.PropKit;

public class IndexController extends BaseController{

    public void index() {
        PropKit.use("qny.properties");
        String domain = PropKit.get("domain");
        System.err.println(domain);
        setAttr("domain", domain);
        render("/lang/index.html");
    }
}
