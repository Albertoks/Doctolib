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
    private Font font;
    private Map attributes;
    private boolean isSelected;
    private Color color;

    public CustomButton(String title, Color color, Dimension dimension, int radius, Boolean isHeader) {
        super(title);
        this.radius = radius;
        this.isHeader = isHeader;
        this.color = color;
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

        if (this.isRounded())
            setContentAreaFilled(false);

    }

    public CustomButton(String title, Color color, Dimension dimension) {
        this(title, color, dimension, 0, false);
    }

    public CustomButton(String title, Color color, Dimension dimension, int radius) {
        this(title, color, dimension, radius, false);
    }

    public CustomButton(String title, Color color, Dimension dimension, Boolean isHeader) {
        this(title, color, dimension, 0, isHeader);
    }

    public CustomButton(ImageIcon icon, Color color, Dimension dimension, int radius) {
        super(icon);
        this.radius = radius;
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(dimension);
        this.setBackground(color);

        if (this.isRounded())
            this.setRounded();
    }

    private void setRounded() {
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        if (!this.isRounded()) {
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

        } else {
            g.setColor(color);
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.radius,
                    this.radius);
            if (getModel().isRollover()) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        if (!this.isRounded()) {
            if (getModel().isRollover()) {
                g2.setStroke(new BasicStroke(2));
            } else {
                g2.setStroke(new BasicStroke(1));
            }
            if (!isHeader)
                g2.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.radius, this.radius);
        }

        g2.setColor(Color.WHITE);
        if (this.isRounded()) {
            g2.drawRoundRect(0, 0, getSize().width, getSize().height, this.radius, this.radius);
        }

    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if (isSelected) {
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            this.setFont(font.deriveFont(attributes));
        } else {
            attributes.put(TextAttribute.UNDERLINE, -1);
            this.setFont(font.deriveFont(attributes));
        }
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public boolean isRounded() {
        return this.radius != 0;
    }
}