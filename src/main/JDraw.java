package main;

import ui.DrawWindow;

import com.alee.laf.WebLookAndFeel;

/**
 * 
 * @author Nick Stanish
 *
 */
public class JDraw {

  public static void main(String[] args) {

    WebLookAndFeel.install();

    new DrawWindow();
  }

}
