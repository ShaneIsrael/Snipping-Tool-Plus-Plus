package com.shaneisrael.st.utilities;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.shaneisrael.st.Main;

public class AnimatedTrayIcon implements Runnable
{
    private final int delayMs;
    private List<Image> frames;
    private boolean animate;

    public AnimatedTrayIcon(String basePath, int numFrames, int delayMs)
    {
        this.delayMs = delayMs;
        frames = new ArrayList<>();
        for (int i = 1; i < numFrames; i++)
        {
            frames.add(new ImageIcon(this.getClass().getResource(basePath + i + ".png")).getImage());
        }
        animate = true;
    }

    @Override
    public void run()
    {
        while (animate)
        {
            for (Image frame : frames)
            {
                Main.trayIcon.setImage(frame);
                try
                {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        Main.trayIcon.setImage(new ImageIcon(this.getClass().getResource("/images/trayIcon.png")).getImage());
    }

    public void stopAnimating()
    {
        animate = false;
    }
}
