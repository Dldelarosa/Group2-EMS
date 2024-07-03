import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class LoadingScreenApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame parentFrame = new JFrame();
                parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parentFrame.setSize(400, 200);
                parentFrame.setVisible(true);

                LoadingScreen loadingScreen = new LoadingScreen(parentFrame);
                loadingScreen.setVisible(true);
            }
        });
    }

    static class LoadingScreen extends JDialog {
        private JProgressBar progressBar;
        private Timer timer;
        private int progress = 0;

        public LoadingScreen(JFrame parent) {
            super(parent, "Loading", true);
            setSize(300, 100);
            setLocationRelativeTo(parent);

            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            getContentPane().add(progressBar, BorderLayout.CENTER);

            timer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress++;
                    progressBar.setValue(progress);
                    if (progress == 100) {
                        timer.stop();
                        dispose();
                    }
                }
            });
            timer.start();
        }
    }
}
