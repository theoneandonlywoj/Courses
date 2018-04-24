from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("count").setMaster("local[*]")
    sc = SparkContext(conf = conf)
    
    inputWords = ["spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop"]
    
    wordRdd = sc.parallelize(inputWords)

    # Counting all elements using count
    print("Count: {}".format(wordRdd.count()))
    
    # Counting by value
    worldCountByValue = wordRdd.countByValue()
    print("CountByValue: ")
    for word, count in worldCountByValue.items():
        print("{} : {}".format(word, count))

