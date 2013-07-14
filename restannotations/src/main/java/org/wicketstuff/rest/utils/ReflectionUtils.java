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
package org.wicketstuff.rest.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.wicketstuff.rest.annotations.parameters.AnnotatedParam;
import org.wicketstuff.rest.annotations.parameters.RequestBody;
import org.wicketstuff.rest.annotations.parameters.RequestParam;

public class ReflectionUtils {
	/**
	 * Check if a parameter is annotated with a given annotation.
	 * 
	 * @param i
	 *            function parameter index
	 * @param method
	 *            the method the parameter belongs to.
	 * @param targetAnnotation
	 *            the annotation type we want to check for.
	 * 
	 * @return true if the method parameter is annotated with the given
	 *         annotation, false otherwise.
	 */
	static public boolean isParameterAnnotatedWith(int i, Method method,
			Class<? extends Annotation> targetAnnotation) {
		Annotation[][] parametersAnnotations = method.getParameterAnnotations();

		if (parametersAnnotations.length == 0)
			return false;

		Annotation[] parameterAnnotations = parametersAnnotations[i];

		for (int j = 0; j < parameterAnnotations.length; j++) {
			Annotation annotation = parameterAnnotations[j];
			if (targetAnnotation.isInstance(annotation))
				return true;
		}
		return false;
	}

	/**
	 * Check if a parameter is annotated with an AnnotatedParam annotation.
	 * 
	 * @param i
	 *            function parameter index
	 * @param method
	 *            the method the parameter belongs to.
	 * 
	 * @return true if such an annotation is found, false otherwise.
	 */
	static public boolean isParameterAnnotatedWithAnnotatedParam(int i, Method method) {
		Annotation[][] parametersAnnotations = method.getParameterAnnotations();

		if (parametersAnnotations.length == 0)
			return false;

		Annotation[] parameterAnnotations = parametersAnnotations[i];

		for (int j = 0; j < parameterAnnotations.length; j++) {
			Annotation annotation = parameterAnnotations[j];
			AnnotatedParam isAnnotatedParam = annotation.annotationType().getAnnotation(
					AnnotatedParam.class);

			if (isAnnotatedParam != null)
				return true;
		}

		return false;
	}

	/**
	 * Utility method to find if an annotation type is present in an array of
	 * annotations.
	 * 
	 * @param parameterAnnotations
	 *            the array of annotations we will look in.
	 * @param targetAnnotation
	 *            the type of annotation we are looking for.
	 * @return the first occurrence of the targetAnnotation found in the array,
	 *         null if no occurrence is found
	 */
	static public <T extends Annotation> T findAnnotation(Annotation[] parameterAnnotations,
			Class<T> targetAnnotation) {

		for (int i = 0; i < parameterAnnotations.length; i++) {
			Annotation annotation = parameterAnnotations[i];

			if (targetAnnotation.isInstance(annotation))
				return targetAnnotation.cast(annotation);
		}

		return null;
	}
}
