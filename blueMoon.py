#Holden Anderson
#blueMoon assignment
#10-21

#ask about the blue moon
blueMoon = input("Is there a blue moon tonight?")

if (blueMoon == "Yes") or (blueMoon == "yes"):
    print("Once in a Blue Moon")
else:
    #get the day of the week
    weekDay = input("What is the day of the week (Sunday - Saturday)? ")
    #get the day of the month, specifically an int
    monthDay = int(input("What is the day of the month (1 - 31)?"))
    #make sure the day of the month is less than 7
    if (monthDay <= 7):
        #iterate through the days of the week
        #if the specific day of the week matches the form with the first letter capitalized,
        #or the lowercase form: print the related song title
        if weekDay == ("Monday") or weekDay ==("monday"):
            print("Manic Monday")
        elif weekDay == "Tuesday" or weekDay =="tuesday":
            print("Tuesday's Gone")
        elif weekDay == "Wednesday" or weekDay =="wednesday":
            print("Just Wednesday")
        elif weekDay == "Thursday" or weekDay =="thursday":
            print("Sweet Thursday")
        elif weekDay == "Friday" or weekDay =="friday":
            print("Friday I'm in Love")
        elif weekDay == "Saturday" or weekDay =="saturday":
            print("Saturday in the Park")
        elif weekDay == "Sunday" or weekDay =="sunday":
            print("Lazing on a Sunday Afternoon")
            #else print "Days of the WeeK
    else:
        print("Days of the Week")
