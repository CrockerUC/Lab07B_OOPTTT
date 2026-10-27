public class TTTBoard
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private final String[][] board = new String[ROW][COL];

    public TTTBoard()
    {
        clearBoard();
    }

    public void clearBoard()
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    public boolean isValidMove(int row, int col)
    {
        return row >= 0 && row < ROW
                && col >= 0 && col < COL
                && board[row][col].equals(" ");
    }

    public void makeMove(int row, int col, String player)
    {
        if (isValidMove(row, col)) {
            board[row][col] = player;
        }
    }

    public String getCell(int row, int col)
    {
        return board[row][col];
    }

    public boolean isWin(String player)
    {
        // Check rows.
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player)
                    && board[row][1].equals(player)
                    && board[row][2].equals(player))
            {
                return true;
            }
        }

        // Check columns.
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player)
                    && board[1][col].equals(player)
                    && board[2][col].equals(player))
            {
                return true;
            }
        }

        // Check both diagonals.
        if (board[0][0].equals(player)
                && board[1][1].equals(player)
                && board[2][2].equals(player))
        {
            return true;
        }

        return board[0][2].equals(player)
                && board[1][1].equals(player)
                && board[2][0].equals(player);
    }

    public boolean isTie()
    {
        // A tie occurs when no row, column, or diagonal
        // can still be completed by either player.
        for (int row = 0; row < ROW; row++)
        {
            if (lineCanWin(board[row][0], board[row][1], board[row][2]))
            {
                return false;
            }
        }

        for (int col = 0; col < COL; col++)
        {
            if (lineCanWin(board[0][col], board[1][col], board[2][col]))
            {
                return false;
            }
        }

        if (lineCanWin(board[0][0], board[1][1], board[2][2]))
        {
            return false;
        }

        return !lineCanWin(board[0][2], board[1][1], board[2][0]);
    }

    private boolean lineCanWin(String first, String second, String third)
    {
        boolean hasX = first.equals("X")
                || second.equals("X") || third.equals("X");

        boolean hasO = first.equals("O")
                || second.equals("O") || third.equals("O");

        return !(hasX && hasO);
    }
}
