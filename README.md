android-httpcache
=================

Add caching capabilities in your own requests easily.

Author
======

Developed by Jonathan da Silva Santos - zerojnt.
Created at: May 07 2012.

License
=======

This file is part of HttpCache.

HttpCache is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
 
HttpCache is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
 
You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 

How to use
===========

1:	Change the first line of method getFromUrl(String url, File f) in HttpRequest.java file replacing
  	the sample call method with your own method that returns a HTTPResponse.

2:	Now make requests using the method getResults(String url) from an instance of class HttpRequest.
	It's already prepared for using memory and file cache.

3:	Enjoy it! Any question? You can ask for me: zerojnt (twitter) or zerojnt@gmail.com.

