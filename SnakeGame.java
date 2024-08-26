// This Block Is Used To Import Required Packages.

import java.util.*;

//_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________

//  This Block Is Used To Deal With Board By Creating Board Adding Snake's Body etc.

class SnakeGame {
    // These Strings Are Going To Be Used To Color Text.
    static String setBackSkin = "\033[48;2;168;128;104m", setBackSkinDark = "\033[48;2;133;101;82m",
            red = "\033[38;2;150;30;20m", cyan = "\033[38;2;0;100;100 m", green = "\033[92m", blue = "\033[94m",
            pink = "\033[38;2;180;20;100m", endColor = "\033[0m";

    // This Object Is From Random Class To Generate Random Value.
    static Random rand = new Random();

    // This Array Is Used To Create Snake Game Board.
    String board[][] = new String[30][30];

    // This Is The Location Of The Head Of Snake
    locations Head;

    // This Class Is Used To Create Custom Linked List With Two Variable Having
    // Location For Body Of Snake For The Array Board.
    class locations {
        int row, col;
        locations next;

        locations(int row, int col) {
            this.row = row;
            this.col = col;
            next = null;
        }
    }

    // This Method Is Used To Clear The Board Array.
    void clearBoard(String[][] board) {
        this.board = new String[board.length][board[0].length];
    }

    // This Method Is Used To Check If Snake Hits Itself.
    boolean findElements(int row, int col) {
        if (board[row][col] == null) {
            return true;
        }
        return false;
    }

    // This Method Is Used To Add First In Custom Linked List 'locations'.
    void addFirst(int row, int col) {
        // This Is To Check If Linked List Is Empty Or Not.
        if (Head == null) {
            Head = new locations(row, col);
        } else {
            locations temp = new locations(row, col);
            temp.next = Head;
            Head = temp;
        }
    }

    // This Method Is To Delete Last Element Of Custom Linked List
    void delLast() {
        if (Head != null) {
            locations temp = Head;
            if (temp.next == null) {
                Head.next = null;
            } else {
                while (temp.next != null && temp.next.next != null) {
                    temp = temp.next;
                }
                temp.next = null;
            }
        }
    }

    // This Method Is To Move Snake Into The Given Direction
    boolean moveForward(int row, int col, int hRow, int hCol) {
        locations temp = Head;
        while (temp != null) {

            // if To check if Snake Collid with itself.
            if (findElements(row, col)) {

                // String to store Value of snake Body at given location
                String str = board[temp.row][temp.col];

                // To Check if movement is Upward Or Downward.
                if (temp.row == row) {

                    // if To check in which Direction is Snake Goining
                    if (temp.col > col) {
                        str = "<<";

                        // else if To check in which Direction is Snake Goining
                    } else if (temp.col < col) {
                        str = ">>";
                    }

                    // To Check if movement is Left Or Right.
                } else if (temp.col == col) {

                    // if To check in which Direction is Snake Goining
                    if (temp.row > row) {
                        str = "^^";

                        // else if To check in which Direction is Snake Goining
                    } else if (temp.row < row) {
                        str = "vv";
                    }
                }

                // This is Used To Replace Value.
                board[temp.row][temp.col] = board[row][col];
                board[row][col] = str;
                row = temp.row;
                col = temp.col;
                temp = temp.next;
            } else
                return true;
        }
        return false;
    }

