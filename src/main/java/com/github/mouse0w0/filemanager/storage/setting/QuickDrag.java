package com.github.mouse0w0.filemanager.storage.setting;

import com.github.mouse0w0.filemanager.transfer.FileTransfer;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;

public class QuickDrag {

    private String name;
    private Ikon icon;
    private Color iconColor;
    private Color backGroundColor;
    private FileTransfer transfer;

    public QuickDrag() {
    }

    public QuickDrag(String name, Ikon icon, Color iconColor, Color backGroundColor, FileTransfer transfer) {
        this.name = name;
        this.icon = icon;
        this.iconColor = iconColor;
        this.backGroundColor = backGroundColor;
        this.transfer = transfer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ikon getIcon() {
        return icon;
    }

    public void setIcon(Ikon icon) {
        this.icon = icon;
    }

    public Color getIconColor() {
        return iconColor;
    }

    public void setIconColor(Color iconColor) {
        this.iconColor = iconColor;
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public FileTransfer getTransfer() {
        return transfer;
    }

    public void setTransfer(FileTransfer transfer) {
        this.transfer = transfer;
    }
}
