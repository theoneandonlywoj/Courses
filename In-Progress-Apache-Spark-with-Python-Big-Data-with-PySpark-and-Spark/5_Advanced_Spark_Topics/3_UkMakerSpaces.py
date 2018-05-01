from pyspark import SparkContext, SparkConf
import re

class Utils():
    COMMA_DELIMITER = re.compile(''',(?=(?:[^"]*"[^"]*")*[^"]*$)''')

def loadPostCodeMap():
    # Loading the postcode data
    lines = open("data/uk-postcode.csv", "r").read().split("\n")
    splitsForLines = [Utils.COMMA_DELIMITER.split(line) for line in lines if line != ""]

    # Returning a dictionary {postcode_prefix : region}
    return {splits[0]: splits[7] for splits in splitsForLines}

def getPostPrefix(line: str):
    splits = Utils.COMMA_DELIMITER.split(line)
    postcode = splits[4]

    # Split postcode on the white space
    return None if not postcode else postcode.split(" ")[0]

if __name__ == "__main__":
    conf = SparkConf().setAppName('UkMakerSpaces').setMaster("local[*]")
    sc = SparkContext(conf = conf)

    # Broadcasting the postcode data
    # That results in distributing the data on each (a copy on each worker)
    # to speed up tha matching operations
    postCodeMap = sc.broadcast(loadPostCodeMap())

    # Load the makerSpace dataset as a string RDD
    makerSpaceRdd = sc.textFile("data/uk-makerspaces-identifiable-data.csv")

    # Filtering out the header.
    regions = makerSpaceRdd.filter(lambda line: Utils.COMMA_DELIMITER.split(line)[0] != "Timestamp") \
      .filter(lambda line: getPostPrefix(line) is not None) \
      .map(lambda line: postCodeMap.value[getPostPrefix(line)] if getPostPrefix(line) in postCodeMap.value else "Unknown")

    for region, count in regions.countByValue().items():
        print("{} : {}".format(region, count))
