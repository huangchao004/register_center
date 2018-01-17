package com.curry.like.api.api_service.util;

import java.util.Date;
import java.util.Random;


/**
 * 
 * @Title: RandomUtils.java
 * @Package com.payelm.common.secure
 * @Description: 产生随机功能的工具类
 * @version V1.0
 */
public class RandomUtils {

	/**
	 * 生成指定长度的随机字符串
	 * 
	 * @param strLength
	 * @return
	 */
	public static String getRandomString(int strLength) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < strLength; i++) {
			int charInt;
			char c;
			if (random.nextBoolean()) {
				charInt = 48 + random.nextInt(10);
				c = (char) charInt;
				buffer.append(c);
				continue;
			}
			charInt = 65;
			if (random.nextBoolean())
				charInt = 65 + random.nextInt(26);
			else
				charInt = 97 + random.nextInt(26);
			if (charInt == 79)
				charInt = 111;
			c = (char) charInt;
			buffer.append(c);
		}

		return buffer.toString();

	}
	public static String getSerialNo() {
		String s7 = getRandomString(6);
		String date = DateUtils.format(new Date(), "MMddHHmmss");
		return date + s7;
	}
	public static String getOrderId(){
		return "TB"+getSerialNo();
	}
	public static String getMiddlePaySerialNo(){
		String serial = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS") + getRandomString(3);
		return serial;
	}

	public static void main(String args[]){
		System.out.println(getMiddlePaySerialNo());
	}
}
