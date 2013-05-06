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

import java.lang.ref.SoftReference;
import java.util.HashMap;
public class HttpMemoryCache {

    private HashMap<String, SoftReference<String>> cache=new HashMap<String, SoftReference<String>>();
    
    public String get(String id){
        if(!cache.containsKey(id))
            return null;
        SoftReference<String> ref=cache.get(id);
        return ref.get();
    }
    
    public void put(String id, String content){
        cache.put(id, new SoftReference<String>(content));
    }

    public void clear() {
        cache.clear();
    }	
	
}
