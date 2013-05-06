/*
* Developed by Jonathan da Silva Santos - zerojnt.
* Created at: May 07 2012.
*
* This file is part of HttpCache.
*
* HttpCache is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* HttpCache is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.jonathan.android.httpcache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import android.content.Context;
import br.com.vivo.android.vivotv.TVStarDataManager;

public class HttpRequest {

	HttpMemoryCache memoryCache=new HttpMemoryCache();
	HttpFileCache fileCache;
	public Context context;

	public HttpRequest(Context context){
		fileCache=new HttpFileCache(context);
		this.context = context;
	}

	public String request(String url)
	{
		String result=memoryCache.get(url);
		if(result!=null) {
			return result;
		}else{
			return getResults(url);
		}
	}

	public String getResults(String url)
	{
		File f=fileCache.getFile(url);
		try {
			FileInputStream fs = new FileInputStream(f);

			int ch;
			StringBuffer strContent = new StringBuffer("");
			while( (ch = fs.read()) != -1) strContent.append((char)ch);
			fs.close();

			String result = strContent.toString();
			if(result.length() <= 0) {
				f.delete();
				return null;
			}
			memoryCache.put(url, result);
			return result;

		} catch (FileNotFoundException e) {

			String resultBase = getFromUrl(url, f);

			if(resultBase == null) return null;

			if(resultBase.length() <= 0) {
				return null;
			} else {
				if(writeFileWithString(resultBase, f)) return resultBase; 
			}

		} catch (IOException e) {
			return null;
		}
		return null;
	}

	public String getFromUrl(String url, File f)
	{
		// Please, change this line.
		HttpResponse response = REPLACE_FOR_A_METHOD_THAT_ACCEPTS_A_URL_STRING_AND_RETURNS_A_HTTPRESPONSE(url);

		if(response == null) return null;

		StatusLine status = response.getStatusLine();
		if (status.getStatusCode() != 200) return null;

		HttpEntity entity = response.getEntity();
		InputStream inputStream;
		try {
			inputStream = entity.getContent();

			ByteArrayOutputStream content = new ByteArrayOutputStream();

			int readBytes = 0;
			byte[] sBuffer = new byte[512];
			while ((readBytes = inputStream.read(sBuffer)) != -1) {
				content.write(sBuffer, 0, readBytes);
			}

			String resultBase = new String(content.toByteArray());
			return resultBase;
		}catch (IllegalStateException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean writeFileWithString(String d, File f){
		try {
			f.createNewFile();
		} catch (IOException e1) {
			return false;
		}
		if(f.exists())
		{
			OutputStream fo;
			try {
				fo = new FileOutputStream(f);
				final PrintStream printStream = new PrintStream(fo);
				printStream.print(d);
				printStream.close();
				return true;
			} catch (FileNotFoundException e) {
				return false;
			}              

		}
		return false;
	}

}