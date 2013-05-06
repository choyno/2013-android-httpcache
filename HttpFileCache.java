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

import java.io.File;

import android.content.Context;

public class HttpFileCache {

    private File cacheDir;
    public Context currentContext;
    
    public HttpFileCache(Context context){
    	currentContext = context;
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"ftgcache");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }
    
    public File getFile(String url){
        String filename=String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
        
    }
    
    public File getFileFromHash(String url){
        File f = new File(cacheDir, url);
        
        
        
        return f;
        
    }
    
    
    public void clear(){
        File[] files=cacheDir.listFiles();
        for(File f:files)
            f.delete();
    }
	
}
