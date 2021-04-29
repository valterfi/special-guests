package com.valterfi.specialguests.utils;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public final class ImageUtils {

	private ImageUtils() {

	}

	public static String convertToBase64(MultipartFile file) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/jpg;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false)));
		return sb.toString();
	}

}
