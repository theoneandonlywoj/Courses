'''
Quiz: Total Sales
In this quiz, you’ll need to change the types of the input and output data in order to get the result you want.

Calculate and print the total sales for the week from the data provided. 
Print out a string of the form "This week's total sales: xxx", where xxx will be the actual total of all the numbers. 
You’ll need to change the type of the input data in order to calculate that total.
'''

mon_sales = "121"
tues_sales = "105"
wed_sales = "110"
thurs_sales = "98"
fri_sales = "95"

# TODO: Print a string with this format: This week's total sales: xxx
# You will probably need to write some lines of code before the print statement.
message = "This week's total sales: " + str(int(mon_sales) + int(tues_sales) 
                       + int(wed_sales) + int(thurs_sales) + int(fri_sales))
         
print(message)