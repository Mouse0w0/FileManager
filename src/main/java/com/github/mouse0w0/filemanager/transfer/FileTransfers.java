package com.github.mouse0w0.filemanager.transfer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTransfers {

    private static final Map<String, FileTransfer.Factory> REGISTERED_TRANSFERS = new HashMap<>();

    static {
        registerFactory(FileTransferNormal.Factory.INSTANCE);
    }

    public static void registerFactory(FileTransfer.Factory factory) {
        REGISTERED_TRANSFERS.put(factory.getName(), factory);
    }

    public static FileTransfer.Factory getFactory(String name) {
        return REGISTERED_TRANSFERS.get(name);
    }

    public static Set<String> getFactoryNames() {
        return REGISTERED_TRANSFERS.keySet();
    }

}
