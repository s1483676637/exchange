package com.bs.exchange.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

public class SharedPreferencesUtils {
	
	private static final String SP_NAME = "sp";
	private static SharedPreferences sp;
	
	public static void saveBoolean(Context context, String key, boolean value){
		if(sp == null)
		sp = context.getSharedPreferences(SP_NAME,0);
		sp.edit().putBoolean(key, value).commit();
		
	}
	
	//接收信息
	//发送信息
	//删除信息
	public static boolean getBoolean(Context context , String key, boolean defValue){
		if(sp == null)
		sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getBoolean(key, defValue);
	}
	
	
	public static void saveString(Context context, String key, String value){
		SharedPreferences sp = context.getSharedPreferences(SP_NAME,0);
		sp.edit().putString(key, value).commit();
	}
	public static String getString(Context context , String key, String defValue){
		if(sp == null)
		sp = context.getSharedPreferences(SP_NAME, 0);
		return sp.getString(key, null);
	}
	
	public static void saveInt(Context context, String key, int value){
		sp = context.getSharedPreferences(SP_NAME,0);
		sp.edit().putInt(key, value).commit();
	}
	
	
	public static int getInt(Context context, String key, int value){
		if(sp==null){
			 sp = context.getSharedPreferences(SP_NAME,value);
		}
		return sp.getInt(key, value);
	}
	
/**
 * 将集合转成string
 * @param SceneList
 * @return
 * @throws IOException
 */
	public static String SceneList2String(List SceneList)
	            throws IOException {
	      // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
	      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	      // 然后将得到的字符数据装载到ObjectOutputStream
	      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
	              byteArrayOutputStream);
	      // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
	      objectOutputStream.writeObject(SceneList);
	      // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
	      String SceneListString = new String(Base64.encode(
	              byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
	      // 关闭objectOutputStream
	      objectOutputStream.close();
	      return SceneListString;

	}

	/**
	 * 将String转成集合
	 * @param SceneListString
	 * @return
	 * @throws StreamCorruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	 @SuppressWarnings("unchecked")
	  public static List String2SceneList(String SceneListString)
	          throws StreamCorruptedException, IOException,
			 ClassNotFoundException {
	      byte[] mobileBytes = Base64.decode(SceneListString.getBytes(),
	              Base64.DEFAULT);
	      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
	              mobileBytes);
	      ObjectInputStream objectInputStream = new ObjectInputStream(
	              byteArrayInputStream);
	      List SceneList = (List) objectInputStream
	              .readObject();
	      objectInputStream.close();
	      return SceneList;
	  }



	
}
