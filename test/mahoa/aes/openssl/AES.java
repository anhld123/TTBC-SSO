/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahoa.aes.openssl;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author BAOANH
 */
public class AES {

    public static final String banksmsIv = "Banksms@Viettel!";
    //public static final byte[] iv = banksmsIv.getBytes("UTF-8");

    public static SecretKey generateSharedSecret(PrivateKey privateKey,
            PublicKey publicKey) {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(publicKey, true);
            SecretKeySpec key = new SecretKeySpec(keyAgreement.generateSecret(), "AES");
            return key;
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptString(SecretKey key, String plainText, byte[] iv) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] plainTextBytes = plainText.getBytes("UTF-8");
            byte[] cipherText;
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            cipherText = new byte[cipher.getOutputSize(plainTextBytes.length)];
            int encryptLength = cipher.update(plainTextBytes, 0,
                    plainTextBytes.length, cipherText, 0);
            encryptLength += cipher.doFinal(cipherText, encryptLength);
            return bytesToHex(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private static String pubKeyCPSample = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEgwQOxoXhdBBamWPMYUEl4WKIOqRBLTgSKOuHhQU9iWbD2crMZOYvj0F31YZL4m/7Uy2oOjLjYi+Odfrvaj24OQ==";
    private static String priKeyCPSample = "MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgnFkCkg26s2pwWa1oV0cYFUUqU0sCDeYP2wRpUQcu74WhRANCAASDBA7GheF0EFqZY8xhQSXhYog6pEEtOBIo64eFBT2JZsPZysxk5i+PQXfVhkvib/tTLag6MuNiL451+u9qPbg5";
    private static String pubKeyVT = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEe9YVVfiuEbTC8MLFcmettM9z6i1bh49kM97NZJ0yLCZcDQtWhuQ230W/nackSySWO8tErAzDWiMdvEACaHevaA==";

    private static String plainText = "Hello! This is an banksms message!";

    public static void main(String[] args) {
        //try {
            /*
            byte[] iv = banksmsIv.getBytes("UTF-8");
            byte[] pkcs8EncodedBytesPri = Base64.getDecoder().decode(priKeyCPSample);
            //priKeyCPSample.getBytes("UTF-8");
            byte[] pkcs8EncodedBytesPub = Base64.getDecoder().decode(pubKeyVT);
            //pubKeyVT.getBytes("UTF-8");
            // extract the private key
            PKCS8EncodedKeySpec keySpecpri = new PKCS8EncodedKeySpec(pkcs8EncodedBytesPri);
            KeyFactory kfpri = KeyFactory.getInstance("EC");
            PrivateKey privKey = kfpri.generatePrivate(keySpecpri);

//            PKCS8EncodedKeySpec keySpecPub = new PKCS8EncodedKeySpec(pkcs8EncodedBytesPub);
//            KeyFactory kfpub = KeyFactory.getInstance("ECDSA", "BC");
//            PublicKey publKey=kfpub.generatePublic(keySpecPub);
         
            X509EncodedKeySpec specPublic = new X509EncodedKeySpec(pkcs8EncodedBytesPub);

            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            ECPublicKey public_key12 = (ECPublicKey) keyFactory.generatePublic(specPublic);



            SecretKey secrecKey = generateSharedSecret(privKey, public_key12);

            String msg = encryptString(secrecKey, plainText, iv);

            System.err.println(msg);

        } catch (Exception ex) {
            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
}
