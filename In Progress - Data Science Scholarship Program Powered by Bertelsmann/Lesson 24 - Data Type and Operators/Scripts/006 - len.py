'''
Quiz: len()
Use string concatenation and the len() function to find the length of a certain movie star's actual full name. 
Store that length in the name_length variable. Don't forget that there are spaces in between the different parts of a name!
'''
given_name = "William"
middle_names = "Bradley"
family_name = "Pitt"

#todo: calculate how long this name is
name_length =  len(given_name + " " + middle_names + " " + family_name)


# Now we check to make sure that the name fits within the driving license character limit
# Nothing you need to do here
driving_license_character_limit = 28
print(name_length <= driving_license_character_limit)