#Holden Anderson
#School
#12-2-21

import commonGameFunctions as cgf


def instructions():
    """ This definition will let you print a funny string about the instructions of tic tac toe"""
    print("""Welcome to the greatest intellectual challenge of all time: Tic-Tac-Toe.
               This will be a showdown between your human brain and my silicon processor.
               You will make your move known by entering a number, 1 - 9. The number
               will correspond to the board position as illustrated:

                          1 | 2 | 3
                          ---------
                          4 | 5 | 6
                          ---------
                          7 | 8 | 9

              Prepare yourself, human. The ultimate battle is about to begin. """)
# function list
# display instructions
# new board
# ask yes no or flip coin
# assign pieces
# display board current
# legal moves
# check for winner
# human move
# computer move
# switching turns
# congrats winner

# new board
def NEW_BOARD():
    """define a new board"""
    board = []
    for i in range(MAX_SQUARES):
        board.append(EMPTY_TOKEN)
    return board

def ask_yes_no():
    pass




# give pieces
def pieces():
    """Allows you to decide which pieces you want to use"""
    global human_player
    global comp_player
    answer = cgf.ask_yes_no("would you like to go first")
    if answer == "yes":
        print("you will need it to stand any chance of winning")
        human_player = X_TOKEN
        comp_player = O_TOKEN

# display board current
def DISPLAY_BOARD(gameboard):
    """ display the board """
    print(str.format("""
    {} | {} | {}
    ------------
    {} | {} | {}
    ------------
    {} | {} | {}""", gameboard[0],gameboard[1],gameboard[2],gameboard[3],gameboard[4],gameboard[5],gameboard[6],gameboard[7],gameboard[8]))


# legal moves
def legal_moves():
    """ defines where you can still play a token legally"""
    pass


# check for winner
def winner():
    """ends the game if the right combination of winner requirements are fulfilled"""
    pass


# human move
def human_move(board):
    """this function defines logic for the user to place their token on an empty slot"""

    while True:
        spot = cgf.ask_number_in_range("where would you like to place your tokens",1,9)
        spot = spot-1
        if board[spot] == EMPTY_TOKEN:
            board[spot]=human_player
            break
        else:
            print("you can't go there")


# computer move, I think this one is set to play with another human instead of a computer
def comp_move(board):
    """this function defines logic for the opponent to place their token on an empty slot"""
    while True:
        spot = cgf.ask_number_in_range("where would you like to place your tokens",1,9)
        spot = spot-1
        if board[spot] == EMPTY_TOKEN:
            board[spot]=comp_player
            break
        else:
            print("you can't go there")

    # switching turns
def switch_turns(turn):
    """used to move the game along, pass in manually if you're tryna cheat or smthin"""
    if turn == X_TOKEN:
        turn = O_TOKEN
    else:
        turn = X_TOKEN
        return turn



#GLobal Variables
MAX_SQUARES = (9)
EMPTY_TOKEN = " "
X_TOKEN = "X"
O_TOKEN = "O"
human_player = ""
comp_player = ""

def main():
    """Runs the defs"""
    turn = X_TOKEN
    # display the game instructions
    instructions()
    # create an empty tic_tac_toe board
    GAMEBOARD = NEW_BOARD()
    pieces()
    while True:
        DISPLAY_BOARD(GAMEBOARD)
        if turn == human_player:
            human_move(GAMEBOARD)
        else:
            comp_move(GAMEBOARD)
        turn = switch_turns(turn)

    # determine who goes first
    # while playing (no players have got 3 in a row and board still has empty squares)
    # if turn was x
    # get x move
    # placed x token //update board
    # else
    # get o move
    # placed o token //update board
    # switch turns
    # congratulate the winner or declare a tie


main()
