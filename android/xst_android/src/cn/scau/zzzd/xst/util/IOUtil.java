package cn.scau.zzzd.xst.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
/**
 * 文件操作工具类
 * @author gccd
 * 2013-12-13
 */
public class IOUtil {

	private static String TAG = IOUtil.class.getSimpleName();

	/**
	 * sd卡是否可用
	 * @return
	 */
	public static boolean sdCanUse() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 获取图片
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static Bitmap getImage(String dir,String fileName) {
		// check image file exists
		String realFileName = dir + "/" + fileName;
		File file = new File(realFileName);
		if (!file.exists()) {
			return null;
		}
		// get original image
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(realFileName, options);
	}
	
	private final static int MAX_SIZE_OF_BITMAP = 200 * 1024;
	private static double FREE_SD_SPACE_NEEDED_TO_CACHE = 10;
	/**
	 *	如果图片像素很高,进行一定的压缩.压缩仅通过 
	 * if (getBitmapSize(bitmap) > 10 * MAX_SIZE_OF_BITMAP) {
	 *			bitmap.compress(Bitmap.CompressFormat.PNG, 60, outStream);
	 *			Log.i("compress 60", "size :" + getBitmapSize(bitmap) + " b");
	 *		} else if(getBitmapSize(bitmap) >  MAX_SIZE_OF_BITMAP){
	 *			bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream);
	 *			Log.i("compress 90", "size :" + getBitmapSize(bitmap) + " b");
	 *		}else{
	 *			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	 *		}
	 * }
	 * @param bitmap
	 * @param fileName
	 * @param sdir
	 * @return
	 */
	public static String saveImage(Bitmap bitmap, String fileName, String sdir) {

		if (bitmap == null) {
			Log.w(TAG, " trying to save null bitmap");
			return null;
		}
		// 判断sdcard上的空间
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > getFreeSpace()) {
			Log.w(TAG, "Low free space onsd, do not cache");
			return null;
		}
		// 不存在则创建目录
		File dir = new File(sdir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 保存图片
		try {
			String realFileName = sdir + "/" + fileName;
			File file = new File(realFileName);
			file.createNewFile();
			OutputStream outStream = new FileOutputStream(file);
			// 如果手机很好，像素很高。那么进行压缩
			if (getBitmapSize(bitmap) > 10 * MAX_SIZE_OF_BITMAP) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 60, outStream);
				Log.i("compress 60", "size :" + getBitmapSize(bitmap) + " b");
			} else if(getBitmapSize(bitmap) >  MAX_SIZE_OF_BITMAP){
				bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream);
				Log.i("compress 90", "size :" + getBitmapSize(bitmap) + " b");
			}else{
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			}
			outStream.flush();
			outStream.close();
			Log.i(TAG, "Image saved tosd");
			return realFileName;
		} catch (FileNotFoundException e) {
			Log.w(TAG, "FileNotFoundException");
		} catch (IOException e) {
			Log.w(TAG, "IOException");
		}
		return null;
	}

	/**
	 * 获取Bitmap的大小
	 * @param bitmap
	 * @return
	 */
	public static int getBitmapSize(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		}
		return bitmap.getRowBytes() * bitmap.getHeight();
	}

	protected static void updateTime(String fileName) {
		File file = new File(fileName);
		long newModifiedTime = System.currentTimeMillis();
		file.setLastModified(newModifiedTime);
	}
	
	private static double MB = 1024;
	/**
	 * 计算sdcard上的剩余空间
	 * 
	 * @return
	 */
	public static int getFreeSpace() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / MB;
		return (int) sdFreeMB;
	}
	
	

	public static void clearDir(String dir) {
		File fdir = new File(dir);
		clearDir(fdir);
	}
	public static void clearDir(File fdir) {
		if (fdir.isDirectory()) {
			for (File file : fdir.listFiles()) {
				if(file.isDirectory()){
					clearDir(file);
				}else{
					file.delete();
				}
			}
		}
	}

	
	public static void sortByLastModify(File[] files) {
		if (files == null || files.length == 0)
			return;
		quick(files);
	}

	private static void quick(File[] list) {
		if (list.length > 0) { // 查看数组是否为空
			quickSort(list, 0, list.length - 1);
		}
	}

	private static void quickSort(File[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high);
			quickSort(list, low, middle - 1);
			quickSort(list, middle + 1, high);
		}
	}

	private static int getMiddle(File[] list, int low, int high) {
		File tmp = list[low];
		while (low < high) {
			while (low < high
					&& list[high].lastModified() <= tmp.lastModified()) {
				high--;
			}
			list[low] = list[high];
			while (low < high && list[low].lastModified() >= tmp.lastModified()) {
				low++;
			}
			list[high] = list[low];
		}
		list[low] = tmp;
		return low;
	}

	/**
	 * file1比file2新则返回1
	 * 
	 * @param file1
	 * @param file2
	 * @return file1.lastmodify()>file2?1:-1
	 */
	private static int compare(File file1, File file2) {
		return file1.lastModified() > file2.lastModified() ? 1 : -1;
	}

	/**
	 * 读取文件文本
	 * @param file
	 * @return
	 */
	public static String readText(File file) {
		if (file == null || !file.exists())
			return null;
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		String temp = null;
		StringBuffer buffer = new StringBuffer();
		try {
			reader = new FileReader(file);
			bufferedReader = new BufferedReader(reader);
			while ((temp = bufferedReader.readLine()) != null) {
				buffer.append(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 写入 
	 * @param content
	 * @param filePath
	 */
	public static void writeText(String content, String filePath,boolean append) {
		File file = new File(filePath);
		FileWriter writer = null;
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
				writer = new FileWriter(file,append);
				writer.write(content);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 写入
	 * @param content
	 * @param file
	 * @param append
	 */
	public static void writeText(String content, File file,boolean append) {
		FileWriter writer = null;
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
				writer = new FileWriter(file,append);
				writer.write(content);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				writer = new FileWriter(file,append);
				writer.write(content);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private final static float CACHE_REMOVE_FACTOR = 0.4f;
	/**
	 * 清除缓存 当sd剩余空间少于预定值,则按照
	 * @param dirPath
	 */
	public static void removeCache(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null) {
			return;
		}
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > IOUtil.getFreeSpace()) {
			int removeFactor = (int) ((CACHE_REMOVE_FACTOR * files.length) + 1);
			Arrays.sort(files, new FileLastModifSort());
			Log.i(TAG, "Clear some expiredcache files ");
			for (int i = 0; i < removeFactor; i++) {
				files[i].delete();
			}
		}
	}
	private static class FileLastModifSort implements Comparator<File> {
		@Override
		public int compare(File arg0, File arg1) {
			if (arg0.lastModified() > arg1.lastModified()) {
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * 
	 * @param f
	 * @return bytes
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			System.out.println("文件不存在");
		}
		return s;
	}

	// 递归
	/**
	 * 
	 * @param dir
	 * @return bytes
	 * @throws Exception
	 */
	public static long getFilesSize(File dir) throws Exception// 取得文件夹大小
	{
		long size = 0;
		File flist[] = dir.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	public static String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public static long getlist(File dir) {// 递归求取目录文件个数
		long size = 0;
		File flist[] = dir.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getlist(flist[i]);
				size--;
			}
		}
		return size;
	}

	/**
	 * 
	 * @param bm
	 * @return
	 */
	public static InputStream Bitmap2IS(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
		return sbs;
	}
	
	/**
	 * EntityUtils.toString() 添加 gzip 解压功能
	 * @param entity
	 * @param defaultCharset
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String gzipToString(final HttpEntity entity, final String defaultCharset) throws IOException, ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		InputStream instream = entity.getContent();
		if (instream == null) {
			return "";
		}
		// gzip logic start
		if (entity.getContentEncoding().getValue().contains("gzip")) {
			instream = new GZIPInputStream(instream);
		}
		// gzip logic end
		if (entity.getContentLength() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
		}
		int i = (int)entity.getContentLength();
		if (i < 0) {
			i = 4096;
		}
		String charset = EntityUtils.getContentCharSet(entity);
		if (charset == null) {
			charset = defaultCharset;
		}
		if (charset == null) {
			charset = HTTP.DEFAULT_CONTENT_CHARSET;
		}
		Reader reader = new InputStreamReader(instream, charset);
		CharArrayBuffer buffer = new CharArrayBuffer(i);
		try {
			char[] tmp = new char[1024];
			int l;
			while((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}
	
	/**
	 * EntityUtils.toString() 添加 gzip 解压功能
	 * @param entity
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String gzipToString(final HttpEntity entity)
		throws IOException, ParseException {
		return gzipToString(entity, null);
	}
}