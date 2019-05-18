package com.magazine.utils;

import java.io.*;

/**
 * 序列化通用类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-18
 */
public class SerializeUtils {


	/**
	 * 序列化
	 *
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] serializeObject(Object object) throws IOException {
		ByteArrayOutputStream saos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(saos);
		oos.writeObject(object);
		oos.flush();
		return saos.toByteArray();
	}

	/**
	 * 反应序列化
	 *
	 * @param buf
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserializeObject(byte[] buf) throws IOException, ClassNotFoundException {
		Object object = null;
		ByteArrayInputStream sais = new ByteArrayInputStream(buf);
		ObjectInputStream ois = new ObjectInputStream(sais);
		object = ois.readObject();
		return object;
	}


}
