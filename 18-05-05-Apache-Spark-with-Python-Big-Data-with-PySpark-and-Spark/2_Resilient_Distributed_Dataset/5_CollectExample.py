from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("CollectExample").setMaster("local[*]")
    sc = SparkContext(conf = conf)
    
    # Fake dataset
    inputWords = ["spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop"]
    
    # Distributing the dataset
    wordRdd = sc.parallelize(inputWords)
    
    # Collect operation retrieves the entire RDD and returns it to the driver program in the form of a regular collection of value.
    # For that reason, the "collected" RDD needs to fit to the memory of the driver program.
    # Collect operation is widely used in unit tests.
    # For example:
    # If you have a string RDD, when you call collection action on it, you would get a list of strings.
    # 
    words = wordRdd.collect()
    
    for word in words:
        print(word)

