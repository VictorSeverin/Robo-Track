package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;

public class About extends Command {

    public About() {
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // gw.displayAbout();
        Dialog.show("About", "Name: Victor Severin, Course Name: CSC133, Version: 2.0", "OK", "Cancel");
    }
}
