'''
Create a Spark program to read the an article from in/word_count.text,
output the number of occurrence of each word in descending order.

Sample output:

apple : 200
shoes : 193
bag : 176
...

'''
from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("wordCounts").setMaster("local[*]")
    sc = SparkContext(conf = conf)
    
    lines = sc.textFile("data/word_count.text")
    wordRdd = lines.flatMap(lambda line: line.split(" "))

    wordPairRdd = wordRdd.map(lambda word: (word, 1))
    wordToCountPairs = wordPairRdd.reduceByKey(lambda x, y: x + y)

    # Sort by the second value in the touple
    sortedWordCountPairs = wordToCountPairs.sortBy(lambda wordCount: wordCount[1], ascending = False)

    for word, count in  sortedWordCountPairs.collect():
        print("{} : {}".format(word, count))


