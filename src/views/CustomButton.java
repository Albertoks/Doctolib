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
    private boolean isHeader;
    private boolean rounded;
    private Font font;
    private Map attributes;
    private boolean isSelected;

    public CustomButton(String title, Color color, Boolean rounded, Dimension dimension, Boolean isHeader) {
        super(title);
        this.radius = rounded ? 100 : 0;
        this.isHeader = isHeader;
        this.rounded = rounded;
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(dimension);
        this.setBackground(color);

        this.font = this.getFont();
        this.attributes = font.getAttributes();
        if (isHeader) {
            this.setForeground(Color.WHITE);
        }

        if (!isHeader) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }

        this.setFocusPainted(false);

        if (rounded) {
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            setContentAreaFilled(false);
        }

    }

    public CustomButton(String title, Color color, Dimension dimension, Boolean isHeader) {
        this(title, color, false, dimension, isHeader);
    }

    public CustomButton(ImageIcon icon, Color color, Boolean rounded, Dimension dimension) {
        super(icon);
        this.rounded=rounded;
        this.radius = rounded ? 100 : 0;
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
        if (!rounded) {
            if (getModel().isRollover()) {
                if (!isHeader) {
                    this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                if (!isHeader) {
                    this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        
        }else{
            g.setColor(Color.ORANGE);
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.radius,
            this.radius);
            if (getModel().isRollover()) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else{
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        if(!rounded){
            if (getModel().isRollover()) {
                g2.setStroke(new BasicStroke(2));
            } else {
                g2.setStroke(new BasicStroke(1));
            }
            if (!isHeader)
                g2.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.radius, this.radius);
        }

        g2.setColor(Color.WHITE);
        if(rounded){
            g2.drawRoundRect(0, 0, getSize().width, getSize().height, this.radius, this.radius);
        }
            
    }

    public void setSelected(boolean isSelected) {
        this.isSelected=isSelected;
        if (isSelected) {
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            this.setFont(font.deriveFont(attributes));
        } else {
            attributes.put(TextAttribute.UNDERLINE, -1);
            this.setFont(font.deriveFont(attributes));
        }
    }
    public boolean getIsSelected(){
        return this.isSelected;
    }

    public boolean isRounded() {
        return rounded;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }
    
}