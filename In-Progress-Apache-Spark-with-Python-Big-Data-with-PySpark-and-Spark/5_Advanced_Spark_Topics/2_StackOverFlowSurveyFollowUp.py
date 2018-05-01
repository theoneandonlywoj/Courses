from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')

if __name__ == "__main__":
    conf = SparkConf().setAppName('StackOverFlowSurvey').setMaster("local[*]")
    sc = SparkContext(conf = conf)

    total = sc.accumulator(0)
    missingSalaryMidPoint = sc.accumulator(0)

    # Initializing a new accumulator
    processedBytes = sc.accumulator(0)
    responseRDD = sc.textFile("data/2016-stack-overflow-survey-responses.csv")

    def filterResponseFromCanada(response):
        # Adding number of bytes pre-processed 
        processedBytes.add(len(response.encode('utf-8')))
        splits = Utils.COMMA_DELIMITER.split(response)
        total.add(1)
        if not splits[14]:
            missingSalaryMidPoint.add(1)
        return splits[2] == "Canada"

    responseFromCanada = responseRDD.filter(filterResponseFromCanada)

    print("Count of responses from Canada: {}".format(responseFromCanada.count()))
    print("Number of bytes processed: {}".format(processedBytes.value))
    print("Total count of responses: {}".format(total.value))
    print("Count of responses missing salary middle point: {}".format(missingSalaryMidPoint.value))
