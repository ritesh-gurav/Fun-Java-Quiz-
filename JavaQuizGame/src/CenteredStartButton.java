import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenteredStartButton {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Quiz");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);

            // Create a panel with GridBagLayout for centering components and set background color
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(91, 199, 225)); // Set light blue background color
            frame.add(panel);
            GridBagConstraints gbc = new GridBagConstraints();

            // Add the headline "Welcome to Java Quiz" with larger font size and no spacing
            JLabel headlineLabel = new JLabel("Welcome to Java Quiz");
            headlineLabel.setFont(new Font("Arial", Font.BOLD, 50));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 0, 0);
            panel.add(headlineLabel, gbc);

            // Create the start button with specified colors and size
            JButton startButton = new JButton("START");
            startButton.setBackground(new Color(206, 20, 20));
            startButton.setForeground(Color.WHITE);
            startButton.setFont(new Font("Arial", Font.BOLD, 20));
            startButton.setPreferredSize(new Dimension(120, 40));
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create an instance of the QuizGame class when Start button is clicked
                    new QuizGame();
                    // Close the current frame if you want
                    frame.dispose();
                }
            });

            // Add the start button to the panel with no spacing
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            panel.add(startButton, gbc);

            // Center components in the middle of the panel
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panel.add(Box.createGlue(), gbc);

            // Set the frame to be visible
            frame.setVisible(true);

            // Center the frame on the screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        });
    }
}
