package src.views;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import java.awt.BasicStroke;
import java.awt.*;
import java.awt.font.TextAttribute;


public class CustomButton extends JButton {
    private int radius;
    private boolean notHeaderButton;
    private boolean rounded;

    public CustomButton(String title, Color color, Boolean rounded, Dimension dimension, Boolean notHeaderButton) {
        super(title);
        this.radius = rounded ? 100 : 0;
        this.notHeaderButton=notHeaderButton;
        this.rounded = rounded;
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(dimension);
        this.setBackground(color);

        if (notHeaderButton){
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
        if(! notHeaderButton){
            this.setForeground(Color.WHITE);
            Font font = this.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            this.setFont(font.deriveFont(attributes));
        }
           
        
        this.setFocusPainted(false);

        if (rounded) {
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            setContentAreaFilled(false);
        }
        
    }

    public CustomButton(String title, Color color,Dimension dimension, Boolean notHeaderButton) {
        this(title, color, false,dimension,notHeaderButton);
    }

    public CustomButton(ImageIcon icon, Color color, Boolean rounded, Dimension dimension) {
        super(icon);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(dimension);
        this.setBackground(color);

        if (rounded) {
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            setContentAreaFilled(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        if(! rounded){
            if (getModel().isRollover()) {
                if(notHeaderButton){
                    this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                if(notHeaderButton){
                    this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        

        // g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.radius,
        // this.radius);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        if (getModel().isRollover()) {
            g2.setStroke(new BasicStroke(2));
        }
        else{
            g2.setStroke(new BasicStroke(1));
        }
        if(notHeaderButton)
            g2.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.radius, this.radius);
    }

    public void setHover() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public void removeHover() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}