# Holden Anderson
import datetime
from random import randrange
import math

options = ("Snorkeling", "Scuba Diving", "Fishing", "Sunbathing", "Shopping", "Helicopter Ride", "Sleeping")
prices = (10.00, 150.00, 25.00, 0.00, 200.00, 450.00, 0.00)
costList = []
# def formforTimes():
startDateString = input("Enter the starting date of your vacation like this: MM/DD/YYY")
startDate = datetime.datetime.strptime(startDateString, "%m/%d/%Y")
startonlydate = datetime.datetime.date(startDate)
# print(type(startDate))
print(startonlydate)
# copy this if youre lazy: 06/05/2012. It's the right input format, it works with the output string
stopDateString = input("Enter the starting date of your vacation like this: MM/DD/YYY")
stopDate = datetime.datetime.strptime(stopDateString, "%m/%d/%Y")
# print(type(stopDate))06/06/2021
stopOnlyDate = datetime.datetime.date(stopDate)
print(stopOnlyDate)

vacayLen = stopDate - startDate
print("Your vacation is {} days long".format(vacayLen.days))
# return startDate
# return stopDate
# return vacayLen

costs = []

# def calculateActivities(vacayLen, startDate):
for i in range(0, vacayLen.days):
    #from random import randrange

    # from datetime import *
    activityIndex = randrange(len(options))
    # print(type(activityIndex))
    print(activityIndex)
    print(options[activityIndex])
    #str.options[activityIndex]
    #print(type(activityIndex))
    #options[activityIndex].append(costList)
    activity = (options[activityIndex])
    cost = prices[activityIndex]
    thisDate = startDate + datetime.timedelta(days=i)
    thisDateString = datetime.datetime.strftime(thisDate,"%A %m/%d/%Y")
    print(thisDateString)
print(str.format("On {} your activity will be: {}, it costs ${:.2f}", thisDateString, activity, cost))

total = costs
