package ru.stupidstick.visual;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MyString extends GrObject{
    private String text="";
    transient private int count=0;
    transient int fsize=20;
    transient boolean up=true;
    public MyString(String ss){
        text = ss;
        }
    public MyString(){
        }
    @Override
    public void setParams(int x0, int y0, int r0, int n0, String s0, Component component) {
        super.setParams(x0, y0, r0, n0, s0, component);
        text = s0;
        gg.setFont(new Font("Arial",Font.BOLD,50));
        }
    @Override
    void step() {

        }

    @Override
    double size() {
        return 0;
        }
    @Override
    public String getName() {
        return "String";
    }

    @Override
    boolean inside(int xx, int yy) {
        return Math.abs(yy-y)<50 && Math.abs(xx-(x-text.length()/2))<100;
        }

    @Override
    void Save(DataOutputStream F) throws IOException {
        super.Save(F);
        F.writeUTF(text);
        }

    @Override
    void Load(DataInputStream F) throws IOException {
        super.Load(F);
        text = F.readUTF();
        }

    @Override
    public void paint(boolean fore) {
        super.paint(fore);
        int ll = text.length();
        count++;
        if (count%10==0){
            if (up) fsize++; else fsize--;
            if (up && fsize > 50 || !up && fsize<20)
                up = !up;
            }
        if (count%100==0){
            text = text.substring(ll-1)+text.substring(0,ll-1);
            }
        gg.setFont(new Font("Arial",Font.BOLD,fsize));
        gg.drawString(text,(int)(x-text.length()/2),(int)y);
        }

    @Override
    public String toString() {
        return super.toString();
        }
}
