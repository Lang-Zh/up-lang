package cn.lang.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;

import cn.lang.controller.IndexController;
import cn.lang.controller.QNYController;

public class LangConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }
    
    @Override
    public void configRoute(Routes me) {
        me.add("/qny", QNYController.class);
        me.add("/", IndexController.class);
    }
    @Override
    public void configEngine(Engine me) {
        
    }

    @Override
    public void configHandler(Handlers me) {
        
    }

    @Override
    public void configInterceptor(Interceptors me) {
        
    }

    @Override
    public void configPlugin(Plugins me) {
        
    }

    

}
