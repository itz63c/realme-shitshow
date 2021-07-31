package neton;

import com.coloros.neton.NetonException;
import com.coloros.neton.ProgressCallBack;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import neton.MultipartBody;
import neton.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

public abstract class RequestBody {
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink);

    public long contentLength() {
        return -1;
    }

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(Map<String, Object> map, final ProgressCallBack progressCallBack) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (!(obj instanceof File)) {
                builder.addFormDataPart(next, obj.toString());
            } else {
                final File file = (File) obj;
                builder.addFormDataPart(next, file.getName(), new RequestBody() {
                    public final MediaType contentType() {
                        return MediaType.parse(MediaType.OCTET_STREAM);
                    }

                    public final long contentLength() {
                        return file.length();
                    }

                    public final void writeTo(BufferedSink bufferedSink) {
                        try {
                            Source source = Okio.source(file);
                            Buffer buffer = new Buffer();
                            long contentLength = contentLength();
                            long j = 0;
                            while (true) {
                                long read = source.read(buffer, 2048);
                                if (read != -1) {
                                    bufferedSink.write(buffer, read);
                                    j += read;
                                    if (progressCallBack != null) {
                                        progressCallBack.onProgress(contentLength, j);
                                    }
                                } else {
                                    return;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
        return builder.build();
    }

    public static RequestBody create(Map<String, Object> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (!(obj instanceof File)) {
                builder.addFormDataPart(next, obj.toString());
            } else {
                File file = (File) obj;
                builder.addFormDataPart(next, file.getName(), create(MediaType.parse(MediaType.OCTET_STREAM), file));
            }
        }
        return builder.build();
    }

    public static RequestBody create(final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() {
            public final MediaType contentType() {
                return MediaType.this;
            }

            public final long contentLength() {
                return (long) byteString.size();
            }

            public final void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NetonException((Throwable) new NullPointerException("content == null"));
        }
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        return new RequestBody() {
            public final MediaType contentType() {
                return MediaType.this;
            }

            public final long contentLength() {
                return (long) i2;
            }

            public final void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(bArr, i, i2);
            }
        };
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                public final MediaType contentType() {
                    return MediaType.this;
                }

                public final long contentLength() {
                    return file.length();
                }

                public final void writeTo(BufferedSink bufferedSink) {
                    Source source = null;
                    try {
                        source = Okio.source(file);
                        bufferedSink.writeAll(source);
                    } finally {
                        Util.closeQuietly((Closeable) source);
                    }
                }
            };
        }
        throw new NetonException((Throwable) new NullPointerException("content == null"));
    }

    public String toString() {
        try {
            return "RequestBody{contentType:" + contentType() + ",length:" + contentLength() + "}";
        } catch (IOException e) {
            return super.toString();
        }
    }
}
