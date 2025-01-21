import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGame {
    private JFrame frame;
    private JLabel instructions;
    private JLabel scoreLabel;
    private int userScore;
    private int computerScore;
    private int roundsPlayed;
    private boolean gameOver;

    public RockPaperScissorsGame() {
        userScore = 0;
        computerScore = 0;
        roundsPlayed = 0;
        gameOver = false;

        // Initialize the main frame
        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Instructions label
        instructions = new JLabel("Choose Rock, Paper, or Scissors:", SwingConstants.CENTER);
        frame.add(instructions, BorderLayout.NORTH);

        // Score label
        scoreLabel = new JLabel("Score: You - 0 | Computer - 0", SwingConstants.CENTER);
        frame.add(scoreLabel, BorderLayout.SOUTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        // Buttons for Rock, Paper, and Scissors
        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        rockButton.addActionListener(e -> play("Rock"));
        paperButton.addActionListener(e -> play("Paper"));
        scissorsButton.addActionListener(e -> play("Scissors"));

        frame.setVisible(true);
    }

    private void play(String userChoice) {
        if (gameOver) {
            JOptionPane.showMessageDialog(frame, "Game over! Click 'Play Again' to restart.");
            return;
        }

        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[new Random().nextInt(choices.length)];
        String result = determineWinner(userChoice, computerChoice);

        if (result.equals("Win")) {
            userScore++;
            JOptionPane.showMessageDialog(frame, "You chose " + userChoice + ". Computer chose " + computerChoice + ". You win this round!");
        } else if (result.equals("Lose")) {
            computerScore++;
            JOptionPane.showMessageDialog(frame, "You chose " + userChoice + ". Computer chose " + computerChoice + ". You lose this round!");
        } else {
            JOptionPane.showMessageDialog(frame, "You chose " + userChoice + ". Computer chose " + computerChoice + ". It's a tie!");
        }

        roundsPlayed++;
        updateScore();

        // Check for game over condition
        if (userScore == 3 || computerScore == 3) {
            gameOver = true;
            if (userScore == 3) {
                JOptionPane.showMessageDialog(frame, "Congratulations! You win the game! Final score: You " + userScore + " - " + computerScore + " Computer");
            } else {
                JOptionPane.showMessageDialog(frame, "Game Over! Computer wins the game! Final score: Computer " + computerScore + " - " + userScore + " You");
            }
            int response = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "Tie";
        }
        if ((userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
            (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
            (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Win";
        }
        return "Lose";
    }

    private void updateScore() {
        scoreLabel.setText("Score: You - " + userScore + " | Computer - " + computerScore);
    }

    private void resetGame() {
        userScore = 0;
        computerScore = 0;
        roundsPlayed = 0;
        gameOver = false;
        updateScore();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RockPaperScissorsGame::new);
    }
}
