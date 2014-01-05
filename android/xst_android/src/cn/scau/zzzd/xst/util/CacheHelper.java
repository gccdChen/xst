package cn.scau.zzzd.xst.util;

import java.io.File;
import java.io.FilenameFilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.Pair;
import cn.scau.zzzd.xst.base.C;
import cn.scau.zzzd.xst.base.C.ARTCLE_TYPE;
/**
 * 2013-12-13
 */
public class CacheHelper implements AppMode{
	
	// tag for log
	private static String TAG = CacheHelper.class.getSimpleName();
	/**
	 * 获取缓存图片
	 * @param ctx 
	 * @param url
	 * @return 没有则返回null
	 */
	public static Bitmap getCachedImage (Context ctx, String url) {
		String filename = StringUtil.getFilenameFromUrl(url);
		Bitmap cachedImage = IOUtil.getImage(C.dir.img,filename);
		Log.w(TAG, "get cached image");
		return cachedImage;
	}
	
	/**
	 * 
	 * @param type
	 * @return if has not null
	 */
    public static String getListCache(int type) {
        File cacheFile =new File(C.dir.list+"/"+type);
        if(!cacheFile.exists())
        	return null;
        String result = IOUtil.readText(cacheFile);
        return result;
    }
    
    /**
     * 放入文件缓存
     * @param type
     * @param content
     * @return
     */
    public static boolean saveListCache(int type,String content){
    	 File cacheFile =new File(C.dir.list + "/" +type);
    	 if(!cacheFile.getParentFile().exists())
    		 cacheFile.getParentFile().mkdirs();
         IOUtil.writeText(content, cacheFile,false);
         return true;
    }
    /**
     * 放入文件缓存
     * @param type
     * @param content
     * @return
     */
    public static boolean saveListCache(ARTCLE_TYPE artcle_type,String content){
    	int type = Integer.parseInt(artcle_type.toString());
    	return saveListCache(type, content);
    }

    /**
     * 详细页的缓存文件存储为 id:updatetime
     * 如果过慢,考虑使用本地命令实现
     * @param article_id
     * @return Pair<String, String> updateTime,content
     */
	private static Pair<String, String> getDetailCache(long article_id) {
		// TODO Auto-generated method stub
		File cacheDir =new File(C.dir.detail);
		String start_txt = article_id+C.SEPARATOR;
		if(cacheDir.exists()){
			File[] files = cacheDir.listFiles();
			File f = null;
			String filename = null;
			for(int i=0;i<files.length;i++){
				f = files[i];
				filename = f.getName();
				if(f.exists() && f.isFile() && filename.startsWith(start_txt)){
					String[] a = filename.split(C.SEPARATOR);
					String content = IOUtil.readText(f);
					return new Pair<String, String>(a[1], content);
				}
			}
		}
		return null;
	}
    
    final static long MAX_CACHE_SIZE = 20 * 1024 * 1024;//20M
    /**
     * 清内存将会清掉
     */
    public static void clearCache() {
		IOUtil.clearDir(C.dir.base);
	}
    /**
     * 获取缓存大小 KB
     */
    public static long getCacheSize() {
    	File file = new File(C.dir.base);
    	try {
    		long size = IOUtil.getFilesSize(file);
			return size >> 10;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }

	public static String getCacheDecodeString(String data) {
        return data;
    }
	
	private static long IMAGE_EXPIRE_TIME = 864000000;//图片最大保留时间 = 10*24*60*60*1000 10天
	public static void removeExpiredCache(String dirPath, String filename) {
		File file = new File(dirPath, filename);
		if (System.currentTimeMillis() - file.lastModified() > IMAGE_EXPIRE_TIME) {
			Log.i(TAG, "Clear some expiredcache files ");
			file.delete();
		}
	}
}
class CacheUrlFilter implements FilenameFilter{
	String url = null;
	public CacheUrlFilter(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}
	@Override
	public boolean accept(File dir, String filename) {
		// TODO Auto-generated method stub
		return filename.contains(url);
	}
	
	
	//app mode
}
interface AppMode{
	
}