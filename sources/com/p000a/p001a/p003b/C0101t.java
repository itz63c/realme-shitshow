package com.p000a.p001a.p003b;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.a.b.t */
/* compiled from: Primitives */
public final class C0101t {

    /* renamed from: a */
    private static final Map<Class<?>, Class<?>> f168a;

    /* renamed from: b */
    private static final Map<Class<?>, Class<?>> f169b;

    static {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        m196a(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        m196a(hashMap, hashMap2, Byte.TYPE, Byte.class);
        m196a(hashMap, hashMap2, Character.TYPE, Character.class);
        m196a(hashMap, hashMap2, Double.TYPE, Double.class);
        m196a(hashMap, hashMap2, Float.TYPE, Float.class);
        m196a(hashMap, hashMap2, Integer.TYPE, Integer.class);
        m196a(hashMap, hashMap2, Long.TYPE, Long.class);
        m196a(hashMap, hashMap2, Short.TYPE, Short.class);
        m196a(hashMap, hashMap2, Void.TYPE, Void.class);
        f168a = Collections.unmodifiableMap(hashMap);
        f169b = Collections.unmodifiableMap(hashMap2);
    }

    /* renamed from: a */
    private static void m196a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    /* renamed from: a */
    public static boolean m197a(Type type) {
        return f168a.containsKey(type);
    }

    /* renamed from: a */
    public static <T> Class<T> m195a(Class<T> cls) {
        Class<T> cls2 = f168a.get(C0013a.m11a(cls));
        return cls2 == null ? cls : cls2;
    }
}
