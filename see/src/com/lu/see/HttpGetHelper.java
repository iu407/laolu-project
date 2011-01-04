package com.lu.see;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

import android.content.Context;

public class HttpGetHelper {
	Context context;

	URL uri;
	URLConnection uconnection;

	BufferedInputStream bis;

	ByteArrayBuffer baf;

	public HttpGetHelper(Context c, String address, int size)
			throws IOException {
		context = c;

		uri = new URL(address);
		uconnection = uri.openConnection();

		bis = new BufferedInputStream(uconnection.getInputStream());

		baf = new ByteArrayBuffer(size);
	}

	public ByteArrayBuffer read() throws IOException {
		int current = 0;
		baf.clear();

		while ((current = bis.read()) != -1) {
			baf.append((byte) current);
		}

		return baf;
	}

	public String encode(ByteArrayBuffer buffer) {
		return EncodingUtils.getString(buffer.toByteArray(), "UTF-8");
	}
}
