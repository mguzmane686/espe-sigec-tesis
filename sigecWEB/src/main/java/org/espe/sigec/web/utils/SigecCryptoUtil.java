package org.espe.sigec.web.utils;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * @author roberto
 *
 */
public class SigecCryptoUtil {
	public static final SigecCryptoUtil SIGEC_CRYPTO_UTIL = new SigecCryptoUtil();

	public static SigecCryptoUtil getInstance() {
		return SIGEC_CRYPTO_UTIL;
	}
	
	public String encodeString(String rawString){
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		return passwordEncoder.encodePassword(rawString, null);
	}
	
	public boolean isValidRaw(String encodedString, String rawString){
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		return passwordEncoder.isPasswordValid(encodedString, rawString, null);
	}
	@Test
	public void test(){
		System.out.println(SigecCryptoUtil.getInstance().encodeString("qwe123"));
	}
}
