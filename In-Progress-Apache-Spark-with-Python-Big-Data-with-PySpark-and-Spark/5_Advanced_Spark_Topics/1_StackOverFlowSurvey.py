from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')

if __name__ == "__main__":
    conf = SparkConf().setAppName('StackOverFlowSurvey').setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Initialize accumulators
    # Accumulator are write-only variables from worker's perspective
    total = sc.accumulator(0)
    missingSalaryMidPoint = sc.accumulator(0)

    # Load the data
    responseRDD = sc.textFile("data/2016-stack-overflow-survey-responses.csv")

    def filterResponseFromCanada(response):
        splits = Utils.COMMA_DELIMITER.split(response)

        # Increament the accumulator to get number of rows.
        total.add(1)

        # If the salary is missing (15th column -> column with index 14), 
        # increment the accumulator. 
        if not splits[14]:
            missingSalaryMidPoint.add(1)

        # Return true of false if the country columns is Canada
        return splits[2] == "Canada"

    responseFromCanada = responseRDD.filter(filterResponseFromCanada)
    # Countr number of rows in the responseFromCanada RDD.
    print("Count of responses from Canada: {}".format(responseFromCanada.count()))
    # Get value from the "total" accumulator
    print("Total count of responses: {}".format(total.value))
    # Get value from the "missingSalaryMidPoint" accumulator
    print("Count of responses missing salary middle point: {}".format(missingSalaryMidPoint.value))
