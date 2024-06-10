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
/**
 *
 * @author User
 */
public class Zwezda extends GrObject{
    double R,FI,df;
    int N;
    double V00=0.05;
    transient double xx[],yy[];
    transient double PI2=2*Math.PI;
    public Zwezda(){}
    public Zwezda(double r0,int n0, Component p0){
        super(p0);
        R=r0;
        N=n0;
        FI=0;
        df=Math.random()*V00;
        xx=new double[N];
        yy=new double[N];
        }
    public Zwezda(int xx,int yy,double r0,int n0,Component p0){
        this(r0,n0,p0);
        x=xx; y=yy;
        }
    @Override
    public void paint(boolean fore){
        super.paint(fore);
        for (int i=0;i<N;i++)
            gg.drawLine((int)(x+xx[i]), (int)(y+yy[i]), (int)(x+xx[(i+2)%N]), (int)(y+yy[(i+2)%N]));
        }
    @Override
    public boolean inside(int xx,int yy){ return (x-xx)*(x-xx)+(y-yy)*(y-yy) < R*R;}
    @Override
    public void step(){
        FI+=df;
        for (int i=0;i<N;i++){
            xx[i]=R*Math.cos(FI+i*PI2/N);
            yy[i]=R*Math.sin(FI+i*PI2/N);
            }
        }
    @Override
    public double size(){return R; }
    public void Save(DataOutputStream F) throws IOException{
        super.Save(F);
        F.writeDouble(R);
        F.writeDouble(FI);
        F.writeDouble(df);
        F.writeInt(N);
        }

    @Override
    public void setParams(int x0, int y0, int r0, int n0, String s0, Component component) {
        super.setParams(x0, y0, r0, n0, s0, component);
        R=r0;
        N=n0;
        FI=0;
        df=Math.random()*V00;
        xx=new double[N];
        yy=new double[N];
        }

    @Override
    public String getName() {
        return "Star";
        }

    public void Load(DataInputStream F) throws IOException{
        super.Load(F);
        R=F.readDouble();
        FI=F.readDouble();
        df=F.readDouble();
        N=F.readInt();
        xx=new double[N];
        yy=new double[N];
        }

    public void setComponent(Component p0){
        super.setComponent(p0);
        xx=new double[N];
        yy=new double[N];
        }    
}
