import pygame as pg
from sys import *
import os

# game options/settings
TITLE = "Jumpy Boi Time"
gamefolder = os.path.join(os.path.dirname("__file__"))
imgfolder = os.path.join(gamefolder, "img")

WIDTH = 480
HEIGHT = 600
FPS = 60

#Player properties
PLAYER_ACCEL = 0.5
PLAYER_FRICTION = -0.12
PLAYER_GRAV = 0.8

# define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
