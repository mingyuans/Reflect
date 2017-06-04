package com.mingyuans.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yanxq on 17/6/4.
 */
public class Reflect {

    private Object mObj;
    private Class mClazz;


    public static Reflect onObj(Object obj) {
        return new Reflect(obj, null);
    }

    public static Reflect onName(String className) {
        try {
            Class clazz = Class.forName(className);
            return new Reflect(null, clazz);
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public static Reflect onName(String className, ClassLoader classLoader) {
        try {
            Class clazz = classLoader.loadClass(className);
            return new Reflect(null, clazz);
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public static Reflect onClass(Class<?> clazz) {
        return new Reflect(null, clazz);
    }

    public Reflect(Object obj, Class clazz) {
        if (obj == null && clazz == null) {
            throw new IllegalArgumentException("Obj and clazz must not be null!");
        }
        mObj = obj;
        mClazz = obj == null ? clazz : obj.getClass();
    }

    public FiledReflect field(String filedName) {
        return new FiledReflect(filedName);
    }

    public MethodReflect2 method(String methodName) {
        return new MethodReflect2(methodName);
    }

    public MethodReflect method(String methodName, Class<?>[] paramClazz) {
        return new MethodReflect(methodName, paramClazz);
    }

    public class MethodReflect2 {
        private final String methodName;

        public MethodReflect2(String methodName) {
            this.methodName = methodName;
        }

        public MethodReflect find(Class<?>[] paramClazz) {
            return new MethodReflect(methodName, paramClazz);
        }
    }

    public class MethodReflect {
        private final Method method;

        public MethodReflect(String methodName, Class<?>[] paramClazz) {
            try {
                method = mClazz.getDeclaredMethod(methodName, paramClazz);
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception);
            }
        }

        public void accessible() {
            method.setAccessible(true);
        }

        public Object call(Object... params) {
            try {
                accessible();
                return method.invoke(mObj, (Object[]) params);
            } catch (Exception exception) {
                return new IllegalArgumentException(exception);
            }
        }
    }

    public class FiledReflect {
        private final Field field;

        public FiledReflect(String filedName) {
            try {
                field = mClazz.getDeclaredField(filedName);
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception);
            }
        }

        public void accessible() {
            field.setAccessible(true);
        }

        public void setValue(Object obj) {
            try {
                accessible();
                field.set(mObj, obj);
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception);
            }
        }

        public <T> T getValue() {
            try {
                accessible();
                return (T) field.get(mObj);
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception);
            }
        }

        public Reflect reflectValue() {
            try {
                accessible();
                return Reflect.onObj(field.get(mObj));
            } catch (Exception exception) {
                throw new IllegalArgumentException(exception);
            }
        }
    }

}
