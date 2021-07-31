package com.p000a.p001a.p003b;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/* renamed from: com.a.a.b.b */
/* compiled from: $Gson$Types */
public final class C0083b {

    /* renamed from: a */
    static final Type[] f131a = new Type[0];

    /* renamed from: f */
    private static GenericArrayType m172f(Type type) {
        return new C0084c(type);
    }

    /* renamed from: a */
    public static Type m161a(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new C0084c(m161a((Type) cls.getComponentType())) : cls;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C0085d(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new C0084c(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new C0086e(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    /* renamed from: b */
    public static Class<?> m166b(Type type) {
        Type type2 = type;
        while (!(type2 instanceof Class)) {
            if (type2 instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type2).getRawType();
                C0013a.m12a(rawType instanceof Class);
                return (Class) rawType;
            } else if (type2 instanceof GenericArrayType) {
                return Array.newInstance(m166b(((GenericArrayType) type2).getGenericComponentType()), 0).getClass();
            } else {
                if (type2 instanceof TypeVariable) {
                    return Object.class;
                }
                if (type2 instanceof WildcardType) {
                    type2 = ((WildcardType) type2).getUpperBounds()[0];
                } else {
                    throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type2 + "> is of type " + (type2 == null ? "null" : type2.getClass().getName()));
                }
            }
        }
        return (Class) type2;
    }

    /* renamed from: a */
    public static boolean m165a(Type type, Type type2) {
        boolean z;
        Type type3 = type2;
        Type type4 = type;
        while (type4 != type3) {
            if (type4 instanceof Class) {
                return type4.equals(type3);
            }
            if (type4 instanceof ParameterizedType) {
                if (!(type3 instanceof ParameterizedType)) {
                    return false;
                }
                ParameterizedType parameterizedType = (ParameterizedType) type4;
                ParameterizedType parameterizedType2 = (ParameterizedType) type3;
                Type ownerType = parameterizedType.getOwnerType();
                Type ownerType2 = parameterizedType2.getOwnerType();
                if (ownerType == ownerType2 || (ownerType != null && ownerType.equals(ownerType2))) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                    return false;
                }
                return true;
            } else if (type4 instanceof GenericArrayType) {
                if (!(type3 instanceof GenericArrayType)) {
                    return false;
                }
                type4 = ((GenericArrayType) type4).getGenericComponentType();
                type3 = ((GenericArrayType) type3).getGenericComponentType();
            } else if (type4 instanceof WildcardType) {
                if (!(type3 instanceof WildcardType)) {
                    return false;
                }
                WildcardType wildcardType = (WildcardType) type4;
                WildcardType wildcardType2 = (WildcardType) type3;
                return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
            } else if (!(type4 instanceof TypeVariable)) {
                return false;
            } else {
                if (!(type3 instanceof TypeVariable)) {
                    return false;
                }
                TypeVariable typeVariable = (TypeVariable) type4;
                TypeVariable typeVariable2 = (TypeVariable) type3;
                return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
            }
        }
        return true;
    }

    /* renamed from: c */
    public static String m169c(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    /* renamed from: a */
    private static Type m163a(Type type, Class<?> cls, Class<?> cls2) {
        Class<? super Object> cls3 = cls;
        Type type2 = type;
        while (cls2 != cls3) {
            if (cls2.isInterface()) {
                Class<? super Object>[] interfaces = cls3.getInterfaces();
                int i = 0;
                int length = interfaces.length;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (interfaces[i] == cls2) {
                        return cls3.getGenericInterfaces()[i];
                    } else {
                        if (cls2.isAssignableFrom(interfaces[i])) {
                            type2 = cls3.getGenericInterfaces()[i];
                            cls3 = interfaces[i];
                            break;
                        }
                        i++;
                    }
                }
            }
            if (cls3.isInterface()) {
                return cls2;
            }
            while (cls3 != Object.class) {
                Class<? super Object> superclass = cls3.getSuperclass();
                if (superclass == cls2) {
                    return cls3.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    type2 = cls3.getGenericSuperclass();
                    cls3 = superclass;
                } else {
                    cls3 = superclass;
                }
            }
            return cls2;
        }
        return type2;
    }

    /* renamed from: b */
    private static Type m167b(Type type, Class<?> cls, Class<?> cls2) {
        C0013a.m12a(cls2.isAssignableFrom(cls));
        return m164a(type, cls, m163a(type, cls, cls2));
    }

    /* renamed from: d */
    public static Type m170d(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    /* renamed from: a */
    public static Type m162a(Type type, Class<?> cls) {
        Type b = m167b(type, cls, Collection.class);
        if (b instanceof WildcardType) {
            b = ((WildcardType) b).getUpperBounds()[0];
        }
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    /* renamed from: b */
    public static Type[] m168b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type b = m167b(type, cls, Map.class);
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0039 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type m164a(java.lang.reflect.Type r10, java.lang.Class<?> r11, java.lang.reflect.Type r12) {
        /*
            r4 = 1
            r3 = 0
            r0 = r12
        L_0x0003:
            boolean r1 = r0 instanceof java.lang.reflect.TypeVariable
            if (r1 == 0) goto L_0x0048
            r1 = r0
            java.lang.reflect.TypeVariable r1 = (java.lang.reflect.TypeVariable) r1
            java.lang.reflect.GenericDeclaration r0 = r1.getGenericDeclaration()
            boolean r2 = r0 instanceof java.lang.Class
            if (r2 == 0) goto L_0x003a
            java.lang.Class r0 = (java.lang.Class) r0
            r2 = r0
        L_0x0015:
            if (r2 == 0) goto L_0x0046
            java.lang.reflect.Type r0 = m163a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.Class<?>) r2)
            boolean r5 = r0 instanceof java.lang.reflect.ParameterizedType
            if (r5 == 0) goto L_0x0046
            java.lang.reflect.TypeVariable[] r5 = r2.getTypeParameters()
            r2 = r3
        L_0x0024:
            int r6 = r5.length
            if (r2 >= r6) goto L_0x0040
            r6 = r5[r2]
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x003d
            java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0
            java.lang.reflect.Type[] r0 = r0.getActualTypeArguments()
            r0 = r0[r2]
        L_0x0037:
            if (r0 != r1) goto L_0x0003
        L_0x0039:
            return r0
        L_0x003a:
            r0 = 0
            r2 = r0
            goto L_0x0015
        L_0x003d:
            int r2 = r2 + 1
            goto L_0x0024
        L_0x0040:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r0.<init>()
            throw r0
        L_0x0046:
            r0 = r1
            goto L_0x0037
        L_0x0048:
            boolean r1 = r0 instanceof java.lang.Class
            if (r1 == 0) goto L_0x0066
            r1 = r0
            java.lang.Class r1 = (java.lang.Class) r1
            boolean r1 = r1.isArray()
            if (r1 == 0) goto L_0x0066
            java.lang.Class r0 = (java.lang.Class) r0
            java.lang.Class r1 = r0.getComponentType()
            java.lang.reflect.Type r2 = m164a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.reflect.Type) r1)
            if (r1 == r2) goto L_0x0039
            java.lang.reflect.GenericArrayType r0 = m172f(r2)
            goto L_0x0039
        L_0x0066:
            boolean r1 = r0 instanceof java.lang.reflect.GenericArrayType
            if (r1 == 0) goto L_0x007b
            java.lang.reflect.GenericArrayType r0 = (java.lang.reflect.GenericArrayType) r0
            java.lang.reflect.Type r1 = r0.getGenericComponentType()
            java.lang.reflect.Type r2 = m164a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.reflect.Type) r1)
            if (r1 == r2) goto L_0x0039
            java.lang.reflect.GenericArrayType r0 = m172f(r2)
            goto L_0x0039
        L_0x007b:
            boolean r1 = r0 instanceof java.lang.reflect.ParameterizedType
            if (r1 == 0) goto L_0x00be
            java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0
            java.lang.reflect.Type r1 = r0.getOwnerType()
            java.lang.reflect.Type r7 = m164a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.reflect.Type) r1)
            if (r7 == r1) goto L_0x00af
            r1 = r4
        L_0x008c:
            java.lang.reflect.Type[] r5 = r0.getActualTypeArguments()
            int r8 = r5.length
            r6 = r3
        L_0x0092:
            if (r6 >= r8) goto L_0x00b1
            r2 = r5[r6]
            java.lang.reflect.Type r9 = m164a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.reflect.Type) r2)
            r2 = r5[r6]
            if (r9 == r2) goto L_0x0107
            if (r1 != 0) goto L_0x0104
            java.lang.Object r1 = r5.clone()
            java.lang.reflect.Type[] r1 = (java.lang.reflect.Type[]) r1
            r2 = r4
            r3 = r1
        L_0x00a8:
            r3[r6] = r9
        L_0x00aa:
            int r6 = r6 + 1
            r1 = r2
            r5 = r3
            goto L_0x0092
        L_0x00af:
            r1 = r3
            goto L_0x008c
        L_0x00b1:
            if (r1 == 0) goto L_0x0039
            java.lang.reflect.Type r1 = r0.getRawType()
            com.a.a.b.d r0 = new com.a.a.b.d
            r0.<init>(r7, r1, r5)
            goto L_0x0039
        L_0x00be:
            boolean r1 = r0 instanceof java.lang.reflect.WildcardType
            if (r1 == 0) goto L_0x0039
            java.lang.reflect.WildcardType r0 = (java.lang.reflect.WildcardType) r0
            java.lang.reflect.Type[] r1 = r0.getLowerBounds()
            java.lang.reflect.Type[] r2 = r0.getUpperBounds()
            int r5 = r1.length
            if (r5 != r4) goto L_0x00ea
            r2 = r1[r3]
            java.lang.reflect.Type r2 = m164a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.reflect.Type) r2)
            r1 = r1[r3]
            if (r2 == r1) goto L_0x0039
            com.a.a.b.e r0 = new com.a.a.b.e
            java.lang.reflect.Type[] r1 = new java.lang.reflect.Type[r4]
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            r1[r3] = r5
            java.lang.reflect.Type[] r4 = new java.lang.reflect.Type[r4]
            r4[r3] = r2
            r0.<init>(r1, r4)
            goto L_0x0039
        L_0x00ea:
            int r1 = r2.length
            if (r1 != r4) goto L_0x0039
            r1 = r2[r3]
            java.lang.reflect.Type r1 = m164a((java.lang.reflect.Type) r10, (java.lang.Class<?>) r11, (java.lang.reflect.Type) r1)
            r2 = r2[r3]
            if (r1 == r2) goto L_0x0039
            com.a.a.b.e r0 = new com.a.a.b.e
            java.lang.reflect.Type[] r2 = new java.lang.reflect.Type[r4]
            r2[r3] = r1
            java.lang.reflect.Type[] r1 = f131a
            r0.<init>(r2, r1)
            goto L_0x0039
        L_0x0104:
            r2 = r1
            r3 = r5
            goto L_0x00a8
        L_0x0107:
            r2 = r1
            r3 = r5
            goto L_0x00aa
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.p003b.C0083b.m164a(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type):java.lang.reflect.Type");
    }

    /* renamed from: e */
    static /* synthetic */ void m171e(Type type) {
        boolean z;
        if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            z = true;
        } else {
            z = false;
        }
        C0013a.m12a(z);
    }

    /* renamed from: a */
    static /* synthetic */ int m160a(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
