package com.github.mouse0w0.filemanager.ui.quickdrag;

import com.github.mouse0w0.filemanager.file.FileReceiver;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;

public class QuickDragTile {

    private String name;
    private Ikon icon;
    private Color iconColor;
    private Color backGroundColor;
    private FileReceiver receiver;

    public QuickDragTile(String name, Ikon icon, Color iconColor, Color backGroundColor, FileReceiver receiver) {
        this.name = name;
        this.icon = icon;
        this.iconColor = iconColor;
        this.backGroundColor = backGroundColor;
        this.receiver = receiver;
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

    public FileReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(FileReceiver receiver) {
        this.receiver = receiver;
    }
}
