package com.meiya.order.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

public class MD5Util {

	 /**
     * 生成签名结果
     * @param value 要加密的字符串
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(String value , String charset) {
        String mysign = DigestUtils.md5Hex(getContentBytes(value, charset));
        return mysign;
    }



    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}
