package root;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class UI extends JFrame {
    private final JButton getImgButton;
    private JLabel imgLbl;
    private JPanel mainPanel;
    private BufferedImage worldImg;



    public UI(ILandModel landModel) {
        //JFrame frame = new JFrame("Vormen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);

        mainPanel = new JPanel(new FlowLayout());
        JPanel buttonPane = new JPanel();
        getImgButton = new JButton("img");
        getImgButton.setVisible(false);
        JLabel img = new JLabel();
        mainPanel.add(img);

        getImgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use absolute path here:

                img.setIcon(new ImageIcon(worldImg));
                img.revalidate();
                img.repaint();
                img.update(img.getGraphics());
                img.setVisible(true);
                //mainPanel.updateUI();


                mainPanel.revalidate();
                mainPanel.repaint();
                mainPanel.update(img.getGraphics());
            }
        });

        buttonPane.add(getImgButton);
        // buttonPane.add(button2);
        // buttonPane.add(button3);

        this.add(buttonPane, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void display(BufferedImage image) {
        new Thread(){
            @Override
            public void run() {
                try {
                    worldImg = image;
                    if (worldImg != null)
                        getImgButton.doClick();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
