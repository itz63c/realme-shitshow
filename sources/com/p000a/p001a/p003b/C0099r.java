package com.p000a.p001a.p003b;

import java.math.BigInteger;

/* renamed from: com.a.a.b.r */
/* compiled from: LazilyParsedNumber */
public final class C0099r extends Number {

    /* renamed from: a */
    private final String f167a;

    public C0099r(String str) {
        this.f167a = str;
    }

    public final int intValue() {
        try {
            return Integer.parseInt(this.f167a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.f167a);
            } catch (NumberFormatException e2) {
                return new BigInteger(this.f167a).intValue();
            }
        }
    }

    public final long longValue() {
        try {
            return Long.parseLong(this.f167a);
        } catch (NumberFormatException e) {
            return new BigInteger(this.f167a).longValue();
        }
    }

    public final float floatValue() {
        return Float.parseFloat(this.f167a);
    }

    public final double doubleValue() {
        return Double.parseDouble(this.f167a);
    }

    public final String toString() {
        return this.f167a;
    }
}
