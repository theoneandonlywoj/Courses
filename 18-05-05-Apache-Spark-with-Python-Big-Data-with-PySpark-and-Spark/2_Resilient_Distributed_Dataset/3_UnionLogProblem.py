'''
"in/nasa_19950701.tsv" file contains 10000 log lines from one of NASA's apache server for July 1st, 1995.
"in/nasa_19950801.tsv" file contains 10000 log lines for August 1st, 1995
Create a Spark program to generate a new RDD which contains the log lines from both July 1st and August 1st,
take a 0.1 sample of those log lines and save it to "out/sample_nasa_logs.tsv" file.

Keep in mind, that the original log files contains the following header lines.
host    logname    time    method    url    response    bytes

Make sure the head lines are removed in the resulting RDD.
'''
from pyspark import SparkContext, SparkConf

def isNotHeader(line: str):
	# Exluding lines which start with "host" and contain "bytes"
    return not (line.startswith("host") and "bytes" in line)

if __name__ == "__main__":
    conf = SparkConf().setAppName("unionLogs").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Loading log input files
    julyFirstLogs = sc.textFile("data/nasa_19950701.tsv")
    augustFirstLogs = sc.textFile("data/nasa_19950801.tsv")

    # Union of two logs
    # Because of the nature of union, the order does not matter
    # julyFirstLogs.union(augustFirstLogs) is the same as augustFirstLogs.union(julyFirstLogs)
    aggregatedLogLines = julyFirstLogs.union(augustFirstLogs)

    # Filter out the header
    cleanLogLines = aggregatedLogLines.filter(isNotHeader)

    # Return sample of size 1/10 of the union RDD with replacement sampling
    sample = cleanLogLines.sample(withReplacement = True, fraction = 0.1)

    sample.saveAsTextFile("output/sample_nasa_union_logs.csv")

