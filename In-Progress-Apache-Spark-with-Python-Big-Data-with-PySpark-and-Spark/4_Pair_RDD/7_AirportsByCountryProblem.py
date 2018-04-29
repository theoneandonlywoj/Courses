'''
Create a Spark program to read the airport data from in/airports.text,
output the the list of the names of the airports located in each country.

Each row of the input file contains the following columns:
Airport ID, Name of airport, Main city served by airport, Country where airport is located, IATA/FAA code,
ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

Sample output:

"Canada", ["Bagotville", "Montreal", "Coronation", ...]
"Norway" : ["Vigra", "Andenes", "Alta", "Bomoen", "Bronnoy",..]
"Papua New Guinea",  ["Goroka", "Madang", ...]
...

'''
from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')

if __name__ == "__main__":

    conf = SparkConf().setAppName("airports").setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Loading the file with special encoding
    # During the testing, system had issues with character \u0151
    lines = sc.textFile("data/airports.text", use_unicode = False).map(lambda x: x.decode("iso-8859-1"))

    # Creating a pair RDD
    countryAndAirportNameAndPair = lines.map(lambda airport:\
         (Utils.COMMA_DELIMITER.split(airport)[3],
          Utils.COMMA_DELIMITER.split(airport)[1]))

    # Reducing by key
    # That returns the key and an iterable value
    # For example
    # (UK, Manchester Airport), (UK, Liverpool Airport), (UK, London Airport) -> 
    # -> (UK, [Manchester Airport, Liverpool Airport, London Airport])
    airportsByCountry = countryAndAirportNameAndPair.groupByKey()

    for country, airportName in airportsByCountry.collectAsMap().items():
        print("\n{}: {}".format(country, list(airportName)))


     