    // This Method Is Used To Dislay Board With Snake And Food
    void displayBoard() {
        int counter_k = 0, limit = board.length * 3 + 2;
        System.out.print("\n\n");

        // This is To print Top Of Board.
        System.out.print(blue + ".");
        while (counter_k++ < limit) {
            System.out.print("-");
        }
        System.out.println("." + endColor);

        // Nested For Loop To get all the values of board Array.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                // if To Print first Part Of Board.
                if (j == 0)
                    System.out.print(blue + "| " + endColor);

                // if To Print empty Space when there is no element.
                if (board[i][j] == null)
                    System.out.print("   ");
                else {

                    // if Condition Used to Color Flag Reddish-Brown.
                    if (board[i][j].equals("|>"))
                        System.out.print(red + board[i][j] + endColor + " ");

                    // else if Condition Used to Color Snake Head Dark Pink.
                    else if (board[i][j].equals("OO"))
                        System.out.print(pink + board[i][j] + endColor + " ");

                    // else Condition Used to Color Snake body Green.
                    else
                        System.out.print(green + board[i][j] + endColor + " ");
                }

                // if To Print End Of Board.
                if (j == board[i].length - 1)
                    System.out.print(blue + " |" + endColor);
            }
            System.out.println();
        }

        // This is To print Bottom Of Board.
        System.out.print(blue + "'" + endColor);
        while (counter_k-- > 1) {
            System.out.print(blue + "-" + endColor);
        }
        System.out.print(blue + "'" + endColor);
        System.out.print("\n\n");
    }

    // This Is To Check If Spaces Available
    boolean checkForSpace(String[][] Box) {
        int i = -1, j = -1;
        while (++i < Box.length) {
            while (++j < Box[i].length) {

                // if Condition to check if There is Empty Space.
                if (Box[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    // This Is To Count Number Of Spaces
    int countForSpace(String[][] Box) {
        int i = -1, j = -1, counter = 0;
        while (++i < Box.length) {
            while (++j < Box[i].length) {
                // if To increase counter by 1 when Empty space available.
                if (Box[i][j] == null) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

// _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________

// This Block Is Used To Have Switch Case To Take User Input For Snake Movement.

class switchCase {

    // Thse Strings are Used to Give Color t Output In CMD.
    static String setBackSkin = "\033[48;2;168;128;104m", setBackSkinDark = "\033[48;2;133;101;82m",
            red = "\033[38;2;255;0;0m", yellow = "\033[93m", cyan = "\033[38;2;0;100;100m", green = "\033[92m",
            blue = "\033[94m", purple = "\033[38;2;27;3;163m", magenta = "\033[38;2;255;0;255m",
            endColor = "\033[0m", dRed = "\033[38;2;150;0;50m";
    Scanner sc = new Scanner(System.in);
    SnakeGame game = new SnakeGame();
    RunGame runGame = new RunGame();

    // To Initialize Local Variables Required.
    Random rand = new Random();
    boolean fullClose = false, close = false, pause = false, gameStart = true;
    int moves = 0, flagCap = 0, highestScore = runGame.highestScore, totalMoves = 0;
    String input = "", lastInput = "w";
    int row = 0, col = 0;
    long diffLevel;

    // This Method is Used to get User Input for which Direction user wants to Move
    // Snake.
    void switchCaseMeth(int start_row, int start_col, int food_Spawn_rate, int start_Food_Count) {

        game.board = new String[start_row][start_col];
        // This used to get value for Head of Snake near the center so that Snake Head
        // does not spawn near Border and End game Immediatly.
        row = rand.nextInt(game.board.length / 3) + game.board.length / 3;
        col = rand.nextInt(game.board[row].length / 3) + game.board[row].length / 3;

        // Food assignment
        int counter = 0, limit = game.countForSpace(game.board);

        // while Loop used to give some fixed amount of Food On the Board When Game
        // Start.
        int tempRow = rand.nextInt(game.board.length), tempCol = rand.nextInt(game.board[tempRow].length);
        while (counter++ < start_Food_Count) {
            while (game.board[tempRow][tempCol] != null) {
                tempRow = rand.nextInt(game.board.length);
                tempCol = rand.nextInt(game.board[tempRow].length);
            }
            game.board[tempRow][tempCol] = "|>";
            tempRow = rand.nextInt(game.board.length);
            tempCol = rand.nextInt(game.board[tempRow].length);
        }

        // This is To initialize Head.
        game.board[row][col] = "OO";
        boolean invalidInput = false;
        System.out.println(cyan +
                "\n\nEnter Your choice :-\n   W/w for Up.\n   A/a for Left.\n   S/s for Down.\n   D/d for Right.\n   P/p to Pause Your Game.\n   E/e to End This Turn.\n"
                + endColor);
        do {
            if (!invalidInput) {

                // To increase number of moves after every Go.
                totalMoves++;

                // if To add Flag after 10 moves.
                if (moves++ == food_Spawn_rate && game.checkForSpace(game.board)) {
                    int fRow = rand.nextInt(game.board.length), fCol = rand.nextInt(game.board[fRow].length);

                    // To insert flag and check that the location is empty.
                    while (game.board[fRow][fCol] != null) {
                        fRow = rand.nextInt(game.board.length);
                        fCol = rand.nextInt(game.board[fRow].length);
                    }

                    // To add Flag the Board.
                    game.board[fRow][fCol] = "|>";
                    moves = 0;
                }

                // To display Board after each step.
                game.displayBoard();

                // This is to wait for User input according to difficulty level.
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < diffLevel) {
                    try {
                        if (System.in.available() > 0) {
                            input = sc.nextLine().toLowerCase();
                            break;
                        } else {
                            input = lastInput;
                        }
                    } catch (Exception e) {
                    }
                }

                // if User didnt enter any value then print Times up and move in the same
                // Direction as it was.
                if (System.currentTimeMillis() - startTime >= diffLevel)
                    System.out.println(red + "\n\nTime's Up Moving Forward." + endColor);

                // if To check if new input given then to store last Enterd value by user {i.e
                // Last Direction snake was Moving}.
                if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d"))
                    lastInput = input;

                invalidInput = false;

                // else This is to make it so that when player Clicks Enter then The game Uses
                // last Input {i.e The direction it was moving in}.
            } else {
                input = lastInput;
                invalidInput = false;
            }

            // switch case to move the Snake in the direction given by user.
            switch (input) {

                // Case w : To Move Snake Upawards.
                case "w": {

                    // if To check If Food was Eaten Or Not.
                    if (row > 0 && game.board[row - 1][col] == "|>") {

                        // To Increase Length Of Snake As It Consumed Food.
                        game.board[row][col] = "^^";

                        // To Store Location of new Snake.
                        game.addFirst(row, col);

                        // To Move Head In said Direction.
                        game.board[--row][col] = "OO";

                        // To Increase Score By 1.
                        flagCap++;

                        // else if To Check If The Snake Is Hitting Border Of Board.
                    } else if (row > 0) {

                        // To replace Head With The Said Position To Swap.
                        game.board[row][col] = game.board[--row][col];

                        // To move Snake In Said Direction.
                        game.board[row][col] = "OO";

                        // if To Check If Snake Collided with Itself.
                        if (game.Head != null) {
                            close = game.moveForward(row + 1, col, row, col);

                            // This is to Store Location of Snakes Neck
                            game.addFirst(row + 1, col);

                            // To remove the extra bit Of Snakes Body as it Hasn't Consumed Food
                            game.delLast();
                        }
                    } else
                        close = true;
                    break;
                }

                // Case a : To Move Snake To Left Side.
                case "a": {

                    // if To check If Food was Eaten Or Not.
                    if (col > 0 && game.board[row][col - 1] == "|>") {

                        // To Increase Length Of Snake As It Consumed Food.
                        game.board[row][col] = "<<";

                        // To Store Location of new Snake.
                        game.addFirst(row, col);

                        // To Move Head In said Direction.
                        game.board[row][--col] = "OO";

                        // To Increase Score By 1.
                        flagCap++;

                        // else if To Check If The Snake Is Hitting Border Of Board.
                    } else if (col > 0) {

                        // To replace Head With The Said Position To Swap.
                        game.board[row][col] = game.board[row][--col];

                        // To move Snake In Said Direction.
                        game.board[row][col] = "OO";

                        // if To Check If Snake Collided with Itself.
                        if (game.Head != null) {
                            close = game.moveForward(row, col + 1, row, col);

                            // This is to Store Location of Snakes Neck
                            game.addFirst(row, col + 1);
                            // To remove the extra bit Of Snakes Body as it Hasn't Consumed Food
                            game.delLast();
                        }
                    } else
                        close = true;
                    break;
                }

                // Case s : To Move Snake Downwards.
                case "s": {

                    // if To check If Food was Eaten Or Not.
                    if (row < game.board.length - 1 && game.board[row + 1][col] == "|>") {

                        // To Increase Length Of Snake As It Consumed Food.
                        game.board[row][col] = "vv";

                        // To Store Location of new Snake.
                        game.addFirst(row, col);

                        // To Move Head In said Direction.
                        game.board[++row][col] = "OO";

                        // To Increase Score By 1.
                        flagCap++;

                        // else if To Check If The Snake Is Hitting Border Of Board.
                    } else if (row < game.board.length - 1) {

                        // To replace Head With The Said Position To Swap.
                        game.board[row][col] = game.board[++row][col];

                        // To move Snake In Said Direction.
                        game.board[row][col] = "OO";

                        // if To Check If Snake Collided with Itself.
                        if (game.Head != null) {
                            close = game.moveForward(row - 1, col, row, col);

                            // This is to Store Location of Snakes Neck
                            game.addFirst(row - 1, col);

                            // To remove the extra bit Of Snakes Body as it Hasn't Consumed Food
                            game.delLast();
                        }
                    } else
                        close = true;
                    break;
                }

                // Case d : To Move Snake To Right Side
                case "d": {

                    // if To check If Food was Eaten Or Not.
                    if (col < game.board[row].length - 1 && game.board[row][col + 1] == "|>") {

                        // To Increase Length Of Snake As It Consumed Food.
                        game.board[row][col] = ">>";

                        // To Store Location of new Snake.
                        game.addFirst(row, col);

                        // To Move Head In said Direction.
                        game.board[row][++col] = "OO";

                        // To Increase Score By 1.
                        flagCap++;

                        // else if To Check If The Snake Is Hitting Border Of Board.
                    } else if (col < game.board.length - 1) {

                        // To replace Head With The Said Position To Swap.
                        game.board[row][col] = game.board[row][++col];

                        // To move Snake In Said Direction.
                        game.board[row][col] = "OO";

                        // if To Check If Snake Collided with Itself.
                        if (game.Head != null) {
                            close = game.moveForward(row, col - 1, row, col);

                            // This is to Store Location of Snakes Neck
                            game.addFirst(row, col - 1);

                            // To remove the extra bit Of Snakes Body as it Hasn't Consumed Food
                            game.delLast();
                        }
                    } else
                        close = true;
                    break;
                }

                // Case p : Pause Game
                case "p": {

                    // This Is To Print User Score When He choses To Pause The Game.
                    System.out.println(yellow + "\nYour Current Score Is : " + endColor + green + flagCap + endColor
                            + yellow + "\nTotal Moves are : "
                            + endColor + green + totalMoves + endColor + yellow
                            + "\nEnter Any Key To Continue Game." + endColor);

                    // This is To Continue If user Enters Any Key
                    sc.nextLine();
                    break;
                }

                // Case e : End game
                case "e": {

                    // To store the start time when user choose Case e
                    long startTime2 = System.currentTimeMillis();
                    System.out.print(dRed +
                            "\nEnter Your Choice In 5 Sec.\nAre You Sure You Want To Exit.\nEnter N If You Dont Wont To Delete Your Progress : "
                            + endColor);
                    close = true;

                    // This is give user 5 seconds to continue Playing the game or Exit. If 5
                    // seconds reached then game will end.
                    while (System.currentTimeMillis() - startTime2 < 5000) {

                        try {

                            if (System.in.available() > 0) {
                                System.out.print(red);
                                switch (sc.nextLine().toLowerCase()) {

                                    // Case n : If user Wants To continue playing game
                                    case "n": {
                                        close = false;
                                        break;
                                    }
                                    // Default ie if no input or any input other tha 'N' given then game will end.
                                    default: {
                                        close = true;
                                        break;
                                    }
                                }
                                System.out.print(endColor);
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    break;
                }
                default: {
                    invalidInput = true;
                    break;
                }
            }
        } while (!close);

        // This is to Print the Final Score when the game Ends.
        System.out
                .println(purple + "\n\nYour Score Is : " + endColor + magenta + "" + flagCap + "" + endColor + purple
                        + "\nAnd Total number of Moves Were : " + endColor + magenta + "" + totalMoves + "" + endColor
                        + "\n");

        // if To check If this Was the Highest Score So far or not.
        if (flagCap > highestScore)
            highestScore = flagCap;
        // If It was highest Score then to Replace it with main method Highest Score{As
        // that is used to store Highest score While playing Game}.
        runGame.highestScore = highestScore;
    }

}

// _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________

// This Method Is Used To Run Game By Taking User Input For Difficulty Or To Get
// High Score Or To Exit The Game.

class RunGame {

    static int highestScore = 0;
    static String red = "\033[38;2;150;0;0m", green = "\033[92m", blue = "\033[94m", endColor = "\033[0m",
            cyan = "\033[38;2;0;150;150m", magenta = "\033[35m", purple = "\033[38;2;27;3;163m",
            nGreen = "\033[38;2;57;255;20m";

    public static void main(String[] args) {

        // This Is Object Of Scanner Class To Get User Input.
        Scanner sc = new Scanner(System.in);

        // This Object Is From Random Class To Generate Random Value.
        Random rand = new Random();

        // This Object Is Used To Get Board For Sanke Game And Access Methods Of
        // SnakeGame Class.
        SnakeGame game = new SnakeGame();

        // This Is A Flag To Check If User Wants To Exit Game Or Not.
        boolean fullClose = false;

        // This Is Used To Select Difficulty And Food Spawn Rate.
        int start_row = 0, start_col = 0, food_Spawn_rate = 0, start_Food_Count = 0;

        // This Is Object Of Switch Case Class Which is Used To Take User Input For
        // Snake Movement.
        switchCase switch_Case = new switchCase();

        try {
            // We Use try_catch To Just Make Sure That There is No Exception While Running
            // Code.
            // But For This Code It Mainly Focusses To Stop Coloring For Output In CMD So
            // That It Does Not Glitch Out.

            do {
                boolean end = false;
                do {
                    System.out.print(cyan
                            + "\nEnter Your Choice :\n  1. Play New Game\n  2. Show Highest Score\n  3. Exit Game.\n\n\t\t"
                            + endColor + red);

                    switch (sc.nextLine()) {
                        // Case 1 : To Play New Game.
                        case "1": {
                            switch_Case = new switchCase();
                            game.clearBoard(game.board);
                            boolean flag = true;
                            System.out.print("\n" + endColor + cyan +
                                    "\tEnter Your Choice : \n\t1. Beginner Difficulty\n\t2. Pro Difficulty\n\t3. Impossible Difficulty\n\n\t\t"
                                    + endColor + red);

                            do {
                                switch (sc.nextLine()) {
                                    // Case 1 : Easy Difficulty
                                    case "1": {
                                        switch_Case.diffLevel = 750;
                                        start_row = 10;
                                        start_col = 10;
                                        game.board = new String[start_row][start_col];
                                        start_Food_Count = 5;
                                        food_Spawn_rate = 5;
                                        flag = false;
                                        break;
                                    }
                                    // Case 2 : Normal Difficulty
                                    case "2": {
                                        switch_Case.diffLevel = 750;
                                        start_row = 20;
                                        start_col = 20;
                                        food_Spawn_rate = 25;
                                        game.board = new String[start_row][start_col];
                                        start_Food_Count = 10;
                                        flag = false;
                                        break;
                                    }
                                    // Case 3 : Hard Difficulty
                                    case "3": {
                                        switch_Case.diffLevel = 500;
                                        start_row = 35;
                                        start_col = 35;
                                        food_Spawn_rate = 50;
                                        game.board = new String[start_row][start_col];
                                        start_Food_Count = 20;
                                        flag = false;
                                        break;
                                    }

                                    default: {
                                        System.out.println("\nInvalid Input.\n");
                                        break;
                                    }
                                }
                                System.out.print(endColor);
                            } while (flag);
                            end = true;
                            break;
                        }

                        // Case 2 : To Display Highest Score.
                        case "2": {
                            System.out.println(
                                    magenta + "\nHighest Score Is : " + green + switch_Case.highestScore + endColor
                                            + magenta + "\n" + endColor);
                            switch_Case.gameStart = false;
                            end = true;
                            break;
                        }

                        // Case 3 : To Exit Game.
                        case "3": {
                            boolean flag = false;
                            do {
                                System.out.println(red +
                                        "\nAre you sure You want To Exit.\nYour Progress Will be Deleted.\nEnter N to Continue Or Any Other key to Exit.\n"
                                        + endColor + red);
                                switch (sc.nextLine().toLowerCase()) {

                                    // Case 1 : To Cancel Exit And Keep Highest Score Saved.
                                    case "n": {
                                        flag = true;
                                        break;
                                    }

                                    // Default : By Default Game Will Be Closed With Deleting All Progress.
                                    default: {
                                        flag = true;
                                        fullClose = true;
                                        break;
                                    }
                                }
                                System.out.print(endColor);
                            } while (!flag);

                            switch_Case.gameStart = false;
                            end = true;

                            break;
                        }
                        default: {
                            switch_Case.gameStart = false;
                            System.out.println(red + "\nInvalid Input.\n" + endColor);
                            break;
                        }
                    }
                } while (!end);
                if (switch_Case.gameStart) {

                    // This Is Used To Call Switch Case To Take User Input For Snake Movement.
                    switch_Case.switchCaseMeth(start_row, start_col, food_Spawn_rate, start_Food_Count);
                }
            } while (!fullClose);

            // This Is Used To Display Highest Score After The Game Is Ended
            System.out.println(purple +
                    "\n\nGame Over.\n\nYour Highest Score Is : " + endColor + nGreen + highestScore
                    + endColor + purple + "\n\nThank you for Playing Our Game.\n" + endColor);

        } catch (Exception e) {

            // This Is Used To End Color Fromatting So That It Does Not Continue For CMD.
            System.out.print(endColor);
        }

        // This Is To Close Scanner Object
        sc.close();
    }
}

// _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________
