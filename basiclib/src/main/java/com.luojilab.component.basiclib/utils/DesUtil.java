package com.luojilab.component.basiclib.utils;

import java.net.URLDecoder;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by IntelliJ IDEA.
 * User: George
 * Date: 11-9-2
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
public class DesUtil {
    //             private final static Log log = LogFactory.getLog(DesUtil.class);
    private final static String DES = "DES";
    private final static String PADDING = "DES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

        //DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作

        Cipher cipher = Cipher.getInstance(PADDING);

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        // 现在，获取数据并加密

        // 正式执行加密操作

        return cipher.doFinal(src);

    }


    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        // DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建一个DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作

        Cipher cipher = Cipher.getInstance(PADDING);

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        // 现在，获取数据并解密

        // 正式执行解密操作

        return cipher.doFinal(src);

    }

    /**
     * 密码解密
     *
     * @param data
     * @return
     * @throws Exception
     */

    public final static String decrypt(String data, String key) {

        try {

            return new String(decrypt(Base64.decode(data.getBytes()),

                    key.getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 密码解密
     *
     * @param data
     * @return
     * @throws Exception
     */

    public final static String decrypt(String data, String key, String charset) {

        try {

            return new String(decrypt(Base64.decode(data.getBytes()),

                    key.getBytes()), charset);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 密码加密
     *
     * @param code
     * @return
     * @throws Exception
     */

    public final static String encrypt(String code, String key) {

        try {

            return Base64.encodeBytes(encrypt(code.getBytes("utf-8"), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        //System.out.println(DesUtil.encrypt("mGsAmeU/lQA=", "LJc4ZEnVXvtuese1RWsIs1Tml55wDvJU"));
        //System.out.println(decrypt(DesUtil.encrypt("mGsAmeU/lQA=", "LJc4ZEnVXvtuese1RWsIs1Tml55wDvJU"), "LJc4ZEnVXvtuese1RWsIs1Tml55wDvJU", "utf-8"));
        System.out.println(decrypt(URLDecoder.decode("VqvO0loVuKpvfMLdncmtSGMcm274sJu8"), "jd_live_group_message20170628", "utf-8"));

    }

    /**
     * Convert byte[] string to 16进制
     *
     * @param src
     * @return
     */
    public static String bytesTo16HexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return "";
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
