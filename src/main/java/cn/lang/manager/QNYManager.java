package cn.lang.manager;

import java.util.ArrayList;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.BucketManager.FileListIterator;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

/**
 * <p>Description:七牛云 <／p>
 * @author Lang
 * @date 2019年12月22日
 * @version 1.0
 */
public class QNYManager {
    
    private static String accessKey;//AccessKey的值
    private static String secretKey;//SecretKey的值
    private static String bucket;//存储空间名  
    
    static{
        PropKit.use("qny.properties");
        accessKey = PropKit.get("accessKey");
        secretKey = PropKit.get("secretKey");
        bucket = PropKit.get("bucket");  
    }

    public  static Auth getAuth() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth;
    }
    
    public static String getToken() {
        String upToken = getAuth().uploadToken(bucket);
        return upToken;
    }
    
    public static BucketManager getBucketManager() {
        Auth auth = getAuth();
      //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        return new BucketManager(auth, cfg);

    }
    
    public static FileListIterator getFileListIterator(int limit) {
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
       // int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        BucketManager bucketManager = getBucketManager();
        //列举空间文件列表
        return bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
    }
    
    public static Page<Record> pageFileList(int pageNumber,int pageSize) {
        //列举空间文件列表
        FileListIterator fileListIterator = getFileListIterator(pageSize);
        int totalRow=0;
        int totalPage=0;
        ArrayList<Record> arrayList = new ArrayList<>();
        while (fileListIterator.hasNext()) {
            FileInfo[] fileInfos = fileListIterator.next();
            totalRow += fileInfos.length;
            if(pageNumber==++totalPage){
                for (FileInfo fileInfo : fileInfos) {
                    Record r = new Record().set("key", fileInfo.key);
                    arrayList.add(r);
                }
            }
        }  
        return new Page<Record>(arrayList, pageNumber,pageSize,totalPage,totalRow);
    }
    
    public static boolean delete(String key) {
        try {
            Response delete = getBucketManager().delete(bucket, key);
            delete.close();
            return true;
        } catch (QiniuException e) {
            LogKit.warn("删除失败");
            e.printStackTrace();
            return false;
        }
    }
}
