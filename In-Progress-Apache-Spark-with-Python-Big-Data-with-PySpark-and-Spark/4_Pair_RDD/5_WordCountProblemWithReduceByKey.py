from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("wordCounts").setMaster("local[3]")
    sc = SparkContext(conf = conf)

    lines = sc.textFile("data/word_count.text")
    
    wordRdd = lines.flatMap(lambda line: line.split(" "))

    # Mapping word with a number 1 for later summation
    # Creating a tuple of word and number 1. 
    wordPairRdd = wordRdd.map(lambda word: (word, 1))

    # Reducing by key with function of summing up the 1's
    wordCounts = wordPairRdd.reduceByKey(lambda x, y: x + y)
    for word, count in wordCounts.collect():
        print("{} : {}".format(word, count))
