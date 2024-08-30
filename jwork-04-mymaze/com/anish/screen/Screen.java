package com.anish.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.World;

public interface Screen {

    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}
