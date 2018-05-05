'''
Create a Spark program to read the airport data from in/airports.text, find all the airports which are located in United States
and output the airport's name and the city's name to out/airports_in_usa.text.

Each row of the input file contains the following columns:
Airport ID, Name of airport, Main city served by airport, Country where airport is located, IATA/FAA code,
ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

Sample output:
"Putnam County Airport", "Greencastle"
"Dowagiac Municipal Airport", "Dowagiac"
...
'''
from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')


def splitComma(line: str):
	# Split lines on the commas that are only outside of the quotation marks.
    splits = Utils.COMMA_DELIMITER.split(line)

    # Returning 2nd and 3rd columns separated by a comma
    return "{}, {}".format(splits[1], splits[2])

if __name__ == "__main__":
	# Configure Spark application giving it a name airports
	# Running it in the local mode with all available cores
    conf = SparkConf().setAppName("airports").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Load the files and create a RDD from it.
    airports = sc.textFile("data/airports.text")

    # Filter out only those airports that are in the United States
    # It is in the fourth column (index = 3)
    airportsInUSA = airports.filter(lambda line : Utils.COMMA_DELIMITER.split(line)[3] == "\"United States\"")

    # Map transformation using splitComma method
    airportsNameAndCityNames = airportsInUSA.map(splitComma)
    # Save resulting RDD as text
    airportsNameAndCityNames.saveAsTextFile("output/airports_in_usa.text")
