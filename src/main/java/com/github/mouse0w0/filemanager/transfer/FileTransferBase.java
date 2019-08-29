package com.github.mouse0w0.filemanager.transfer;

public abstract class FileTransferBase implements FileTransfer {

    private final Factory factory;

    public FileTransferBase(Factory factory) {
        this.factory = factory;
    }

    @Override
    public Factory getFactory() {
        return factory;
    }
}
