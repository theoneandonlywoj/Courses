from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("create").setMaster("local")
    sc = SparkContext(conf = conf)

    inputStrings = ["Lily 23", "Jack 29", "Mary 29", "James 8"]
    regularRDDs = sc.parallelize(inputStrings)

    # Map function creating a tuple from the string.
    pairRDD = regularRDDs.map(lambda s: (s.split(" ")[0], s.split(" ")[1]))
    pairRDD.coalesce(1).saveAsTextFile("output/pair_rdd_from_regular_rdd")
