from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    conf = SparkConf().setAppName("create").setMaster("local")
    sc = SparkContext(conf = conf)

    # Creating a list of tuples
    tuples = [("Lily", 23), ("Jack", 29), ("Mary", 29), ("James", 8)]

    # parallelize creates a pair RDD from a Python collection
    # in this case it is a list
    pairRDD = sc.parallelize(tuples)

    # Coalesce does the repartition of the RDD
  	# Coalesce(1) will gather all RDD partition together into one. 
    pairRDD.coalesce(1).saveAsTextFile("output/pair_rdd_from_tuple_list")
