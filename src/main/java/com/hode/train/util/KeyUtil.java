package com.hode.train.util;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 数字证书工具类
 *
 * @author lh
 *
 */
public class KeyUtil {

    /**
     * Java密钥库(Java Key Store，JKS)KEY_STORE
     */
    public static final String KEY_STORE = "JKS";

    public static final String X509 = "X.509";

    /**
     * 密钥库
     */
    public static Map<String, String> privateKeyMap = new HashMap<String, String>();

    static {
        privateKeyMap.put("lgdx01", "506164"); // 河南理工大学安全技术培训中心
        privateKeyMap.put("mk01", "429169"); // 河南煤矿安全培训中心
        privateKeyMap.put("gcxy01", "936316"); // 河南工程学院安全技术培训中心
        privateKeyMap.put("pds01", "130677"); // 平顶山天安煤业股份有限公司安全技术培训中心
        privateKeyMap.put("hb01", "304382"); // 鹤壁煤业技师学院
        privateKeyMap.put("zz01", "734981"); // 郑州煤炭高级技工学校
        privateKeyMap.put("xm01", "562402"); // 新密市煤炭学校
        privateKeyMap.put("ym01", "777574"); // 义马煤业集团股份有限公司安全培训中心
        privateKeyMap.put("yc01", "984414"); // 永城煤电控股集团有限公司职工培训学校
        privateKeyMap.put("jm01", "317275"); // 焦煤集团培训中心
        privateKeyMap.put("szxld", "134397"); // 省中心领导
        privateKeyMap.put("spry", "437217"); // 审批人员
        privateKeyMap.put("scry", "881904"); // 审查人员
        privateKeyMap.put("bzry", "130227"); // 补证变更人员
        privateKeyMap.put("cjgl", "997788"); // 成绩管理人员
        privateKeyMap.put("admin", "798581"); // 系统管理员
    }

    /**
     * 由KeyStore获得私钥
     *
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String keyStorePath, String alias,
                                            String keyStorePassword, String privateKeyPassword)
            throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, keyStorePassword);
        PrivateKey key = (PrivateKey) ks.getKey(alias,
                privateKeyPassword.toCharArray());
        return key;
    }

    /**
     * 由Certificate获得公钥
     *
     * @param certificatePath
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String certificatePath)
            throws Exception {
        Certificate certificate = getCertificate(certificatePath);
        PublicKey key = certificate.getPublicKey();
        return key;
    }

    /**
     * 获得Certificate
     *
     * @param certificatePath
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String certificatePath)
            throws Exception {
        CertificateFactory certificateFactory = CertificateFactory
                .getInstance(X509);
        FileInputStream in = new FileInputStream(certificatePath);

        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();

        return certificate;
    }

    /**
     * 获得Certificate
     *
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String keyStorePath,
                                              String alias, String password) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, password);
        Certificate certificate = ks.getCertificate(alias);

        return certificate;
    }

    /**
     * 获得KeyStore
     *
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath, String password)
            throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath,
                                             String alias, String keyStorePassword, String privateKeyPassword)
            throws Exception {
        // 取得私钥
        PrivateKey privateKey = getPrivateKey(keyStorePath, alias,
                keyStorePassword, privateKeyPassword);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);

    }

    /**
     * 私钥解密
     *
     * @param data
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath,
                                             String alias, String keyStorePassword, String privateKeyPassword)
            throws Exception {
        // 取得私钥
        PrivateKey privateKey = getPrivateKey(keyStorePath, alias,
                keyStorePassword, privateKeyPassword);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);

    }

    /**
     * 公钥加密
     *
     * @param data
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String certificatePath)
            throws Exception {

        // 取得公钥
        PublicKey publicKey = getPublicKey(certificatePath);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);

    }

    /**
     * 公钥解密
     *
     * @param data
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String certificatePath)
            throws Exception {
        // 取得公钥
        PublicKey publicKey = getPublicKey(certificatePath);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);

    }

    /**
     * 验证Certificate
     *
     * @param certificatePath
     * @return
     */
    public static boolean verifyCertificate(String certificatePath) {
        return verifyCertificate(new Date(), certificatePath);
    }

    /**
     * 验证Certificate是否过期或无效
     *
     * @param date
     * @param certificatePath
     * @return
     */
    public static boolean verifyCertificate(Date date, String certificatePath) {
        boolean status = true;
        try {
            // 取得证书
            Certificate certificate = getCertificate(certificatePath);
            // 验证证书是否过期或无效
            status = verifyCertificate(date, certificate);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 验证证书是否过期或无效
     *
     * @param date
     * @param certificate
     * @return
     */
    private static boolean verifyCertificate(Date date, Certificate certificate) {
        boolean status = true;
        try {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            x509Certificate.checkValidity(date);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 签名
     *
     * @param keyStorePath
     * @param alias
     * @param password
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] sign, String keyStorePath, String alias,
                              String keyStorePassword, String privateKeyPassword)
            throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(
                keyStorePath, alias, keyStorePassword);
        // 获取私钥
        KeyStore ks = getKeyStore(keyStorePath, keyStorePassword);
        // 取得私钥
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias,
                privateKeyPassword.toCharArray());

        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate
                .getSigAlgName());
        signature.initSign(privateKey);
        signature.update(sign);
        return encryptBASE64(signature.sign());
    }

    /**
     * 验证签名
     *
     * @param data
     * @param sign
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String sign,
                                 String certificatePath) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
        // 获得公钥
        PublicKey publicKey = x509Certificate.getPublicKey();
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate
                .getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(data);

        return signature.verify(decryptBASE64(sign));

    }

    /**
     * 验证Certificate
     *
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     */
    public static boolean verifyCertificate(Date date, String keyStorePath,
                                            String alias, String password) {
        boolean status = true;
        try {
            Certificate certificate = getCertificate(keyStorePath, alias,
                    password);
            status = verifyCertificate(date, certificate);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 验证Certificate
     *
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     */
    public static boolean verifyCertificate(String keyStorePath, String alias,
                                            String password) {
        return verifyCertificate(new Date(), keyStorePath, alias, password);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
}
