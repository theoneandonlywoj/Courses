'''
Create a Spark program to read the airport data from in/airports.text;
generate a pair RDD with airport name being the key and country name being the value.
Then remove all the airports which are located in United States and output the pair RDD to out/airports_not_in_usa_pair_rdd.text

Each row of the input file contains the following columns:
Airport ID, Name of airport, Main city served by airport, Country where airport is located,
IATA/FAA code, ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

Sample output:

("Kamloops", "Canada")
("Wewak Intl", "Papua New Guinea")
...

'''
from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')

if __name__ == "__main__":

    conf = SparkConf().setAppName("airports").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    airportsRDD = sc.textFile("data/airports.text")

    # Getting the 2th (index 1) the 4th (index 3) value and creating a following tuple:
    # (Name of airport, Country where airport is located) 
    airportPairRDD = airportsRDD.map(lambda line: (Utils.COMMA_DELIMITER.split(line)[1], Utils.COMMA_DELIMITER.split(line)[3]))

    # Filtering out airports that are not in the US
    airportsNotInUSA = airportPairRDD.filter(lambda keyValue: keyValue[1] != "\"United States\"")

    airportsNotInUSA.saveAsTextFile("output/airports_not_in_usa_pair_rdd.text")

