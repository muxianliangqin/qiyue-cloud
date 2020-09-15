package com.qiyue.base.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
