import javax.swing.*;
import java.awt.*;

public class TTTFrame extends JFrame
{
    private final Game game;
    private final TicTacToeTile[][] tiles =
            new TicTacToeTile[3][3];

    private JLabel statusLbl;

    public TTTFrame()
    {
        super("Tic Tac Toe");

        game = new Game();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);

        JPanel mainPnl = new JPanel(new BorderLayout(10, 10));
        add(mainPnl);

        statusLbl = new JLabel("Player X's turn", JLabel.CENTER);
        statusLbl.setFont(new Font("Arial", Font.BOLD, 22));
        mainPnl.add(statusLbl, BorderLayout.NORTH);

        JPanel boardPnl = new JPanel(new GridLayout(3, 3, 5, 5));

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                tiles[row][col] = new TicTacToeTile(row, col);
                tiles[row][col].setFont(new Font("Arial", Font.BOLD, 50));

                tiles[row][col].addActionListener(e ->
                {
                    TicTacToeTile clicked = (TicTacToeTile) e.getSource();
                    handleTileClick(clicked.getRow(), clicked.getCol());
                });

                boardPnl.add(tiles[row][col]);
            }
        }

        mainPnl.add(boardPnl, BorderLayout.CENTER);

        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> quitGame());

        JPanel controlPnl = new JPanel();
        controlPnl.add(quitBtn);
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                quitGame();
            }
        });

        setVisible(true);
    }

    private void handleTileClick(int row, int col)
    {
        if (!game.makeMove(row, col))
        {
            JOptionPane.showMessageDialog(this, "That square is already occupied. Choose another square.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        updateBoard();

        if (game.isWinner())
        {
            JOptionPane.showMessageDialog(this, "Player " + game.getCurrentPlayer() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            askToPlayAgain();
            return;
        }

        if (game.isTie()) {
            JOptionPane.showMessageDialog(this, "The game is a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            askToPlayAgain();
            return;
        }

        game.switchPlayer();
        statusLbl.setText("Player " + game.getCurrentPlayer() + "'s turn");
    }

    private void updateBoard()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                String mark = game.getCell(row, col);

                if (mark.equals(" "))
                {
                    tiles[row][col].setText("");
                }
                else
                {
                    tiles[row][col].setText(mark);
                }
            }
        }
    }

    private void askToPlayAgain() {
        int response = JOptionPane.showConfirmDialog(this, "Would you like to play another game?", "Play Again?", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION)
        {
            game.resetGame();
            updateBoard();

            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    tiles[row][col].setEnabled(true);
                }
            }

            statusLbl.setText("Player X's turn");
        }
        else
        {
            dispose();
        }
    }

    private void quitGame() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION)
        {
            dispose();
        }
    }
}