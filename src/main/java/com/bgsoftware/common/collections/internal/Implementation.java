package com.bgsoftware.common.collections.internal;

import java.util.Objects;

public enum Implementation {

    FAST_UTILS {
        @Override
        protected boolean isSupported() {
            try {
                Class.forName("it.unimi.dsi.fastutil.ints.IntList");
                return true;
            } catch (ClassNotFoundException error) {
                return false;
            }
        }
    },
    JAVA {
        @Override
        protected boolean isSupported() {
            return true;
        }
    };

    protected abstract boolean isSupported();

    private static Implementation implementation;

    public static Implementation getImplementation() {
        if (implementation == null) {
            for (Implementation curr : Implementation.values()) {
                if (curr.isSupported()) {
                    implementation = curr;
                    break;
                }
            }
        }

        return Objects.requireNonNull(implementation);
    }

}
