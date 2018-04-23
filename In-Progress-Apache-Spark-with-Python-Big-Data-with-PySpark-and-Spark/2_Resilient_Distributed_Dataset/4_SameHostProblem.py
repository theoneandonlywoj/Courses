'''
"in/nasa_19950701.tsv" file contains 10000 log lines from one of NASA's apache server for July 1st, 1995.
"in/nasa_19950801.tsv" file contains 10000 log lines for August 1st, 1995
Create a Spark program to generate a new RDD which contains the hosts which are accessed on BOTH days.
Save the resulting RDD to "output/nasa_logs_same_hosts.csv" file.

Example output:
vagrant.vf.mmc.com
www-a1.proxy.aol.com
.....    

Keep in mind, that the original log files contains the following header lines.
host    logname    time    method    url    response    bytes

Make sure the head lines are removed in the resulting RDD.
'''
from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("hosts").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Loading log input files
    julyFirstLogs = sc.textFile("data/nasa_19950701.tsv")
    augustFirstLogs = sc.textFile("data/nasa_19950801.tsv")

    # Getting only hosts
    # Split on table and get the first column
    julyFirstHosts = julyFirstLogs.map(lambda line: line.split('\t')[0])
    augustFirstHosts = augustFirstLogs.map(lambda line: line.split('\t')[0])

    # Same in both days means used both on the 1st of July and 1st of August
    # That corresponds to INTERSECTION
    # Because of the nature of intersection, the order does not matter
    # julyFirstHosts.intersection(augustFirstHosts) is the same as augustFirstHosts.intersection(julyFirstHosts)
    hosts_used_both_days = julyFirstHosts.intersection(augustFirstHosts)

    # Filter out the header which corresponds to word "host"
    cleaned_hosts = hosts_used_both_days.filter(lambda host: host != 'host')

    cleaned_hosts.saveAsTextFile("output/nasa_logs_same_hosts.csv")

