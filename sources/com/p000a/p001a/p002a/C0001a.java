package com.p000a.p001a.p002a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.a.a.a.a */
/* compiled from: Expose */
public @interface C0001a {
    /* renamed from: a */
    boolean mo3a() default true;

    /* renamed from: b */
    boolean mo4b() default true;
}
