package com.p000a.p001a.p003b;

import java.lang.reflect.Type;

/* renamed from: com.a.a.b.n */
/* compiled from: ConstructorConstructor */
final class C0095n implements C0100s<T> {

    /* renamed from: a */
    final /* synthetic */ Class f149a;

    /* renamed from: b */
    final /* synthetic */ Type f150b;

    /* renamed from: c */
    final /* synthetic */ C0087f f151c;

    /* renamed from: d */
    private final C0078ag f152d = C0078ag.m154a();

    C0095n(C0087f fVar, Class cls, Type type) {
        this.f151c = fVar;
        this.f149a = cls;
        this.f150b = type;
    }

    /* renamed from: a */
    public final T mo91a() {
        try {
            return this.f152d.mo73a(this.f149a);
        } catch (Exception e) {
            throw new RuntimeException("Unable to invoke no-args constructor for " + this.f150b + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
        }
    }
}
