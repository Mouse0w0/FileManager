package com.github.mouse0w0.filemanager.file;

import java.nio.file.Path;

public interface FileReceiver {

    void onReceive(Path path);
}
