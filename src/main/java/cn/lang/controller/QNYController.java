package cn.lang.controller;
import java.util.Objects;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.lang.manager.QNYManager;

/**
 * <p>Description:七牛云 <／p>
 * @author Lang
 * @date 2019年11月23日
 * @version 1.0
 */
public class QNYController extends BaseController{

    /**   
     * @Title: getToken   
     * @Description: 获取token
     * @param:     
     * @return: void      
     * @throws   
     */
    public void getToken() {
        String upToken = QNYManager.getToken();
        renderJson(Ret.ok("token",upToken));
    }
    
    
    public void getAllImage() {
        Integer pageNumber = getParaToInt("pageNumber");
        Integer pageSize = getParaToInt("pageSize");
        Page<Record> pageFileList = QNYManager.pageFileList(pageNumber, pageSize);
        renderJson(Ret.ok("data",pageFileList));
    }
    
    public void deleteImage() {
        String key = getPara("key");
        String secret = getPara("secret");
        if(!Objects.equals(PropKit.use("qny.properties").get("secret"), secret)){
            renderJson(Ret.fail("msg","秘钥呢？不要瞎几把删"));
            return;
        }
        if(QNYManager.delete(key)){
            renderJson(Ret.ok());
        }else{
            renderJson(Ret.fail("msg","删除失败"));
        }
        
    }
    
}
