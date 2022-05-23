package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.views.Header;

public class HeaderController implements ActionListener{
    private Header header;

    public HeaderController(Header header){
        this.header = header;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.header.getMesRdv()) {
            
        } else if (e.getSource() == this.header.getPrendreRdv()) {
            
        }
    }

}
