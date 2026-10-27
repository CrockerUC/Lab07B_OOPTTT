public class Game
{
    private TTTBoard board;
    private String currentPlayer;
    private int moveCount;

    public Game()
    {
        board = new TTTBoard();
        currentPlayer = "X";
        moveCount = 0;
    }

    public boolean makeMove(int row, int col)
    {
        if (!board.isValidMove(row, col))
        {
            return false;
        }

        board.makeMove(row, col, currentPlayer);
        moveCount++;
        return true;
    }

    public boolean isWinner()
    {
        return board.isWin(currentPlayer);
    }

    public boolean isTie()
    {
        return moveCount >= 7 && board.isTie();
    }

    public void switchPlayer()
    {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    public String getCurrentPlayer()
    {
        return currentPlayer;
    }

    public String getCell(int row, int col)
    {
        return board.getCell(row, col);
    }

    public void resetGame()
    {
        board.clearBoard();
        currentPlayer = "X";
        moveCount = 0;
    }
}
