package pl.sdadas.fsbrowser.view.mainwindow;

import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebFrame;
import pl.sdadas.fsbrowser.Version;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * @author Sławomir Dadas
 */
public class MainWindow extends WebFrame {

    private final MainPanel panel;

    public MainWindow(MainPanel panel) {
        this.panel = panel;
        initView();
    }

    private void initView() {
        setMinimumSize(new Dimension(1024, 600));
        setContentPane(this.panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("FSBrowser v" + Version.getVersion() + "-" + Version.getRevision());
        setLocationRelativeTo(null);

        URL iconRes = MainWindow.class.getClassLoader().getResource("icons/fsbrowser.png");
        if (iconRes != null) {
            try {
                setIconImage(ImageIO.read(iconRes));
            } catch (IOException ignore) {
            }
        }

        pack();
    }

    public void run() {
        setVisible(true);
        if(panel.hasConnections()) {
            WebButton connect = panel.getStatusBarButton("Connect");
            if(connect != null) connect.doClick();
        } else {
            panel.showConnectionsDialog();
        }
    }
}
