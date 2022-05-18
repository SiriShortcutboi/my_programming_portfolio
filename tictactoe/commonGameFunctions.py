#Holden Anderson
#school
#12-2-21 


import math




def roll_die(diesize):
    """ Used to get a random die number """
    import random

    roll = random.randint(1, diesize)
    return roll



def ask_yes_no(question):
    
    """ Get a y value for yes or an n value for no"""
    while True:
        answer = input(question)
        if "y" in answer.lower() and "n" in answer.lower():
            print("not a valid option")
        elif "y" in answer.lower():
            return "yes"
        elif "n" in answer.lower():
            return "no"
        else:
            print("not a valid option")



# define and flip a virtual coin
def flip_coin():
    """ flips a coin and returns the results of Heads or Tails"""
    import random
    results = None
    choices = {"Heads", "Tails"}
    results = random.choice(choices)
    return results


# ask yes or no flip coin
def ask_number_in_range(question, low, high):
    """ask the user to pick a num in a given range, return num if it has a good value"""
    while True:
        number = input(question)
        try:
            number = int(number)
            if number >= low:
                if number <= high:
                    return number
                else:
                    print("that number is too high")
            else:
                print("that number is too low")
        except:
            print("not a good number")
            return number

def pick_random_card(deck):
    import random
    card = random.choice(deck)
    return card

