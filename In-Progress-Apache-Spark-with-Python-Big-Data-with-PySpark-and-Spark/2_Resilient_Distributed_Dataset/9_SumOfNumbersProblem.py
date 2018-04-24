'''
Create a Spark program to read the first 100 prime numbers from data/prime_nums.text,
print the sum of those numbers to console.
Each row of the input file contains 10 prime numbers separated by spaces.
'''

from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("primeNumbers").setMaster("local[*]")
    sc = SparkContext(conf = conf)
    
    lines = sc.textFile("data/prime_nums.text")

    # Split the lines by tab
    numbers = lines.flatMap(lambda line: line.split("\t"))

    # List of all numbers
    # Split result might contain empty strings as well
    # That is why we need to filter them out.
    validNumbers = numbers.filter(lambda number: number)
    
    # Converting all numbers from string to integers
    intNumbers = validNumbers.map(lambda number: int(number))
    
    # Sum all numbers using reduce
    print("Sum is: {}".format(intNumbers.reduce(lambda x, y: x + y)))
    
