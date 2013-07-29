/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wicketstuff.rest.testJsonRequest;

import org.wicketstuff.rest.contenthandling.IObjectSerialDeserial;
import org.wicketstuff.rest.contenthandling.RestMimeTypes;

public class TestJsonDesSer implements IObjectSerialDeserial {
	static public Object getObject(){
		return RestResourceFullAnnotated.createTestPerson();
	}
	
	static public String getJSON(){
		return "{\"name\" : \"Mary\", \"surname\" : \"Smith\", \"email\" : \"m.smith@gmail.com\"}";
	}

	@Override
	public String objectToString(Object targetObject, RestMimeTypes format) {
		if(format == RestMimeTypes.PLAIN_TEXT)
			return targetObject.toString();
		
		return getJSON();
	}

	@Override
	public <T> T stringToObject(String source, Class<T> targetClass, RestMimeTypes format) {
		return (T) getObject();
	}
}
