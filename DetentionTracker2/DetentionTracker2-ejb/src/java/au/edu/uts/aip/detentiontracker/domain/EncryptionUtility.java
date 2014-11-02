/*
 *Utility class that provides encryption functionality for password creation (provided from tutorial notes)
 */
package au.edu.uts.aip.detentiontracker.domain;

import java.security.*;

public class EncryptionUtility {

    /**
     * Encryption algorithm provided in the tutorial notes
     *
     * @param data the string that is to be encrypted
     * @return the encrypted string
     * @throws NoSuchAlgorithmException if it fails to encrypt
     */
    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
