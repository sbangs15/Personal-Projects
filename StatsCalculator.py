#Program Description:
# The purpose of this program is to collect a data set from the user and ask them what statistical calculation they need to be provided.
# This statistic calculator provides 3 calculations (Mean, Median, and Mode). The user can select certain calculations they want 
# (Example: Only Mode, Only Mean and Median, etc) and if they want all 3 calculations to be performed the user can select the all of the 
# above option to perform all calculations. After the user selects what calculations they want, the calculations are displayed and the 
# program ends.


#Implimentations
# 1. To implement decisions using if statements: Lines: 27,36,50,53,56
# 2. To write statements using the boolean primitive data types: Lines: 97,99,101
# 3. To compare strings and/or characters: Line 85
# 4. To write loops using while or for : For Loops: Lines: 27,28,etc While Loop: Line 73
# 5. To write functions: Mean Function(Lines:18-24), Sort Function(Lines:26-34), Median Function (Lines: 36-46), Mode Function(Lines:48-63)


#function to calculate mean of data
def mean(num_list):
    #initialize sum variable
    sum = 0 
    
    #for loop goes through list and adds all the numbers 
    for i in num_list:
        sum += i
    
    #calculates mean/average by dividing sum of numbers by length of list
    avg = sum/len(num_list)
    #prints mean
    print("\nMean: ",avg)

#function to sort list of numbers 
def sort(num_list):
    #Use bubble sort method to sort numbers
    
    #first for loop makes sure the loop continues till numbers are sorted
    for i in range(0,len(num_list)-1):  
        for j in range(len(num_list)-1):  
            #checks if the index is greater than the next index
            if(num_list[j]> num_list[j+1]):  
                #puts number at the index in a temparary variable to perform swap
                temp = num_list[j]  
                #since index j is greater than index j +1 variables swap to make it in increasing order 
                num_list[j] = num_list[j+1]  
                num_list[j+1] = temp 

#function to calculate the median of the data 
def median(num_list):
   
   # to find the median, data must be sorted
    sort(num_list)
    
    #checks if the amount of data in the list is odd 
    if (len(num_list) % 2 != 0):
        #since the length is the list is odd, median is the middle of the list
        print ("\nMedian: ", num_list[len(num_list)//2])
    else:  
        #since the length of the list is even there is not 1 middle number, so it takes the average of the two middle numbers
        median1 = num_list[len(num_list)//2] #returns middle index
        median2 = num_list[len(num_list)//2 - 1]#returns other middle index
        median = (median1 + median2)/2 #takes the average of the two middle numbers
        print("\nMedian" , median)#prints median

#function to calculate mode 
def mode(num_list):
    count = 0 
    List1 =[]
    modes =[]
    
    #loop to interate through list of numbers 
    for i in range (len(num_list)):
        count = num_list.count(num_list[i])#returns the amount of the value is in the list (Example: if 0 is in the list twice, it returns 2)
        List1.append(count)#appends counts to a list 
    
    top = max(List1)#returns the highest count in the list 
    
    #loop to iterate through list of numbers
    for i in range (len(num_list)):
        #checks if the count of the number is the same as the max and if it is not already the list of modes 
        if num_list.count(num_list[i]) == top and num_list[i] not in modes: 
            modes.append(num_list[i])# if condtions above return true, append to mode list 

    #if the the original list is the same length as the mode list, there is no mode
    if len(num_list) == len(modes):
        print("\nMode: There is no mode")
    else: 
        print("\nMode(s):", modes)#prints mode

if __name__=="__main__":
    #welcomes user to the calculator
    print("Welcome to the Statistics Calculator!\n") 
    #asks the user how much data is in the set 
    vals = int(input("Enter the amount of data is in the set: "))
    #initialize variables 
    n = 0 
    num_list= []
    
    #loop for user to enter each number of data set 
    while n < vals:
        num = float (input("Enter Number {}: ".format(n+1)))#gets input from user 
        num_list.append(num)#appends each number to a list 
        n+=1#increases num by 1
    #asks the user what calculations they want to calculator to perform 
    calc = input("\nWhat do you want to Calculate?\n\n1.Mean\n2.Median\n3.Mode\n4.All of the above\n\nEnter Corresponding Number(You may enter multiple numbers if more than 1 calculation is needed): ")
    
    #initailize boolean variables to false 
    meanCheck = False
    medianCheck = False 
    modeCheck = False
    
    #checks if user selects all of the above
    if calc == "4":
        mean(num_list)#performs mean function
        median(num_list)#perform median function
        mode(num_list)#perform mode function 
        
    else:
        if '1' in calc: #checks if 1 is in calc(user input)
         meanCheck = True #set meanCheck to True
        if '2'in calc:  #checks if 2 is in calc(user input)
         medianCheck = True #set medianCheck to True
        if '3' in calc:  #checks if 3 is in calc(user input)
         modeCheck = True #set modeCheck to True

    if meanCheck == True: #checks if meanCheck to True
        mean(num_list)#performs mean function
    if medianCheck == True: #checks if medianCheck to True
        median(num_list)#performs median function
    if modeCheck == True: #checks if modeCheck to True
        mode(num_list)#performs mode function

    print("\n\nThank You for using the Statistics Calculator!")#Thanks user for using calculator 


 