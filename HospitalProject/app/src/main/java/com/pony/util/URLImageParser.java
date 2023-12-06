package com.pony.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.widget.TextView;

public class URLImageParser implements ImageGetter {

	Context c;
	TextView tv_image;

	public URLImageParser(TextView t, Context c) {
		this.tv_image = t;
		this.c = c;
	}

	@Override
	public Drawable getDrawable(String source) {
		// TODO Auto-generated method stub
		URLDrawable urlDrawable = new URLDrawable();
		ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);
		asyncTask.execute(source);
		return urlDrawable;
	}

	public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
		URLDrawable urlDrawable;

		public ImageGetterAsyncTask(URLDrawable d) {
			this.urlDrawable = d;
		}

		@Override
		protected void onPostExecute(Drawable result) {
			if (result != null) {
				urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth(),
						0 + result.getIntrinsicHeight());
				urlDrawable.drawable = result;
				URLImageParser.this.tv_image.invalidate();
			}
		}

		@Override
		protected Drawable doInBackground(String... params) {
			// TODO Auto-generated method stub
			String source = params[0];// 图片URL
			return fetchDrawable(source);
		}

		// 获取URL的Drawable对象
		public Drawable fetchDrawable(String urlString) {
			try {
				InputStream is = fetch(urlString);
				Drawable drawable = Drawable.createFromStream(is, "src");
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			} catch (Exception e) {
				return null;
			}
		}

		private InputStream fetch(String urlString)
				throws MalformedURLException, IOException {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(urlString);
			HttpResponse response = httpClient.execute(request);
			return response.getEntity().getContent();
		}

	}

}
