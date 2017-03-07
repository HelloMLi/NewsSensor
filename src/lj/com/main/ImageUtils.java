package lj.com.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;





import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class ImageUtils {

	/**
	 * 将图片保存到SD中
	 */
	public ImageUtils() {
		super();
	}
	
	public  void saveFile(Bitmap bm, String fileName) throws IOException {
		// 未安装SD卡时不做保存
		String storageState = Environment.getExternalStorageState();
		if(!storageState.equals(Environment.MEDIA_MOUNTED)) {
			//ToastUtils.showToast(context, "未检测到SD卡", Toast.LENGTH_SHORT);
			//Toast.makeText(this, "未检测到卡", duration)
			return;
		}
		
		// 图片文件保存路径
		File storageDirectory = Environment.getExternalStorageDirectory();
		File path = new File(storageDirectory, "/NewsSensor/newsimg");
		// 图片路径不存在创建之
		if (!path.exists()) {
			path.mkdirs();
		}
		// 图片文件如果不存在创建之
		File myCaptureFile = new File(path, fileName);
		if (!myCaptureFile.exists()) {
			myCaptureFile.createNewFile();
		}
		Log.i("tag", bm.toString()+"");
		// 将图片压缩至文件对应的流里,即保存图片至该文件中
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
		bos.flush();
		bos.close();
		Log.i("Imageutil", "success");
	}
	public  Bitmap readImage(String imgname)
	{
		Bitmap bmpDefaultPic;
	
		//文件保存路径
		File storageDirectory = Environment.getExternalStorageDirectory();
		File path1 = new File(storageDirectory, "/NewsSensor/newsimg");

		// 图片文件如果不存在创建之
		File myCaptureFile = new File(path1, imgname);
		if (!path1.exists()||!myCaptureFile.exists()) {
			Log.i("XX", "没找到图片");
			bmpDefaultPic=null;
		}
		else
			{
			Log.i("GO", "找到了图片");
		//	if(bmpDefaultPic==null)
			Log.i("XX", "1");
			//bmpDefaultPic = BitmapFactory.decodeFile(path.toString()+"/NewsSensor/newsimg/"+fileName,null);
			bmpDefaultPic = BitmapFactory.decodeFile(myCaptureFile.toString(),null);
			}
			return bmpDefaultPic;
    
	}
	
	

	
	public  void showImage(ImageView iv,String filename)
	{
		Bitmap read=readImage(filename);
	      if(read==null)
	    	  new AsyncTaskImageLoad(iv,filename).execute();
	    	//  iv.setImageResource(R.drawable.ic_launcher);

	      else
	    	 iv.setImageBitmap(read);
	}
	
	
	  public  Bitmap getBitmapFromURL(String urlString)
			{
				Bitmap bitmap;
				URL url;
				InputStream is = null;
				try {
					url = new URL(urlString);
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					is=new BufferedInputStream(conn.getInputStream());
				    bitmap=BitmapFactory.decodeStream(is);
				    conn.disconnect();
				return bitmap;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
	
	  
	private  class AsyncTaskImageLoad extends AsyncTask<Void, Void, Bitmap> {

			 private ImageView img=null;
			 private String mfilename;
			
			      public AsyncTaskImageLoad(ImageView img,String name) 
			      {
			          this.img=img;
			          this.mfilename=name;
			      }


			protected Bitmap doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				
				String query = URLEncoder.encode(mfilename);
				String pic_url="http://183.174.228.2/pic/pic.aspx?c=1&q="+query+"&market=zh-CN";
				 Bitmap bitmap=getBitmapFromURL(pic_url);	
				 if(bitmap!=null)
					try {
						saveFile(bitmap,mfilename);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return bitmap;
			}
			
			 protected void onPostExecute(Bitmap result)
			    {
				  super.onPostExecute(result);
					//ImageView iv=(ImageView)lv.findViewWithTag(mfilename);
					
			       // if(iv!=null && result!=null)
				  if(img.getTag().equals(mfilename))
			        {
//			        	try {
//							ImageUtil.saveFile(result, mfilename);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
			            img.setImageBitmap(result);
			        }
			     //   mTask.remove(this);
			    }

			

		}
//	  public void cancelAllTasks() {
//			// TODO Auto-generated method stub
//	   if(mTask!=null)
//		   for(AsyncTaskImageLoad task:mTask)
//		   {
//			   task.cancel(false);
//		   }
//			
//		}
//	  
	  
	  
}
