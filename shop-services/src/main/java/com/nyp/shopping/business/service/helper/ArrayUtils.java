/**
 * 
 */
package com.nyp.shopping.business.service.helper;

import java.io.UnsupportedEncodingException;

/**
 * @author Java Developer
 *
 */
public class ArrayUtils {

	public static byte[] charArrayToByteArray(char[] buffer) {

		byte[] byteArray = null;
		try {
			byteArray = new String(buffer).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArray;
		/*byte[] b = new byte[buffer.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) buffer[i];
		}
		return b;*/
	}

}
