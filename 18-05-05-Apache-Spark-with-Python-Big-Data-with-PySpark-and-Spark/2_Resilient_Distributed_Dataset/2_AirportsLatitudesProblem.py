'''
Create a Spark program to read the airport data from in/airports.text,  find all the airports whose latitude are bigger than 40.
Then output the airport's name and the airport's latitude to out/airports_by_latitude.text.

Each row of the input file contains the following columns:
Airport ID, Name of airport, Main city served by airport, Country where airport is located, IATA/FAA code,
ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

Sample output:
"St Anthony", 51.391944
"Tofino", 49.082222
...
'''

from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')


def splitComma(line: str):
	# Split lines on the commas that are only outside of the quotation marks.
    splits = Utils.COMMA_DELIMITER.split(line)

    # Returning 2nd and 6th columns separated by a comma
    return "{}, {}".format(splits[1], splits[6])

if __name__ == "__main__":
	# Configure Spark application giving it a name airports
	# Running it in the local mode with all available cores
    conf = SparkConf().setAppName("airports").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Load the files and create a RDD from it.
    airports = sc.textFile("data/airports.text")

    # Filter out only those airports have latitudes greater than 40
    # It is in the seventh column (index = 6)
    airportsInUSA = airports.filter(lambda line : float(Utils.COMMA_DELIMITER.split(line)[6]) > 40)

    # Map transformation using splitComma method
    airportsNameAndCityNames = airportsInUSA.map(splitComma)
    # Save resulting RDD as text
    airportsNameAndCityNames.saveAsTextFile("output/airports_latitudes.text")
