package ru;

import com.sun.org.apache.bcel.internal.generic.DUP_X1;
import sun.awt.X11.XAWTIcon32_security_icon_yellow16_png;
import sun.security.pkcs11.wrapper.CK_X9_42_DH1_DERIVE_PARAMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Frame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 440;
    public static int screenWidth, screenHeight,i,n;
    public static final String Name = "Palygon";
    public static JPanel panel;
    public static int Ax, Ay,xk,yk,count, Bx, By, Cx, Cy, Dx, Dy;
    public static final Position[] massp = new Position[100];
    public Frame()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        setLocation((screenWidth / 2)-DEFAULT_WIDTH/2, (screenHeight / 2)-DEFAULT_HEIGHT/2); //?????????????? ????????? ??????
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); //?????????????? ??????? ??????
        setTitle(Name); //?????????????? ???
        setResizable(true); //?????? ????? ?? ???????????? ???????? ??????? ??????

        panel = new JPanel();
        panel.setFocusable(true);
        add(panel);
        JButton button1 = new JButton("���������� �������������");
        button1.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(button1);
        JButton button2 = new JButton("��������� �������������");
        button2.setAlignmentY(RIGHT_ALIGNMENT);
        panel.add(button2);
        panel.setBackground(Color.green);
        panel.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                Point location = MouseInfo.getPointerInfo().getLocation();
                Ax = (int) location.getX() - AreaOfaPolygon.frame.getLocation().x;
                Ay = (int) location.getY() - AreaOfaPolygon.frame.getLocation().y;
                Panel.paintSquare(getGraphics());
                massp[n] = new ru.Position(Ax, Ay);
                n++;
                //		for(Position e1 : massp)
                //			System.out.println(e1.getpositionX() + " " + e1.getpoditionY());
            }
        });
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                massp[n]=massp[0];
                massp[n+1]=massp[1];
                massp[n+2]=massp[2];
                for(i=0;i<n;i++)
                {
                    Ax = massp[i].getPositionAx();
                    Ay = massp[i].getPositionAy();
                    Bx = massp[i+1].getPositionAx();
                    By = massp[i+1].getPositionAy();
                    Cx = massp[i+2].getPositionAx();
                    Cy = massp[i+2].getPositionAy();
                    Dx = massp[i+3].getPositionAx();
                    Dy = massp[i+3].getPositionAy();
                    Panel.paintLine(getGraphics(), Ax, Ay, Bx, By);
                    Position position1 = CreateLine.intersection(Ax, Ay, Bx, By, Cx, Cy);
                    Position position2 = CreateLine.intersection(Bx, By, Cx, Cy, Dx, Dy);
                    massp[i]=position1;
                    Panel.paintLine(getGraphics(), position1.getPositionAx(), position1.getPositionAy(), position2.getPositionAx(), position2.getPositionAy());
                    if(i == n-1) {
                        massp[n]=massp[0];
                        massp[n+1]=massp[1];
                        massp[n+2]=massp[2];
                    }
//                    xk += Ax;
//                    yk += Ay;
//                    count++;
                }
//                xk =(xk + Bx)/(count+1);
//                yk =(yk + By)/(count+1);
//                Ax = xk;
//                Ay = yk;
            }
        });
    }
}