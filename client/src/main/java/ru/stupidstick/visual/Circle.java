package ru.stupidstick.visual;


import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Circle extends GrObject {
    double R, R0;
    int ns = 0;

    public Circle() {
    }

    @Override
    public void setParams(int x0, int y0, int r0, int n0, String s0, Component component) {
        super.setParams(x0, y0, r0, n0, s0, component);
        R = R0 = r0;
    }

    @Override
    public String getName() {
        return "Circle";
    }

    public Circle(double r0, Component p0) {
        super(p0);
        R0 = R = r0;
    }

    @Override
    public double size() {
        return R / 2;
    }

    public Circle(int xx, int yy, double r0, Component p0) {
        this(r0, p0);
        x = xx;
        y = yy;
    }

    @Override
    public void paint(boolean fore) {
        super.paint(fore);
        gg.fillOval((int) (x - R / 2), (int) (y - R / 2), (int) R, (int) R);
    }

    @Override
    public void step() {
        R = R0 * (1 + 0.3 * Math.sin(ns / 20.));
        ns++;
    }

    @Override
    public boolean inside(int xx, int yy) {
        return (x - xx) * (x - xx) + (y - yy) * (y - yy) < R * R / 4;
    }

    public void Save(DataOutputStream F) throws IOException {
        super.Save(F);
        F.writeDouble(R0);
    }

    public void Load(DataInputStream F) throws IOException {
        super.Load(F);
        R = R0 = F.readDouble();
        ns = 0;
    }

    public void setComponent(Component p0) {
        super.setComponent(p0);
    }

    public String toString() {
        return super.toString() + " R=" + R;
    }
}
