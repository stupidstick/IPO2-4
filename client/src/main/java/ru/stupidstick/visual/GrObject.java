package ru.stupidstick.visual;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author User
 */
public abstract class GrObject implements Runnable, Serializable {
    double x, y, dx, dy;
    double V0 = 0.7;
    transient Color cl;
    int color = 0;
    transient int maxx, maxy;
    transient volatile boolean stop = false;
    transient volatile boolean pause = false;
    transient Graphics gg;
    transient Component pp;

    public GrObject() {
        cl = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        color = cl.getRGB();
    }

    abstract public String getName();

    public void setParams(int x0, int y0, int r0, int n0, String s0, Component component) {
        x = x0;
        y = y0;
        setComponent(component);
    }

    public GrObject(Component p0) {
        if (p0 != null) {
            pp = p0;
            maxx = pp.getWidth();
            maxy = pp.getHeight();
            gg = pp.getGraphics();
            x = Math.random() * maxx;
            y = Math.random() * maxy;
        }
        dx = Math.random() * V0;
        dy = Math.random() * V0;
        cl = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        color = cl.getRGB();
    }

    public void setComponent(Component p0) {
        pp = p0;
        gg = pp.getGraphics();
        maxx = pp.getWidth();
        maxy = pp.getHeight();
    }

    void Save(DataOutputStream F) throws IOException {
        F.writeDouble(x);
        F.writeDouble(y);
        F.writeDouble(dx);
        F.writeDouble(dy);
        F.writeInt(cl.getRGB());
    }

    void Load(DataInputStream F) throws IOException {
        x = F.readDouble();
        y = F.readDouble();
        dx = F.readDouble();
        dy = F.readDouble();
        color = F.readInt();
        cl = new Color(color);
    }

    //------------------------------------------------------------------------------------
    abstract void step();

    abstract double size();

    abstract boolean inside(int xx, int yy);

    //-------------------------------------------------------------------------------------
    public void paint(boolean fore) {
        if (fore) gg.setColor(Color.white);
        else gg.setColor(cl);
    }

    public void run() {
        while (!stop) {
            try {
                Thread.sleep(10);
                synchronized (pp) {
                    if (pause) {
                        pause = false;
                        pp.wait();
                    }
                    paint(true);
                    step();
                    x += dx;
                    y += dy;
                    double sz = size();
                    if (dx > 0 && x + sz > maxx || dx < 0 && x < sz) dx = -dx;
                    if (dy > 0 && y + sz > maxy || dy < 0 && y < sz) dy = -dy;
                    paint(false);
                }
            } catch (InterruptedException ex) {
            }
        }
        paint(true);
    }

    public String toString() {
        return "x=" + x + " y=" + y;
    }
}
