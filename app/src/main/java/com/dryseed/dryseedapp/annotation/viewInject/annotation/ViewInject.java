package com.dryseed.dryseedapp.annotation.viewInject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

///	public enum ElementType {
//		/**
//		 * Class, interface or enum declaration.
//		 */
//		TYPE,
//		/**
//		 * Field declaration.
//		 */
//		FIELD,
//		/**
//		 * Method declaration.
//		 */
//		METHOD,
//		/**
//		 * Parameter declaration.
//		 */
//		PARAMETER,
//		/**
//		 * Constructor declaration.
//		 */
//		CONSTRUCTOR,
//		/**
//		 * Local variable declaration.
//		 */
//		LOCAL_VARIABLE,
//		/**
//		 * Annotation type declaration.
//		 */
//		ANNOTATION_TYPE,
//		/**
//		 * Package declaration.
//		 */
//		PACKAGE
//	}


//	public enum RetentionPolicy {
//		/**
//		 * Annotation is only available in the source code.
//		 */
//		SOURCE,
//		/**
//		 * Annotation is available in the source code and in the class file, but not
//		 * at runtime. This is the default policy.
//		 */
//		CLASS,
//		/**
//		 * Annotation is available in the source code, the class file and is
//		 * available at runtime.
//		 */
//		RUNTIME
//	}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    int value();
}
