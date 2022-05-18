import os.path
from os import *
import fileinput
input('Paste or enter the file name with qoutes around it (like this "/Users/Guy/Downloads") here: ')
filepath = input
fileinput.input(filepath)
#os.path.dirname("/Volumes/BRUHDRIVE/SeniorPics")
files = os.listdir(fileinput)
for f in files:
    if "_." not in f:
        print(f)