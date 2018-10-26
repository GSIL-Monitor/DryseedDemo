package com.dryseed.dryseedapp.framework.volley.simple.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

public class PostUploadImageRequest extends Request<JSONObject> {

	final static private int IMAGE_SIZE_MAX = 75 * 1024;
	final static private String PREFIX = "--";
	final static private String LINEND = "\r\n";
	final static private String BOUNDARY = UUID.randomUUID().toString();

	private Context mContext;

	private Response.Listener mResponseListener;

	private Map<String, Uri> mImageList;
	private Map<String, String> mParamList;

	public PostUploadImageRequest(Context context,
								  String url,
								  Map<String, String> paramList,
								  Map<String, Uri> imageList,
								  Response.Listener responseListener,
								  Response.ErrorListener errorListener) {
		super(Method.POST, url, errorListener);

		mContext = context;
		mImageList = imageList;
		mParamList = paramList;
		mResponseListener = responseListener;

		setShouldCache(false);
		setRetryPolicy(new DefaultRetryPolicy(60 * 1000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String string = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(new JSONObject(string),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException ex) {
			return Response.error(new ParseError(ex));
		}
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		mResponseListener.onResponse(response);
	}

	/*@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Account account = ILogin.getActiveAccount();

		Cookie c = new Cookie();
		if (account != null) {
			c.set("uid", account.getUid());
			c.set("username", account.getUserName());
			c.set("tokenid", account.getSessonId());
		}

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Cookie", c.toString());
		headers.put("Charset", "UTF-8");
		headers.put("Accept-Encoding", "gzip");

		return headers;
	}*/

	@Override
	public byte[] getBody() throws AuthFailureError {
		if (mParamList == null || mParamList.size() == 0) {
			return super.getBody();
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : mParamList.entrySet()) {
			builder.append(PREFIX + BOUNDARY);
			builder.append(LINEND);
			builder.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
			builder.append(LINEND) ;
			builder.append("Content-Type: text/plain; charset=\"utf-8\"");
			builder.append(LINEND);
			builder.append("Content-Transfer-Encoding: 8bit");
			builder.append(LINEND);
			builder.append(LINEND);
			builder.append(entry.getValue());
			builder.append(LINEND);
		}
		try {
			bos.write(builder.toString().getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (mImageList != null && mImageList.size() > 0) {
			for (Map.Entry<String, Uri> entry : mImageList.entrySet()) {
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX + BOUNDARY);
				sb.append(LINEND);
				sb.append("Content-Disposition: form-data; name=\"files\"");
				sb.append("; filename=\"image" + System.currentTimeMillis() + ".png\"");
				sb.append(LINEND);
				sb.append("Content-Type: image/png");
				sb.append(LINEND);
				sb.append(LINEND);
				try {
					bos.write(sb.toString().getBytes("utf-8"));
					Uri uri = entry.getValue();
					Bitmap bmp = null;
					try {
						bmp = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (bmp != null) {
						int bmpW = bmp.getWidth();
						int bmpH = bmp.getHeight();
						float rat = Math.min(720f / bmpW, 960f / bmpH);
						Bitmap newBmp = bmp;
						if (rat < 1) {
							Matrix matrix = new Matrix();
							matrix.postScale(rat, rat);
							newBmp = Bitmap.createBitmap(bmp, 0, 0, bmpW, bmpH, matrix, true);
						}
						int loop = 0;
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						newBmp.compress(Bitmap.CompressFormat.JPEG, 85, baos);
						while (baos.size() > IMAGE_SIZE_MAX && ++loop < 5) {
							baos.reset();
							newBmp.compress(Bitmap.CompressFormat.JPEG, 85 - 15 * loop, baos);
						}
						bos.write(baos.toByteArray());
						bos.write(LINEND.getBytes("utf-8"));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				bos.write((PREFIX + BOUNDARY + PREFIX + LINEND).getBytes("utf-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(PREFIX + BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"files\"");
			sb.append("; filename=\"\"");
			sb.append(LINEND);
			sb.append("Content-Type: image/png");
			sb.append(LINEND);
			sb.append(LINEND);
			try {
				bos.write(sb.toString().getBytes("utf-8"));
				bos.write(LINEND.getBytes("utf-8"));
				bos.write((PREFIX + BOUNDARY + PREFIX + LINEND).getBytes("utf-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bos.toByteArray();
	}

	@Override
	public String getBodyContentType() {
		return "multipart/form-data; boundary=" + BOUNDARY;
	}
}